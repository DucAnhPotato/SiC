package vn.sic.core.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date utils
 *
 * @author NinhNH
 */
@Slf4j
public final class DateUtils {
    /**
     * Format date
     *
     * @param input        date
     * @param outputFormat output format
     * @return string date formatted
     */
    public static String formatDate(Date input, String outputFormat) {
        try {
            return new SimpleDateFormat(outputFormat).format(input);
        } catch (NullPointerException | IllegalArgumentException ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Parse string to date
     *
     * @param input       string date
     * @param inputFormat input format
     * @return date
     */
    public static Date parseToDate(String input, String inputFormat) {
        try {
            return new SimpleDateFormat(inputFormat).parse(input);
        } catch (ParseException ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }
}
