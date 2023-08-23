package com.miraijr.karaoke.application.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    @Query("select r from RoomEntity r join fetch r.order join fetch r.payment where r.id = :roomId")
    RoomEntity findRoomEager(@Param("roomId") Integer roomId);
}
