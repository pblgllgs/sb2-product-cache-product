package com.pblgllgs.productservice.exception.exceptions;
/*
 *
 * @author pblgl
 * Created on 22-02-2024
 *
 */

import com.pblgllgs.productservice.enums.Language;
import com.pblgllgs.productservice.exception.enums.IFriendlyMessageCode;
import com.pblgllgs.productservice.exception.utils.FriendlyMessageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ProductNotFoundException extends RuntimeException{

    private final Language language;
    private final IFriendlyMessageCode friendlyMessageCode;

    public ProductNotFoundException(Language language, IFriendlyMessageCode friendlyMessageCode,String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(language,friendlyMessageCode));
        this.language = language;
        this.friendlyMessageCode = friendlyMessageCode;
        log.error(
                "[ProductNotFoundException}] -> message: {} developer message. {}",
                FriendlyMessageUtils.getFriendlyMessage(
                        language,
                        friendlyMessageCode
                )
                , message);
    }


}
