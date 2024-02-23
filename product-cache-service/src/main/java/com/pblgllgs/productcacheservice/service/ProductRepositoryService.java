package com.pblgllgs.productcacheservice.service;

import com.pblgllgs.productcacheservice.enums.Language;
import com.pblgllgs.productcacheservice.respository.entity.Product;

public interface ProductRepositoryService {

    Product getProduct(Language language, Long productId);
    void deleteProducts(Language language);
}
