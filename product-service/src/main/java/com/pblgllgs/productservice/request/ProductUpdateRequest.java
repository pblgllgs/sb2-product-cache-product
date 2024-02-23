package com.pblgllgs.productservice.request;
/*
 *
 * @author pblgl
 * Created on 22-02-2024
 *
 */

import lombok.Data;

@Data
public class ProductUpdateRequest {

    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
}
