package com.pblgllgs.productcacheservice.respository;

import com.pblgllgs.productcacheservice.respository.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
}
