package com.pblgllgs.productcacheservice.controller;
/*
 *
 * @author pblgl
 * Created on 23-02-2024
 *
 */

import com.pblgllgs.productcacheservice.enums.Language;
import com.pblgllgs.productcacheservice.response.FriendlyMessage;
import com.pblgllgs.productcacheservice.response.InternalApiResponse;
import com.pblgllgs.productcacheservice.response.ProductResponse;
import com.pblgllgs.productcacheservice.respository.entity.Product;
import com.pblgllgs.productcacheservice.service.ProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/1.0/product-cache")
public class ProductCacheController {

    private final ProductRepositoryService productRepositoryService;

    @GetMapping("/{language}/get/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public InternalApiResponse<ProductResponse> getProductById(
            @PathVariable("language") Language language,
            @PathVariable("productId") Long productId
    ) {
        log.debug("[{}][getProduct] -> request: {}", this.getClass().getSimpleName(), productId);
        Product product = productRepositoryService.getProduct(language,productId);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @DeleteMapping("/{language}/remove-products")
    @ResponseStatus(HttpStatus.OK)
    public void removeProducts(@PathVariable("language") Language language){
        productRepositoryService.deleteProducts(language);
    }

    private ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreatedDate(product.getProductCreatedDate())
                .productUpdatedDate(product.getProductUpdatedDate())
                .build();
    }
}
