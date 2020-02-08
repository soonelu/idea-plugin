package ltd.kafka.soonelu.plugins.entity.gen;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 类型映射分组
 *
 * @author soonelu
 */
@Data
public class TypeMapperGroup implements AbstractGroup<TypeMapper> {
    @ApiModelProperty(value = "分组名称")
    private String name;
    @ApiModelProperty(value = "元素对象")
    private List<TypeMapper> elementList;
}
