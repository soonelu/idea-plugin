package ltd.kafka.soonelu.plugins.entity.gen;

import com.intellij.database.model.DasColumn;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * 列信息
 *
 * @author soonelu
 */
@Data
public class ColumnInfo {
    @ApiModelProperty(value = "原始对象")
    private DasColumn obj;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "注释")
    private String comment;
    @ApiModelProperty(value = "全类型")
    private String type;
    @ApiModelProperty(value = "短类型")
    private String shortType;
    @ApiModelProperty(value = "标记是否为自定义附加列")
    private boolean custom;
    @ApiModelProperty(value = "扩展数据")
    private Map<String, Object> ext;
}
