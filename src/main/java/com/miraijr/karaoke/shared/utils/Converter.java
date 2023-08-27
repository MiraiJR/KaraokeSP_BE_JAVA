package com.miraijr.karaoke.shared.utils;

import com.miraijr.karaoke.application.group_product.GroupProductEntity;
import com.miraijr.karaoke.application.product.ProductEntity;
import com.miraijr.karaoke.application.product.types.GroupProductResponse;
import com.miraijr.karaoke.application.product.types.ProductDetail;
import com.miraijr.karaoke.application.room.RoomEntity;
import com.miraijr.karaoke.application.room.types.RoomDetail;

public class Converter {
    public static GroupProductResponse convertToGroupProductResponse(GroupProductEntity groupProduct) {
        return new GroupProductResponse(groupProduct, groupProduct.getProducts());
    }

    public static RoomDetail convertRoomToRoomDetail(RoomEntity room) {
        return new RoomDetail(room, room.getOrder(), room.getPayment());
    }

    public static ProductDetail convertProductToProductDetail(ProductEntity product) {
        return new ProductDetail(product.getId(), product.getName(), product.getCode(), product.getPrice(), product.getGroupProduct().getCode());
    }
}
