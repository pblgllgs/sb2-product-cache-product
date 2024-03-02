package com.pblgllgs.productservice.exception.utils;

import com.pblgllgs.productservice.enums.Language;
import com.pblgllgs.productservice.exception.enums.IFriendlyMessageCode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/*
 *
 * @author pblgl
 * Created on 22-02-2024
 *
 */
@Slf4j
@UtilityClass
public class FriendlyMessageUtils {
    private static final String RESOURCE_BUNDLE_NAME = "FriendlyMessage";

    public static final String SPECIAL_CHARACTER = "__";

    public static String getFriendlyMessage(Language language, IFriendlyMessageCode messageCode) {
        String messageKey = null;

        try {
            Locale locale = new Locale(language.name());
            ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
            messageKey = messageCode.getClass().getSimpleName() + SPECIAL_CHARACTER + messageCode;
            return resourceBundle.getString(messageKey);
        } catch (MissingResourceException e) {
            log.error("Friendly message not found for the key: {}", messageKey);
            return null;
        }
    }
}
