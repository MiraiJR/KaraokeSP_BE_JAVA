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

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(
            nullable = false
    )
    private Long price;

    @Column(
            nullable = true,
            name = "started_at"
    )
    private Date startedAt;


    @Column(
            nullable = true,
            name = "ended_at"
    )
    private Date endedAt;
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

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Date endedAt) {
        this.endedAt = endedAt;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", floor=" + floor +
                ", isAvailable=" + isAvailable +
                ", price=" + price +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                ", order=" + order +
                '}';
    }
}
