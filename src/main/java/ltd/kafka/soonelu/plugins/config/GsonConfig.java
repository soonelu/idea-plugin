package ltd.kafka.soonelu.plugins.config;

import com.intellij.ide.util.PropertiesComponent;
import ltd.kafka.soonelu.plugins.constant.Constant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author soonelu
 */
@NoArgsConstructor
@Data
public class GsonConfig {

    private static GsonConfig gsonConfig;
    private boolean fieldPrivateMode = true;
    private boolean generateComments = true;
    private boolean useSerializedName = false;
    private boolean objectFromData = false;
    private boolean objectFromData1 = false;
    private boolean arrayFromData = false;
    private boolean arrayFromData1 = false;
    private boolean reuseEntity = false;
    @ApiModelProperty(value = "处女座模式")
    private boolean virgoMode = true;
    private boolean useFieldNamePrefix = false;
    private boolean splitGenerate = false;
    private String objectFromDataStr;
    private String objectFromDataStr1;
    private String arrayFromDataStr;
    private String arrayFromData1Str;
    @ApiModelProperty(value = "注解语句")
    private String annotationStr;
    @ApiModelProperty(value = "字段前缀")
    private String filedNamePreFixStr;
    @ApiModelProperty(value = "创建实体类的包名.")
    private String entityPackName;
    private String suffixStr;
    @ApiModelProperty(value = "错误次数,前两次提醒哪里查看错误日志.")
    private int errorCount;
    @ApiModelProperty(value = "是否使用包装类来替代基本类型")
    private boolean useWrapperClass;

    public void save() {

        PropertiesComponent.getInstance().setValue("fieldPrivateMode", "" + isFieldPrivateMode());
        PropertiesComponent.getInstance().setValue("useSerializedName", isUseSerializedName() + "");
        PropertiesComponent.getInstance().setValue("objectFromData", objectFromData + "");
        PropertiesComponent.getInstance().setValue("objectFromData1", objectFromData1 + "");
        PropertiesComponent.getInstance().setValue("arrayFromData", arrayFromData + "");
        PropertiesComponent.getInstance().setValue("arrayFromData1", arrayFromData1 + "");
        PropertiesComponent.getInstance().setValue("objectFromDataStr", objectFromDataStr + "");
        PropertiesComponent.getInstance().setValue("objectFromDataStr1", objectFromDataStr1 + "");
        PropertiesComponent.getInstance().setValue("arrayFromData1Str", arrayFromData1Str + "");
        PropertiesComponent.getInstance().setValue("suffixStr", suffixStr + "");
        PropertiesComponent.getInstance().setValue("reuseEntity", reuseEntity + "");
        PropertiesComponent.getInstance().setValue("virgoMode", virgoMode + "");
        PropertiesComponent.getInstance().setValue("filedNamePreFixStr", filedNamePreFixStr + "");
        PropertiesComponent.getInstance().setValue("annotationStr", annotationStr + "");
        PropertiesComponent.getInstance().setValue("errorCount", errorCount + "");
        PropertiesComponent.getInstance().setValue("entityPackName", entityPackName + "");
        PropertiesComponent.getInstance().setValue("useFieldNamePrefix", useFieldNamePrefix + "");
        PropertiesComponent.getInstance().setValue("generateComments", generateComments + "");
        PropertiesComponent.getInstance().setValue("splitGenerate", splitGenerate + "");
        PropertiesComponent.getInstance().setValue("useWrapperClass", useWrapperClass + "");

    }

