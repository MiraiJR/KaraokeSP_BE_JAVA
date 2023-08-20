package com.miraijr.karaoke.application.room.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RoomDTO {
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Floor cannot be null")
    @Positive(message = "Floor must be a positive number")
    private Integer floor;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive number")
    private Long price;

    public RoomDTO() {
    }

    public RoomDTO(@NotNull(message = "Name cannot be null") String name, @NotNull(message = "Floor cannot be null") Integer floor, @NotNull(message = "Price cannot be null") Long price) {
        this.name = name;
        this.floor = floor;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CreateRoomDTO{" +
                "name='" + name + '\'' +
                ", floor=" + floor +
                ", price=" + price +
                '}';
    }
}
