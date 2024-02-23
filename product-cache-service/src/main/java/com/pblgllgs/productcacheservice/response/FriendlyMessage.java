package com.pblgllgs.productcacheservice.response;

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
public class FriendlyMessage {
    private String title;
    private String description;
    private String buttonPositive;
}
