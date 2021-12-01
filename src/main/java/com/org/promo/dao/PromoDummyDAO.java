package com.org.promo.dao;

import com.org.promo.domain.enumeration.PromotionType;
import com.org.promo.domain.model.PromotionModel;
import com.org.promo.domain.model.SKUPricingModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PromoDummyDAO {

    public static List<PromotionModel> getPromoList() {
        return List.of(
                new PromotionModel("3 of A's for 130", PromotionType.FIXED, Map.of("A", 3L),
                        new BigDecimal(130)),
                new PromotionModel("2 of B's for 45", PromotionType.FIXED, Map.of("B", 2L),
                        new BigDecimal(45)),
                new PromotionModel("C & D for 30", PromotionType.COMBO, Map.of("C", 1L, "D", 1L),
                        new BigDecimal(30)));
    }

    public static List<SKUPricingModel> getSkuList() {
        return List.of(new SKUPricingModel("A", new BigDecimal(50)), new SKUPricingModel("B", new BigDecimal(30)),
                new SKUPricingModel("C", new BigDecimal(20)), new SKUPricingModel("D", new BigDecimal(15)));
    }
}
