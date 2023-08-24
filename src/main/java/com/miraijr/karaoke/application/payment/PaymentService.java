package com.miraijr.karaoke.application.payment;

import com.miraijr.karaoke.application.order.OrderEntity;
import com.miraijr.karaoke.application.ordered_product.OrderedProductEntity;
import com.miraijr.karaoke.application.room.RoomEntity;
import com.miraijr.karaoke.shared.utils.Helper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentEntity initPayment(String roomName, OrderEntity order) {
        PaymentEntity payment = new PaymentEntity(roomName);
        payment.setOrder(order);

        return paymentRepository.save(payment);
    }

    public PaymentEntity updatePayment(PaymentEntity payment) {
        return paymentRepository.save(payment);
    }

    public PaymentEntity calculatePayment(RoomEntity room) {
        if (room.getEndedAt() == null) {
            room.setEndedAt(new Date());
        }
        
        PaymentEntity payment = room.getPayment();
        List<OrderedProductEntity> products = room.getOrder().getProducts();

        if (products.size() != 0) {
            Long totalMoneyProduct = 0L;

            for (OrderedProductEntity orderedProduct : products) {
                totalMoneyProduct += orderedProduct.getQuantity() * orderedProduct.getProduct().getPrice();
            }

            payment.setTotalMoneyProduct(totalMoneyProduct);
        }

        payment.setSingingTime(Helper.calculateSingingTime(room.getStartedAt(), room.getEndedAt()));
        payment.setTotalMoneyHour(Helper.calculateSingingMoney(payment.getSingingTime(), room.getPrice()));
        payment.setTotalMoney(Helper.calculateTotalMoneyOfPayment(payment));

        return payment;
    }
}
