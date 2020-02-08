package ltd.kafka.soonelu.plugins.entity.gen;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 调试方法实体类
 *
 * @author soonelu
 */
@Data
public class DebugMethod {
    @ApiModelProperty(value = "方法名")
    private String name;
    @ApiModelProperty(value = "方法描述")
    private String desc;
    @ApiModelProperty(value = "执行方法得到的值")
    private Object value;
}
