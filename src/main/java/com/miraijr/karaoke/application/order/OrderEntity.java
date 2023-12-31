package com.miraijr.karaoke.application.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miraijr.karaoke.application.ordered_product.OrderedProductEntity;
import com.miraijr.karaoke.application.room.RoomEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    private List<OrderedProductEntity> products;

    @JsonIgnore
    @OneToOne(
            mappedBy = "order",
            cascade = CascadeType.REMOVE
    )
    private RoomEntity room;

    public OrderEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderedProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProductEntity> products) {
        this.products = products;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", products=" + products +
                ", room=" + room +
                '}';
    }
}
