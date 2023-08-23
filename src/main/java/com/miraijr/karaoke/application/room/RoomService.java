package com.miraijr.karaoke.application.room;

import com.miraijr.karaoke.application.order.OrderEntity;
import com.miraijr.karaoke.application.order.OrderService;
import com.miraijr.karaoke.application.payment.PaymentEntity;
import com.miraijr.karaoke.application.payment.PaymentService;
import com.miraijr.karaoke.application.room.DTOs.RoomDTO;
import com.miraijr.karaoke.application.room.types.RoomDetail;
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

    @Autowired
    public RoomService(RoomRepository roomRepository, ModelMapper modelMapper, OrderService orderService, PaymentService paymentService) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
        this.orderService = orderService;
        this.paymentService = paymentService;
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
        RoomDetail roomDetail = new RoomDetail(room, room.getOrder(), room.getPayment());

        return roomDetail;
    }

    public List<RoomEntity> getRooms() {
        Sort sort = Sort.by(Sort.Order.asc("id"));
        List<RoomEntity> rooms = roomRepository.findAll(sort);

        return rooms;
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
        room.setEndedAt(new Date());

        paymentService.calculatePayment(room);

        room = defaultField(room);
        return roomRepository.save(room);
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
