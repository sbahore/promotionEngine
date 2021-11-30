
package com.org.promo.service;

import com.org.promo.domain.enumeration.PromotionType;
import com.org.promo.domain.model.PromotionModel;
import com.org.promo.domain.model.SKUPricingModel;
import com.org.promo.domain.model.SKURequestModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

class PromotionServiceTest {

	PromotionServiceImpl promotionService = new PromotionServiceImpl();

	@BeforeEach
	void setUp() {
		promotionService.skuList = getSkuList();
		promotionService.promotionList = getPromoList();
	}

	@Test
	void applyPromotion() {
	}

	@Test
	void testApplyPromotion() {
		promotionService.applyPromotion(getItemList());
	}

	private List<SKURequestModel> getItemList() {
		return List.of(SKURequestModel
						.builder()
						.skuId("A")
						.quantity(3L)
						.applied(Boolean.FALSE)
						.build(),
				SKURequestModel
						.builder()
						.skuId("B")
						.quantity(5L)
						.applied(Boolean.FALSE)
						.build(),
				SKURequestModel
						.builder()
						.skuId("C")
						.quantity(1L)
						.applied(Boolean.FALSE)
						.build(),
				SKURequestModel
						.builder()
						.skuId("D")
						.quantity(1L)
						.applied(Boolean.FALSE)
						.build());
	}

	private List<PromotionModel> getPromoList() {
		return List.of(
				new PromotionModel("3 of A's for 130", PromotionType.FIXED, Map.of("A", 3L),
						new BigDecimal(130)),
				new PromotionModel("2 of B's for 45", PromotionType.FIXED, Map.of("B", 2L),
						new BigDecimal(45)),
				new PromotionModel("C & D for 30", PromotionType.COMBO, Map.of("C", 1L, "D", 1L),
						new BigDecimal(30)));
	}

	private List<SKUPricingModel> getSkuList() {
		return List.of(new SKUPricingModel("A", new BigDecimal(50)), new SKUPricingModel("B", new BigDecimal(30)),
				new SKUPricingModel("C", new BigDecimal(20)), new SKUPricingModel("D", new BigDecimal(15)));
	}
}
