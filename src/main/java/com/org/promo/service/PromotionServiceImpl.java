
package com.org.promo.service;

import com.org.promo.dao.PromoDummyDAO;
import com.org.promo.domain.enumeration.PromotionType;
import com.org.promo.domain.model.PromotionModel;
import com.org.promo.domain.model.SKUPricingModel;
import com.org.promo.domain.model.SKURequestModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService {

	//We will fetch data from database in real-scanraio.
	List<PromotionModel> promotionList= PromoDummyDAO.getPromoList();
	List<SKUPricingModel> skuList= PromoDummyDAO.getSkuList();


	@Override
	public BigDecimal applyPromotion(List<SKURequestModel> items) {
		return Optional.ofNullable(items)
					   .map(itemsList -> itemsList.stream()
												  .map(item -> processPromotion(item, itemsList))
												  .reduce(BigDecimal::add)
												  .orElse(new BigDecimal(0)))
					   .orElse(new BigDecimal(0));
	}


	public BigDecimal processPromotion(SKURequestModel item, List<SKURequestModel> items) {
		return promotionList.stream()
							.filter(promo -> promo.getSkuMap()
												  .containsKey(item.getSkuId()))
							.map(promotion -> calculatePromo(item, items, promotion))
							.reduce(BigDecimal::add)
							.orElseGet(() -> skuList.stream()
													.filter(sku -> item.getSkuId()
																	   .equals(sku.getSkuId()))
													.map(SKUPricingModel::getPrice)
													.map(price -> price.multiply(BigDecimal.valueOf(item.getQuantity())))
													.findFirst()
													.orElse(new BigDecimal(0)));
	}

	public BigDecimal calculatePromo(SKURequestModel item, List<SKURequestModel> items, PromotionModel promo) {
		Long skuID = promo.getSkuMap()
						  .get(item.getSkuId());
		BigDecimal skuPrice = getSkuPrice(item);

		BigDecimal sum = new BigDecimal(0);
		if (item.getApplied()) {
			return new BigDecimal(0);
		}
		if (PromotionType.FIXED.equals(promo.getPromoType())) {
			Long applicablePromoQty = item.getQuantity() / skuID;
			Long exceedingQty = item.getQuantity() % skuID;
			sum = promo.getTotal()
					   .multiply(BigDecimal.valueOf(applicablePromoQty))
					   .add(skuPrice.multiply(new BigDecimal(exceedingQty)));
		}

		else if (PromotionType.COMBO.equals(promo.getPromoType())) {
			if (items.stream()
					 .map(SKURequestModel::getSkuId)
					 .collect(Collectors.toList())
					 .containsAll(promo.getSkuMap()
									   .keySet())) {

				Long applicablePromoQty = items.stream()
											   .filter(skuRequest -> promo.getSkuMap()
																		  .containsKey(skuRequest.getSkuId()))
											   .map(this::updateStatus)
											   .mapToLong(SKURequestModel::getQuantity)
											   .min()
											   .orElse(0L);

				BigDecimal exceedingSum = items.stream()
											   .filter(skuRequest -> promo.getSkuMap()
																		  .containsKey(skuRequest.getSkuId()))
											   .map(sku -> getSkuPrice(sku).multiply(BigDecimal
													   .valueOf((sku.getQuantity() - applicablePromoQty))))
											   .reduce(BigDecimal::add)
											   .orElse(new BigDecimal(0));


				sum = promo.getTotal()
						   .multiply(BigDecimal.valueOf(applicablePromoQty))
						   .add(skuPrice.multiply(exceedingSum));
			} else {
				sum = getSkuPrice(item).multiply(BigDecimal.valueOf(item.getQuantity()));
			}
		}
		return sum;
	}

	private SKURequestModel updateStatus(SKURequestModel skuRequest) {
		skuRequest.setApplied(Boolean.TRUE);
		return skuRequest;
	}

	private BigDecimal getSkuPrice(SKURequestModel item) {
		return skuList.stream()
					  .filter(sku -> item.getSkuId()
										 .equals(sku.getSkuId()))
					  .map(SKUPricingModel::getPrice)
					  .findFirst()
					  .orElse(new BigDecimal(0));
	}
}
