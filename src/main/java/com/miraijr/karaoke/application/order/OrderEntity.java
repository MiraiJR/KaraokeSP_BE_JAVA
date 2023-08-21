package com.miraijr.karaoke.application.order;

import com.miraijr.karaoke.application.order_product.OrderProductEntity;
import com.miraijr.karaoke.application.room.RoomEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL
    )
    private List<OrderProductEntity> products;

    public OrderEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public List<OrderProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductEntity> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", room=" + room +
                ", products=" + products +
                '}';
    }
}
