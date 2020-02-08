package ltd.kafka.soonelu.plugins.entity.gson;

import ltd.kafka.soonelu.plugins.config.GsonConfig;
import ltd.kafka.soonelu.plugins.constant.Constant;

/**
 * 转换类型
 *
 * @author soonelu
 */
public enum ConvertLibrary {

    Gson, Jack, FastJson, LoganSquare, AutoValue, Other, Lombok;

    public static ConvertLibrary from() {
        return from(GsonConfig.getInstant().getAnnotationStr());
    }

    private static ConvertLibrary from(String annotation) {
        if (GsonConfig.getInstant().getAnnotationStr().equals(Constant.gsonAnnotation)) {
            return Gson;
        }
        if (GsonConfig.getInstant().getAnnotationStr().equals(Constant.fastAnnotation)) {
            return FastJson;
        }
        if (GsonConfig.getInstant().getAnnotationStr().equals(Constant.loganSquareAnnotation)) {
            return LoganSquare;
        }
        if (GsonConfig.getInstant().getAnnotationStr().equals(Constant.autoValueAnnotation)) {
            return AutoValue;
        }
        if (GsonConfig.getInstant().getAnnotationStr().equals(Constant.jackAnnotation)) {
            return Jack;
        }
        if (GsonConfig.getInstant().getAnnotationStr().equals(Constant.lombokAnnotation)) {
            return Lombok;
        }
        return Other;
    }
}
