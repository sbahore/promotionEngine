
package com.org.promo.service;

import com.org.promo.domain.model.SKURequestModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface PromotionService {

    public BigDecimal applyPromotion(List<SKURequestModel> items);

}
