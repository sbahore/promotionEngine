
package com.org.promo.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionRequest {
    List<SKUDetails> request;
}
