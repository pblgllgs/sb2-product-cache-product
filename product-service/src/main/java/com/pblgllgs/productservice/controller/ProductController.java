package com.pblgllgs.productservice.controller;
/*
 *
 * @author pblgl
 * Created on 22-02-2024
 *
 */

import com.pblgllgs.productservice.enums.Language;
import com.pblgllgs.productservice.exception.enums.FriendlyMessageCodes;
import com.pblgllgs.productservice.exception.utils.FriendlyMessageUtils;
import com.pblgllgs.productservice.repository.entity.Product;
import com.pblgllgs.productservice.request.ProductCreateRequest;
import com.pblgllgs.productservice.request.ProductUpdateRequest;
import com.pblgllgs.productservice.response.FriendlyMessage;
import com.pblgllgs.productservice.response.InternalApiResponse;
import com.pblgllgs.productservice.response.ProductResponse;
import com.pblgllgs.productservice.service.IProductRepositoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/1.0/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductRepositoryService iProductRepositoryService;

    @PostMapping("/{language}/create")
    @ResponseStatus(HttpStatus.CREATED)
    public InternalApiResponse<ProductResponse> createProduct(
            @PathVariable("language") Language language,
            @RequestBody ProductCreateRequest productCreateRequest
    ) {
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        Product product = iProductRepositoryService.createProduct(language, productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)

                .payload(productResponse)
                .build();
    }

    @GetMapping("/{language}/get/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public InternalApiResponse<ProductResponse> getProductById(
            @PathVariable("language") Language language,
            @PathVariable("productId") Long productId
    ) {
        log.debug("[{}][getProduct] -> request: {}", this.getClass().getSimpleName(), productId);
        Product product = iProductRepositoryService.getProduct(language,productId);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.OPERATION_SUCCESSFULLY_COMPLETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @PutMapping("/{language}/update/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public InternalApiResponse<ProductResponse> updateProduct(
            @PathVariable("language") Language language,
            @PathVariable("productId") Long productId,
            @RequestBody ProductUpdateRequest productUpdateRequest
    ) {
        log.debug("[{}][updateProduct] -> request: {}", this.getClass().getSimpleName(), productId);
        Product product = iProductRepositoryService.updatedProduct(language,productId, productUpdateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][updateProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_UPDATED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ApiOperation(value = "This endpoint get all products")
    @GetMapping("/{language}/products")
    @ResponseStatus(HttpStatus.OK)
    public InternalApiResponse<List<ProductResponse>> getProductById(
            @PathVariable("language") Language language
    ) {
        log.debug("[{}][getProducts] -> request: {}", this.getClass().getSimpleName());
        List<Product> products = iProductRepositoryService.getProducts(language);
        List<ProductResponse> productsResponse = products.stream().map(this::convertProductResponse).collect(Collectors.toList());
        log.debug("[{}][getProducts] -> response: {}", this.getClass().getSimpleName(),productsResponse );
        return InternalApiResponse.<List<ProductResponse>>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.OPERATION_SUCCESSFULLY_COMPLETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productsResponse)
                .build();
    }

    @ApiOperation(value = "This endpoint delete product by id")
    @DeleteMapping("/{language}/delete/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public InternalApiResponse<ProductResponse> deleteProductById(
            @PathVariable("language") Language language,
            @PathVariable("productId") Long productId
    ) {
        log.debug("[{}][deleteProduct] -> request: {}", this.getClass().getSimpleName(),productId);
        Product product = iProductRepositoryService.deleteProduct(language,productId);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][deleteProduct] -> response: {}", this.getClass().getSimpleName(),productResponse );
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_DELETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    private ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreatedDate(product.getProductCreatedDate().getTime())
                .productUpdatedDate(product.getProductUpdatedDate().getTime())
                .build();
    }
}
