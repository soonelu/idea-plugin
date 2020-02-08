package ltd.kafka.soonelu.plugins.entity.gen;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类型隐射信息
 *
 * @author soonelu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeMapper {
    @ApiModelProperty(value = "列类型")
    private String columnType;
    @ApiModelProperty(value = "java类型")
    private String javaType;
}
