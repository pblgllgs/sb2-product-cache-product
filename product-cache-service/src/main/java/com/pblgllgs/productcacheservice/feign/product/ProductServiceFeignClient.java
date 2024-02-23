package com.pblgllgs.productcacheservice.feign.product;
/*
 *
 * @author pblgl
 * Created on 22-02-2024
 *
 */

import com.pblgllgs.productcacheservice.enums.Language;
import com.pblgllgs.productcacheservice.response.InternalApiResponse;
import com.pblgllgs.productcacheservice.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("${feign.product-service.name}")
public interface ProductServiceFeignClient {

    @GetMapping(value = "/api/1.0/product/{language}/get/{productId}")
    InternalApiResponse<ProductResponse> getProductById(
            @PathVariable("language") Language language,
            @PathVariable("productId") Long productId);


}
