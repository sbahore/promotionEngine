package com.org.promo.controller;

import com.org.promo.domain.model.PromotionRequest;
import com.org.promo.mapper.SKUMapper;
import com.org.promo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@RestController
public class PromotionController {
    @Autowired
    PromotionService promotionService;

    @PostMapping(value = "/applyPromo")
    public BigDecimal applyPromotion(@RequestBody PromotionRequest request) {
        return promotionService.applyPromotion(request.getRequest()
                                                      .stream()
                                                      .map(SKUMapper::apply)
                                                      .collect(Collectors.toList()));
    }
}
