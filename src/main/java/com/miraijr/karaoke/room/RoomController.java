package com.miraijr.karaoke.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;


    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
}
