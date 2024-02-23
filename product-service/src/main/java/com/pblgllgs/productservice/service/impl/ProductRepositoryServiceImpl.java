package com.pblgllgs.productservice.service.impl;
/*
 *
 * @author pblgl
 * Created on 22-02-2024
 *
 */

import com.pblgllgs.productservice.enums.Language;
import com.pblgllgs.productservice.exception.enums.FriendlyMessageCodes;
import com.pblgllgs.productservice.exception.exceptions.ProductAlreadyDeletedException;
import com.pblgllgs.productservice.exception.exceptions.ProductNotCreatedException;
import com.pblgllgs.productservice.exception.exceptions.ProductNotFoundException;
import com.pblgllgs.productservice.repository.ProductRepository;
import com.pblgllgs.productservice.repository.entity.Product;
import com.pblgllgs.productservice.request.ProductCreateRequest;
import com.pblgllgs.productservice.request.ProductUpdateRequest;
import com.pblgllgs.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryServiceImpl implements IProductRepositoryService {
    private final ProductRepository productRepository;


    @Override
    public Product createProduct(Language language, ProductCreateRequest productCreateRequest) {
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        try {
            Product product = Product.builder()
                    .productName(productCreateRequest.getProductName())
                    .price(productCreateRequest.getPrice())
                    .quantity(productCreateRequest.getQuantity())
                    .deleted(false)
                    .build();
            Product productResponse = productRepository.save(product);
            log.debug("[{}][createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
            return productResponse;
        } catch (Exception e) {
            throw new ProductNotCreatedException(
                    language,
                    FriendlyMessageCodes.PRODUCT_NOT_CREATED_EXCEPTION,
                    "product request: " + productCreateRequest.toString()
            );
        }
    }

    @Override
    public Product getProduct(Language language, Long productId) {
        log.debug("[{}][getProduct] -> request: {}", this.getClass().getSimpleName(), productId);
        Product product = productRepository.getByProductIdAndDeletedFalse(productId);
        if (Objects.isNull(product)) {
            throw new ProductNotFoundException(
                    language,
                    FriendlyMessageCodes.PRODUCT_NOT_FOUNT_EXCEPTION,
                    "Product not found for product id: " + productId
            );
        }
        log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), product);
        return product;
    }

    @Override
    public List<Product> getProducts(Language language) {
        log.debug("[{}][getProducts] -> request: {}", this.getClass().getSimpleName());
        List<Product> products = productRepository.getAllByDeletedFalse();
        log.debug("[{}][getProducts] -> response: {}", this.getClass().getSimpleName(), products);
        return products;
    }

    @Override
    public Product updatedProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest) {
        log.debug("[{}][updateProduct] -> request: {}", this.getClass().getSimpleName(), productUpdateRequest);
        try {
            Product product = getProduct(language,productId);
            product.setProductName(productUpdateRequest.getProductName());
            product.setQuantity(productUpdateRequest.getQuantity());
            product.setPrice(productUpdateRequest.getPrice());
            product.setProductCreatedDate(product.getProductCreatedDate());
            product.setProductUpdatedDate(new Date());
            Product productResponse = productRepository.save(product);
            log.debug("[{}][updateProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
            return productResponse;
        } catch (Exception e) {
            throw new ProductNotFoundException(
                    language,
                    FriendlyMessageCodes.PRODUCT_NOT_FOUNT_EXCEPTION,
                    "Product not found for product id: " + productId
            );
        }
    }

    @Override
    public Product deleteProduct(Language language, Long productId) {
        log.debug("[{}][deleteProduct] -> request: {}", this.getClass().getSimpleName(), productId);
        Product product;
        try {
            product = getProduct(language,productId);
            product.setDeleted(true);
            product.setProductUpdatedDate(new Date());
            Product productResponse = productRepository.save(product);
            log.debug("[{}][deleteProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
            return productResponse;
        } catch (Exception e) {
            throw new ProductAlreadyDeletedException(
                    language,
                    FriendlyMessageCodes.PRODUCT_ALREADY_DELETED,
                    "Product already deleted with product id: " + productId
            );
        }
    }
}
