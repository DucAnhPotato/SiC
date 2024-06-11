package vn.sic.sample.api.reponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;

/**
 * The Translator class performs message translation
 *
 * @author NinhNH
 */
@Component
public class Translator {
    private static MessageSource messageSource;

    @Autowired
    Translator(MessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    public static String toLocale(String msgCode, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, args, locale);
    }

    public static String toLocale(String msgCode) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, null, locale);
    }

    public static String getLanguage() {
        Locale locale = LocaleContextHolder.getLocale();
        if (Arrays.binarySearch(Locale.getISOLanguages(), locale.getLanguage()) > 0) {
            return locale.getLanguage();
        } else {
            return "en";
        }
    }
}
