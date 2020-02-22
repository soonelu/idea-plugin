package ltd.kafka.soonelu.plugins.utils.gen;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author soonelu
 */
public class JsonUtils {
    /**
     * 格式化json串
     *
     * @param source 源
     * @return 格式化后
     */
    public static String formatJson(String source) {
        if (null == source || 0 == source.length()) {
            return "{}";
        }
        String json = source.trim();
        if (json.startsWith("{")) {
            JSONObject jsonObject = new JSONObject(json);
            json = jsonObject.toString(4);
        } else if (json.startsWith("[")) {
            JSONArray jsonArray = new JSONArray(json);
            json = jsonArray.toString(4);
        }
        return json;
    }
}
