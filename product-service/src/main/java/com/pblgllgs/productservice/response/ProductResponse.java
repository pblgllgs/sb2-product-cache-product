package com.pblgllgs.productservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*
 *
 * @author pblgl
 * Created on 22-02-2024
 *
 */
@Data
@Builder
@AllArgsConstructor
public class ProductResponse {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private long productCreatedDate;
    private long productUpdatedDate;
}
