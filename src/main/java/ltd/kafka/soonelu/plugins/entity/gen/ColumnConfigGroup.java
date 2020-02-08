package ltd.kafka.soonelu.plugins.entity.gen;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 列配置分组
 *
 * @author soonelu
 */
@Data
public class ColumnConfigGroup implements AbstractGroup<ColumnConfig> {
    @ApiModelProperty(value = "分组名称")
    private String name;
    @ApiModelProperty(value = "元素对象")
    private List<ColumnConfig> elementList;
}
