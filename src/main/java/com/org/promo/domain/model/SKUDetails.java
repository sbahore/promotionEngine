package com.org.promo.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SKUDetails {
    String skuId;
    Long quantity;
}
