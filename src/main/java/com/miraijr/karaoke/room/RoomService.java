package com.miraijr.karaoke.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomEntity createNewRoom() {
        RoomEntity room = new RoomEntity();

        return  room;
    }
}
