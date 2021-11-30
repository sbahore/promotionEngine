
package com.org.promo.service;

import com.org.promo.domain.model.PromotionModel;
import com.org.promo.domain.model.SKUPricingModel;
import com.org.promo.domain.model.SKURequestModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

	//We will fetch data from database in real-scanraio.
	List<PromotionModel> promotionList;
	List<SKUPricingModel> skuList;


	@Override
    public BigDecimal applyPromotion(List<SKURequestModel> items) {
        return null;
    }
}
