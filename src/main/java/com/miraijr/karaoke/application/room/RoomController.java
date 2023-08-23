package com.miraijr.karaoke.application.room;

import com.miraijr.karaoke.application.room.DTOs.RoomDTO;
import com.miraijr.karaoke.application.room.types.RoomDetail;
import com.miraijr.karaoke.shared.utils.Helper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@Validated
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RoomEntity> handleGetRooms() {
        List<RoomEntity> rooms = roomService.getRooms();

        return rooms;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public RoomEntity handleCreateNewRoom(@RequestBody @Valid RoomDTO room) {
        RoomEntity newRoom = roomService.createNewRoom(room);

        return newRoom;
    }

    @PutMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public RoomEntity handleUpdateRoom(@RequestBody @Valid RoomDTO room, @PathVariable Integer roomId) {
        RoomEntity updatedRoom = roomService.updateRoom(roomId, room);

        return updatedRoom;
    }

    @DeleteMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public String handleDeleteRoom(@PathVariable Integer roomId) {
        roomService.deleteRoom(roomId);

        return "Delete room with id [%s] successfully!".formatted(roomId);
    }

    @GetMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public RoomDetail handleGetSpecificRoom(@PathVariable Integer roomId) {
        RoomDetail room = roomService.getRoomDetailById(roomId);

        return room;
    }

    @PutMapping("/{roomId}/open")
    public RoomDetail handleOpenRoom(@PathVariable Integer roomId) {
        RoomDetail room = roomService.openRoom(roomId);

        return room;
    }

    @PutMapping("/{roomId}/close")
    public RoomDetail handleCloseRoom(@PathVariable Integer roomId) {
        RoomEntity room = roomService.closeRoom(roomId);

        return Helper.convertRoomToRoomDetail(room);
    }
}
