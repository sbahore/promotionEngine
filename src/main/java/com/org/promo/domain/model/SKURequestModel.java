
package com.org.promo.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SKURequestModel {
	String skuId;
	Long quantity;
	Boolean applied;
}
