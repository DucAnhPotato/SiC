package vn.sic.core.common.util;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Utility class for URL encoding operations.
 *
 * @author AnDT
 * @version 1.0
 */
public final class UrlUtils {
    /**
     * Encodes the key-value pairs of a map into a URL-encoded string.
     *
     * @param map the map containing the key-value pairs to be encoded
     * @return the URL-encoded string
     */
    public static String urlEncode(Map<String, String> map) {
        StringBuilder reString = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            reString.append(key).append("=").append(value).append("&");
        }
        reString = new StringBuilder(reString.substring(0, reString.length() - 1));
        try {
            reString = new StringBuilder(URLEncoder.encode(reString.toString(), StandardCharsets.UTF_8));
        } catch (Exception e) {
            return null;
        }

        reString = new StringBuilder(reString.toString().replace("%3D", "=").replace("%26", "&"));
        return reString.toString();
    }

    /**
     * parse the httpRequest into an url string
     *
     * @param httpRequest the HttpServletRequest to be parsed
     * @return the http URL string
     */
    public static String urlParseString(HttpServletRequest httpRequest) {
        StringBuilder url = new StringBuilder();
        String requestURL = String.valueOf(httpRequest.getRequestURL());
        String requestParam = httpRequest.getQueryString();

        url.append(requestURL);
        if (requestParam != null) {
            url.append("?").append(requestParam);
        }

        return url.toString();
    }
}
