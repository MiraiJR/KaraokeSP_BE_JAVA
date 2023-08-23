package com.miraijr.karaoke.shared.utils;

import com.miraijr.karaoke.application.payment.PaymentEntity;
import com.miraijr.karaoke.application.room.RoomEntity;
import com.miraijr.karaoke.application.room.types.RoomDetail;

import java.util.Date;

import static com.miraijr.karaoke.shared.constants.Constant.ONE_MINUTE;

public class Helper {
    public static Long calculateSingingTime(Date startedAt, Date endedAt) {
        Long timeDifferenceInMillis = endedAt.getTime() - startedAt.getTime();
        Long singingTime = timeDifferenceInMillis / ONE_MINUTE;

        return singingTime;
    }

    public static Long calculateSingingMoney(Long singingTime, Long price) {
        Double hours = (double) singingTime / 60;
        Double roundedHours = Math.round(hours * 100.0) / 100.0;
        return (long) (roundedHours * price);
    }

    public static RoomDetail convertRoomToRoomDetail(RoomEntity room) {
        return new RoomDetail(room, room.getOrder(), room.getPayment());
    }

    public static Long calculateTotalMoneyOfPayment(PaymentEntity payment) {
        return payment.getTotalMoneyHour() + payment.getTotalMoneyProduct();
    }
}
