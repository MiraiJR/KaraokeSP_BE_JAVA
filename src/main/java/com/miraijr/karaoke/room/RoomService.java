package com.miraijr.karaoke.room;

import com.miraijr.karaoke.room.DTOs.RoomDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoomService(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    public RoomEntity createNewRoom(RoomDTO room) {
        RoomEntity convertedRoom = modelMapper.map(room, RoomEntity.class);
        convertedRoom.setAvailable(false);

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

        if(!isExisted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        roomRepository.deleteById(roomId);
    }

    public RoomEntity getRoomById(Integer roomId) {
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return room;
    }

    public List<RoomEntity> getRooms() {
        List<RoomEntity> rooms = roomRepository.findAll();

        return rooms;
    }
}
