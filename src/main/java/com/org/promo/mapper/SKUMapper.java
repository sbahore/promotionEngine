package com.org.promo.mapper;

import com.org.promo.domain.model.SKUDetails;
import com.org.promo.domain.model.SKURequestModel;

public class SKUMapper {
    public static SKURequestModel apply(SKUDetails request) {
        return SKURequestModel
                .builder()
                .applied(Boolean.FALSE)
                .quantity(request.getQuantity())
                .skuId(request.getSkuId())
                .build();
    }
}
