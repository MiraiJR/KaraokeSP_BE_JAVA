package com.miraijr.karaoke.application.room;

import com.miraijr.karaoke.application.order.OrderEntity;
import com.miraijr.karaoke.application.order.OrderService;
import com.miraijr.karaoke.application.ordered_product.DTOs.OrderedProductDTO;
import com.miraijr.karaoke.application.ordered_product.OrderedProductService;
import com.miraijr.karaoke.application.payment.PaymentEntity;
import com.miraijr.karaoke.application.payment.PaymentService;
import com.miraijr.karaoke.application.room.DTOs.RoomDTO;
import com.miraijr.karaoke.application.room.types.RoomDetail;
import com.miraijr.karaoke.shared.utils.Converter;
import com.miraijr.karaoke.shared.utils.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final OrderedProductService orderedProductService;

    @Autowired
    public RoomService(RoomRepository roomRepository, ModelMapper modelMapper, OrderService orderService, PaymentService paymentService, OrderedProductService orderedProductService) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.orderedProductService = orderedProductService;
    }

    public RoomEntity createNewRoom(RoomDTO room) {
        RoomEntity convertedRoom = modelMapper.map(room, RoomEntity.class);
        convertedRoom.setAvailable(true);

        return roomRepository.save(convertedRoom);
    }

    public RoomEntity updateRoom(Integer roomId, RoomDTO room) {
        RoomEntity updatedRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        updatedRoom.setFloor(room.getFloor());
        updatedRoom.setPrice(room.getPrice());
        updatedRoom.setName(room.getName());

        return roomRepository.save(updatedRoom);
    }

    public void deleteRoom(Integer roomId) {
        Boolean isExisted = roomRepository.existsById(roomId);

        if (!isExisted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        roomRepository.deleteById(roomId);
    }

    public RoomEntity getRoomById(Integer roomId) {
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));

        return room;
    }

    public RoomDetail getRoomDetailById(Integer roomId) {
        RoomEntity room = getRoomById(roomId);
        room.setEndedAt(new Date());

        if (room.getOrder() != null && room.getPayment() != null) {
            return new RoomDetail(room, room.getOrder(), paymentService.calculatePayment(room));
        }

        return Converter.convertRoomToRoomDetail(room);
    }

    public List<RoomEntity> getRooms(Boolean available) {
        if(available) {
            return roomRepository.findAvailableRoom(available);
        }

        Sort sort = Sort.by(Sort.Order.asc("id"));
        return roomRepository.findAll(sort);
    }

    public RoomDetail openRoom(Integer roomId) {
        RoomEntity room = getRoomById(roomId);

        if (!room.getAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[%s] đang hoạt động!".formatted(room.getName()));
        }

        OrderEntity orderOfRoom = orderService.initOrder();
        PaymentEntity paymentOfRoom = paymentService.initPayment(room.getName(), orderOfRoom);

        room.setPayment(paymentOfRoom);
        room.setOrder(orderOfRoom);
        room.setAvailable(false);
        room.setStartedAt(new Date());

        room = roomRepository.save(room);

        RoomDetail roomDetail = new RoomDetail(room, orderOfRoom, paymentOfRoom);
        return roomDetail;
    }

    public RoomEntity closeRoom(Integer roomId) {
        RoomEntity room = getRoomById(roomId);

        if (room.getAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[%s] không hoạt động!".formatted(room.getName()));
        }

        room.setPayment(paymentService.calculatePayment(room));
        paymentService.updatePayment(room.getPayment());

        room = defaultField(room);
        return roomRepository.save(room);
    }

    public RoomDetail orderProduct(Integer roomId, OrderedProductDTO orderedProduct) {
        RoomEntity room = getRoomById(roomId);

        if (room.getAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[%s] không hoạt động!".formatted(room.getName()));
        }

        OrderEntity updatedOrder = orderedProductService.orderProduct(room.getOrder(), orderedProduct);
        room.setOrder(updatedOrder);
        room.setPayment(paymentService.calculatePayment(room));

        return Converter.convertRoomToRoomDetail(room);
    }

    public RoomDetail updateOrderedProduct(Integer roomId, OrderedProductDTO orderedProduct) {
        RoomEntity room = getRoomById(roomId);

        if (room.getAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[%s] không hoạt động!".formatted(room.getName()));
        }

        OrderEntity updatedOrder = orderedProductService.updateOrderedProduct(room.getOrder(), orderedProduct);
        room.setOrder(updatedOrder);
        room.setPayment(paymentService.calculatePayment(room));

        return Converter.convertRoomToRoomDetail(room);
    }

    public RoomDetail changeRoom(Integer sourceRoomId, Integer destinationRoomId) {
        RoomEntity sourceRoom = getRoomById(sourceRoomId);
        if (sourceRoom.getAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[%s] không hoạt động!".formatted(sourceRoom.getName()));
        }

        RoomEntity destinationRoom = getRoomById(destinationRoomId);
        if (!destinationRoom.getAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[%s] đang hoạt động!".formatted(destinationRoom.getName()));
        }

        // open room
        destinationRoom.setStartedAt(sourceRoom.getStartedAt());
        destinationRoom.setAvailable(false);

        // move order + payment of source room to destination room
        sourceRoom.getOrder().setRoom(destinationRoom);
        sourceRoom.getPayment().setRoom(destinationRoom);
        destinationRoom.setOrder(sourceRoom.getOrder());
        destinationRoom.setPayment(sourceRoom.getPayment());

        // close source room
        sourceRoom = defaultField(sourceRoom);

        // update destination + source room to database
        roomRepository.save(sourceRoom);
        destinationRoom = roomRepository.save(destinationRoom);
        destinationRoom.setPayment(paymentService.calculatePayment(destinationRoom));
        return Converter.convertRoomToRoomDetail(destinationRoom);
    }

    public RoomEntity defaultField(RoomEntity room) {
        room.setAvailable(true);
        room.setStartedAt(null);
        room.setEndedAt(null);
        room.setPayment(null);
        room.setOrder(null);

        return room;
    }
}
