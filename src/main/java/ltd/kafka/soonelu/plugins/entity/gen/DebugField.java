package ltd.kafka.soonelu.plugins.entity.gen;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 调试字段实体类
 *
 * @author soonelu
 */
@Data
public class DebugField {
    @ApiModelProperty(value = "字段名")
    private String name;
    @ApiModelProperty(value = "字段类型")
    private Class<?> type;
    @ApiModelProperty(value = "字段值")
    private String value;
}
