package com.miraijr.karaoke.application.room.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ChangeRoomDTO {
    @NotNull(message = "RoomId cannot be null")
    @Positive(message = "RoomId must be a positive number")
    private Integer roomId;

    public ChangeRoomDTO() {
    }

    public ChangeRoomDTO(@NotNull(message = "RoomId cannot be null") Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
