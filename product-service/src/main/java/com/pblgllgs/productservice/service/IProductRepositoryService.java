package com.pblgllgs.productservice.service;

import com.pblgllgs.productservice.enums.Language;
import com.pblgllgs.productservice.repository.entity.Product;
import com.pblgllgs.productservice.request.ProductCreateRequest;
import com.pblgllgs.productservice.request.ProductUpdateRequest;

import java.util.List;

public interface IProductRepositoryService {

    Product createProduct(Language languaje, ProductCreateRequest productCreateRequest);
    Product getProduct(Language languaje, Long productId);
    List<Product> getProducts(Language languaje);
    Product updatedProduct(Language languaje, Long productId, ProductUpdateRequest productUpdateRequest);

    Product deleteProduct(Language languaje, Long productId);
}
