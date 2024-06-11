package vn.sic.core.common.util;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;


/**
 * Json utils
 *
 * @author NinhNH
 */
@Slf4j
public final class JsonUtils {
    /**
     * Serialize JavaBean to Json String.
     *
     * @param input the JavaBean
     * @return a Json String
     */
    public static String serialize(Object input) {
        return new Gson().toJson(input);
    }

    /**
     * Deserialize Json String to JavaBean.
     *
     * @param input the Json String
     * @param type  the type of JavaBean that to return
     * @return JavaBean
     */
    public static <T> T deserialize(String input, Class<T> type) {
        return new Gson().fromJson(input, type);
    }
}
