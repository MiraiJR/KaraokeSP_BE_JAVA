package com.miraijr.karaoke.application.payment;

import com.miraijr.karaoke.application.order.OrderEntity;
import com.miraijr.karaoke.application.order_product.OrderProductEntity;
import com.miraijr.karaoke.application.product.ProductEntity;
import com.miraijr.karaoke.application.room.RoomEntity;
import com.miraijr.karaoke.shared.utils.Helper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.miraijr.karaoke.shared.constants.Constant.ONE_MINUTE;

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

    public PaymentEntity calculatePayment(RoomEntity room) {
        PaymentEntity payment = room.getPayment();
        List<OrderProductEntity> products = room.getOrder().getProducts();

        if (products.size() != 0) {
            Long totalMoneyProduct = 0L;

            for (OrderProductEntity orderedProduct : products) {
                totalMoneyProduct += orderedProduct.getQuantity() * orderedProduct.getProduct().getPrice();
            }

            payment.setTotalMoneyProduct(totalMoneyProduct);
        }

        payment.setSingingTime(Helper.calculateSingingTime(room.getStartedAt(), room.getEndedAt()));
        payment.setTotalMoneyHour(Helper.calculateSingingMoney(payment.getSingingTime(), room.getPrice()));
        payment.setTotalMoney(Helper.calculateTotalMoneyOfPayment(payment));

        return paymentRepository.save(payment);
    }
}
