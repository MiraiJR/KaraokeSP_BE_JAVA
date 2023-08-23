package com.miraijr.karaoke.application.room.types;

import com.miraijr.karaoke.application.order.OrderEntity;
import com.miraijr.karaoke.application.payment.PaymentEntity;
import com.miraijr.karaoke.application.room.RoomEntity;

public class RoomDetail {
    RoomEntity room;
    OrderEntity order;
    PaymentEntity payment;

    public RoomDetail() {
    }

    public RoomDetail(RoomEntity room, OrderEntity order, PaymentEntity payment) {
        this.room = room;
        this.order = order;
        this.payment = payment;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "RoomDetail{" +
                "room=" + room +
                ", order=" + order +
                ", payment=" + payment +
                '}';
    }
}
