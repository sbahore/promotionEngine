
package com.org.promo.service;

import com.org.promo.domain.enumeration.PromotionType;
import com.org.promo.domain.model.PromotionModel;
import com.org.promo.domain.model.SKUPricingModel;
import com.org.promo.domain.model.SKURequestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class PromotionServiceTest {

	PromotionServiceImpl promotionService = new PromotionServiceImpl();

	@BeforeEach
	void setUp() {
		promotionService.skuList = getSkuList();
		promotionService.promotionList = getPromoList();
	}

	@Test
	void testApplyPromotion() {
		BigDecimal result= promotionService.applyPromotion(getItemList(Map.of("A", 3L,"B", 2L,"C", 1L,"D",1L)));
		Assertions.assertEquals(result, BigDecimal.valueOf(205L));
		BigDecimal result1= promotionService.applyPromotion(getItemList(Map.of("A", 1L,"B", 1L,"C", 1L)));
		Assertions.assertEquals(result1, BigDecimal.valueOf(100L));
		BigDecimal result2= promotionService.applyPromotion(getItemList(Map.of("A", 5L,"B", 5L,"C", 1L)));
		Assertions.assertEquals(result2, BigDecimal.valueOf(370L));
		BigDecimal result3= promotionService.applyPromotion(getItemList(Map.of("A", 3L,"B", 5L,"C", 1L,"D",1L)));
		Assertions.assertEquals(result3, BigDecimal.valueOf(280L));
	}

	private List<SKURequestModel> getItemList(Map<String, Long> request) {
		return request.entrySet()
					  .stream()
					  .map(itemRequest -> SKURequestModel.builder()
														 .skuId(itemRequest.getKey())
														 .quantity(itemRequest.getValue())
														 .applied(Boolean.FALSE)
														 .build())
					  .collect(Collectors.toList());

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
