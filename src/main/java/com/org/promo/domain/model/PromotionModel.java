
package com.org.promo.domain.model;

import com.org.promo.domain.enumeration.PromotionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class PromotionModel {
	private String promoName;
	private PromotionType promoType;
	private Map<String, Long> skuMap;
	private BigDecimal total;
}
