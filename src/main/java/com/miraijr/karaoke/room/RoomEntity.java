package com.miraijr.karaoke.room;

import com.miraijr.karaoke.order.OrderEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "rooms")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            nullable = false
    )
    private Integer floor;

    @Column(
           columnDefinition = "bit default B'0'"
    )
    private Boolean is_available;

    @Column(
            nullable = false
    )
    private Long price;

    @Column(
            nullable = true
    )
    private Date started_at;

    @Column(
            nullable = true
    )
    private Date ended_at;

    @OneToOne(
            mappedBy = "room",
            cascade = CascadeType.ALL
    )
    private OrderEntity order;

    public RoomEntity() {
    }

    public RoomEntity(String name, Integer floor, Long price) {
        this.name = name;
        this.floor = floor;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getStarted_at() {
        return started_at;
    }

    public void setStarted_at(Date started_at) {
        this.started_at = started_at;
    }

    public Date getEnded_at() {
        return ended_at;
    }

    public void setEnded_at(Date ended_at) {
        this.ended_at = ended_at;
    }
}
