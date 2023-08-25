package com.miraijr.karaoke.application.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    @Query("select r from RoomEntity r join fetch r.order join fetch r.payment where r.id = :roomId")
    RoomEntity findRoomEager(@Param("roomId") Integer roomId);

    @Query ("select r from RoomEntity r where r.isAvailable = :available order by r.id ASC")
    List<RoomEntity> findAvailableRoom(@Param("available") Boolean available);
}
