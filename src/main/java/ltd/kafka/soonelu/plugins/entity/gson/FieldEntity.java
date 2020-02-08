package ltd.kafka.soonelu.plugins.entity.gson;

import ltd.kafka.soonelu.plugins.third.CellProvider;
import ltd.kafka.soonelu.plugins.third.Selector;
import ltd.kafka.soonelu.plugins.utils.gson.CheckUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.util.TextUtils;
import org.json.JSONObject;

/**
 * @author soonelu
 */
public class FieldEntity implements Selector, CellProvider {

    @Setter
    protected String key;
    @ApiModelProperty(value = "类型")
    @Getter
    @Setter
    protected String type;
    @ApiModelProperty(value = "生成的名字")
    @Getter
    protected String fieldName;
    @ApiModelProperty(value = "值")
    @Getter
    protected String value;
    @ApiModelProperty(value = "依赖的实体类")
    @Getter
    @Setter
    protected ClassEntity targetClass;
    protected boolean generate = true;


    public boolean isGenerate() {
        return generate;
    }

    public void setGenerate(boolean generate) {
        this.generate = generate;
    }

    public String getGenerateFieldName() {
        return CheckUtil.getInstant().handleArg(fieldName);
    }

    public void setFieldName(String fieldName) {
        if (TextUtils.isEmpty(fieldName)) {
            return;
        }
        this.fieldName = fieldName;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String getRealType() {
        if (targetClass != null) {
            return targetClass.getClassName();
        }
        return type;
    }

    public String getBriefType() {
        if (targetClass != null) {
            return targetClass.getClassName();
        }
        int i = type.indexOf(".");
        if (i > 0) {
            return type.substring(i);
        }
        return type;
    }

    public String getFullNameType() {
        if (targetClass != null) {
            return targetClass.getQualifiedName();
        }
        return type;
    }

    public void checkAndSetType(String text) {
        if (type != null && CheckUtil.getInstant().checkSimpleType(type.trim())) {
            //基本类型
            if (CheckUtil.getInstant().checkSimpleType(text.trim())) {
                this.type = text.trim();
            }
        } else {
            //实体类:
            if (targetClass != null && !targetClass.isLock()) {
                if (!TextUtils.isEmpty(text)) {
                    targetClass.setClassName(text);
                }
            }
        }
    }

    public String getKey() {
        return key;
    }

    @Override
    public void setSelect(boolean select) {
        setGenerate(select);
    }

    public boolean isSameType(Object o) {
        if (o instanceof JSONObject) {
            if (targetClass != null) {
                return targetClass.isSame((JSONObject) o);
            }
        } else {
            return DataType.isSameDataType(DataType.typeOfString(type), DataType.typeOfObject(o));
        }
        return false;
    }

    @Override
    public String getCellTitle(int index) {
        String result = "";
        switch (index) {
            case 0:
                result = getKey();
                break;
            case 1:
                result = getValue();
                break;
            case 2:
                result = getBriefType();
                break;
            case 3:
                result = getFieldName();
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public void setValueAt(int column, String text) {
        switch (column) {
            case 2:
                checkAndSetType(text);
                break;
            case 3:
                if (CheckUtil.getInstant().containsDeclareFieldName(text)) {
                    return;
                }
                CheckUtil.getInstant().removeDeclareFieldName(getFieldName());
                setFieldName(text);
                break;
            default:
                break;
        }
    }
}