    public static GsonConfig getInstant() {

        if (gsonConfig == null) {
            gsonConfig = new GsonConfig();
            gsonConfig.setFieldPrivateMode(PropertiesComponent.getInstance().getBoolean("fieldPrivateMode", true));
            gsonConfig.setUseSerializedName(PropertiesComponent.getInstance().getBoolean("useSerializedName", false));
            gsonConfig.setObjectFromData(PropertiesComponent.getInstance().getBoolean("objectFromData", false));
            gsonConfig.setObjectFromData1(PropertiesComponent.getInstance().getBoolean("objectFromData1", false));
            gsonConfig.setArrayFromData(PropertiesComponent.getInstance().getBoolean("arrayFromData", false));
            gsonConfig.setArrayFromData1(PropertiesComponent.getInstance().getBoolean("arrayFromData1", false));
            gsonConfig.setSuffixStr(PropertiesComponent.getInstance().getValue("suffixStr", "Bean"));
            gsonConfig.setReuseEntity(PropertiesComponent.getInstance().getBoolean("reuseEntity", false));
            gsonConfig.setObjectFromDataStr(PropertiesComponent.getInstance().getValue("objectFromDataStr",
                    Constant.objectFromObject));
            gsonConfig.setObjectFromDataStr1(PropertiesComponent.getInstance().getValue("objectFromDataStr1",
                    Constant.objectFromObject1));
            gsonConfig.setArrayFromDataStr(PropertiesComponent.getInstance().getValue("arrayFromDataStr",
                    Constant.arrayFromData));
            gsonConfig.setArrayFromData1Str(PropertiesComponent.getInstance().getValue("arrayFromData1Str",
                    Constant.arrayFromData1));
            gsonConfig.setAnnotationStr(PropertiesComponent.getInstance().getValue("annotationStr",
                    Constant.gsonAnnotation));
            gsonConfig.setEntityPackName(PropertiesComponent.getInstance().getValue("entityPackName"));
            gsonConfig.setFiledNamePreFixStr(PropertiesComponent.getInstance().getValue("filedNamePreFixStr"));
            gsonConfig.setErrorCount(PropertiesComponent.getInstance().getInt("errorCount", 0));
            gsonConfig.setVirgoMode(PropertiesComponent.getInstance().getBoolean("virgoMode", true));
            gsonConfig.setUseFieldNamePrefix(PropertiesComponent.getInstance().getBoolean("useFieldNamePrefix", false));
            gsonConfig.setGenerateComments(PropertiesComponent.getInstance().getBoolean("generateComments", true));
            gsonConfig.setSplitGenerate(PropertiesComponent.getInstance().getBoolean("splitGenerate", false));
            gsonConfig.setUseWrapperClass(PropertiesComponent.getInstance().getBoolean("useWrapperClass", false));

        }
        return gsonConfig;
    }

    public String geFullNameAnnotation() {

        if (annotationStr.equals(Constant.gsonAnnotation)) {
            return Constant.gsonFullNameAnnotation;
        }
        if (annotationStr.equals(Constant.jackAnnotation)) {
            return Constant.jackFullNameAnnotation;
        }
        if (annotationStr.equals(Constant.fastAnnotation)) {
            return Constant.fastFullNameAnnotation;
        }
        if (annotationStr.equals(Constant.loganSquareAnnotation)) {
            return Constant.loganSquareFullNameAnnotation;
        }
        return annotationStr.replaceAll("\\(", "(").replaceAll("\\)", ")").replaceAll("\\s\\*", "");
    }

    public void saveObjectFromDataStr(String objectFromDataStr) {
        this.objectFromDataStr = objectFromDataStr;
        PropertiesComponent.getInstance().setValue("objectFromDataStr", objectFromDataStr + "");
    }

    public void saveObjectFromDataStr1(String objectFromDataStr1) {
        this.objectFromDataStr1 = objectFromDataStr1;
        PropertiesComponent.getInstance().setValue("objectFromDataStr1", objectFromDataStr1 + "");
    }

    public void saveArrayFromDataStr(String arrayFromDataStr) {
        this.arrayFromDataStr = arrayFromDataStr;
        PropertiesComponent.getInstance().setValue("arrayFromDataStr", arrayFromDataStr + "");
    }

    public void saveArrayFromData1Str(String arrayFromData1Str) {
        this.arrayFromData1Str = arrayFromData1Str;
        PropertiesComponent.getInstance().setValue("arrayFromData1Str", arrayFromData1Str + "");
    }

    public boolean isToastError() {
        if (GsonConfig.getInstant().getErrorCount() < 3) {
            GsonConfig.getInstant().setErrorCount(GsonConfig.getInstant().getErrorCount() + 1);
            GsonConfig.getInstant().save();
            return true;
        }
        return false;
    }

    public boolean isSplitGenerate() {
        return splitGenerate;
    }

    public void setSplitGenerate(boolean splitGenerate) {
        this.splitGenerate = splitGenerate;
    }


    public void saveCurrentPackPath(String entityPackName) {
        if (entityPackName == null) {
            return;
        }
        setEntityPackName(entityPackName + ".");
        save();
    }

}
