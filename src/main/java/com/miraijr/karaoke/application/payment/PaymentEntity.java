package com.miraijr.karaoke.application.payment;

import com.miraijr.karaoke.application.order.OrderEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

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
    private Long getTotalMoneyHour;

    @Column(
            name = "total_money",
            nullable = false,
            columnDefinition = "BIGINT default 0"
    )
    private Long totalMoney;

    public PaymentEntity(Long singingTime, Long totalMoneyProduct, Long getTotalMoneyHour, Long totalMoney) {
        this.singingTime = singingTime;
        this.totalMoneyProduct = totalMoneyProduct;
        this.getTotalMoneyHour = getTotalMoneyHour;
        this.totalMoney = totalMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
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

    public Long getGetTotalMoneyHour() {
        return getTotalMoneyHour;
    }

    public void setGetTotalMoneyHour(Long getTotalMoneyHour) {
        this.getTotalMoneyHour = getTotalMoneyHour;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "id=" + id +
                ", order=" + order +
                ", singingTime=" + singingTime +
                ", totalMoneyProduct=" + totalMoneyProduct +
                ", getTotalMoneyHour=" + getTotalMoneyHour +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
