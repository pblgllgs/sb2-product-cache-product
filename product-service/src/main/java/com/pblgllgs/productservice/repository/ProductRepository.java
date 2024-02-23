package com.pblgllgs.productservice.repository;

import com.pblgllgs.productservice.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getByProductIdAndDeletedFalse(Long productId);
    List<Product> getAllByDeletedFalse();
}
