package com.miraijr.karaoke.shared.utils;

import com.miraijr.karaoke.application.group_product.GroupProductEntity;
import com.miraijr.karaoke.application.product.types.GroupProductResponse;
import com.miraijr.karaoke.application.room.RoomEntity;
import com.miraijr.karaoke.application.room.types.RoomDetail;

public class Converter {
    public static GroupProductResponse convertToGroupProductResponse(GroupProductEntity groupProduct) {
        return new GroupProductResponse(groupProduct, groupProduct.getProducts());
    }

    public static RoomDetail convertRoomToRoomDetail(RoomEntity room) {
        return new RoomDetail(room, room.getOrder(), room.getPayment());
    }
}
