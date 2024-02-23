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
public class ProductNotCreatedException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */

    private final Language language;
    private final IFriendlyMessageCode friendlyMessageCode;


    public ProductNotCreatedException(Language language, IFriendlyMessageCode friendlyMessageCode, String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode));
        this.language = language;
        this.friendlyMessageCode = friendlyMessageCode;
        log.error(
                "[ProductNotCreatedException}] -> message: {} developer message. {}",
                FriendlyMessageUtils.getFriendlyMessage(
                        language,
                        friendlyMessageCode
                )
                , message);
    }
}
