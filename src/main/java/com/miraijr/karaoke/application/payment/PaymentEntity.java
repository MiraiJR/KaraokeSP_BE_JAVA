package com.miraijr.karaoke.application.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miraijr.karaoke.application.order.OrderEntity;
import com.miraijr.karaoke.application.room.RoomEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "room_name"
    )
    private String roomName;

    @Column(
            name = "singing_time",
            nullable = false,
            columnDefinition = "BIGINT default 0"
    )
    private Long singingTime;

    @Column(
            name = "total_money_product",
            nullable = false,
            columnDefinition = "BIGINT default 0"
    )
    private Long totalMoneyProduct;

    @Column(
            name = "total_money_hour",
            nullable = false,
            columnDefinition = "BIGINT default 0"
    )
    private Long totalMoneyHour;

    @Column(
            name = "total_money",
            nullable = false,
            columnDefinition = "BIGINT default 0"
    )
    private Long totalMoney;

    @JsonIgnore
    @OneToOne(
            mappedBy = "payment",
            cascade = CascadeType.REMOVE
    )
    private RoomEntity room;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    public PaymentEntity() {
        this.singingTime = 0L;
        this.totalMoneyProduct = 0L;
        this.totalMoneyHour = 0L;
        this.totalMoney = 0L;
    }

    public PaymentEntity(String roomName) {
        this.roomName = roomName;
        this.singingTime = 0L;
        this.totalMoneyProduct = 0L;
        this.totalMoneyHour = 0L;
        this.totalMoney = 0L;
    }

    public PaymentEntity(Long singingTime, Long totalMoneyProduct, Long getTotalMoneyHour, Long totalMoney) {
        this.singingTime = singingTime;
        this.totalMoneyProduct = totalMoneyProduct;
        this.totalMoneyHour = getTotalMoneyHour;
        this.totalMoney = totalMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSingingTime() {
        return singingTime;
    }

    public void setSingingTime(Long singingTime) {
        this.singingTime = singingTime;
    }

    public Long getTotalMoneyProduct() {
        return totalMoneyProduct;
    }

    public void setTotalMoneyProduct(Long totalMoneyProduct) {
        this.totalMoneyProduct = totalMoneyProduct;
    }

    public Long getTotalMoneyHour() {
        return totalMoneyHour;
    }

    public void setTotalMoneyHour(Long getTotalMoneyHour) {
        this.totalMoneyHour = getTotalMoneyHour;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", singingTime=" + singingTime +
                ", totalMoneyProduct=" + totalMoneyProduct +
                ", totalMoneyHour=" + totalMoneyHour +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
