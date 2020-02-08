package ltd.kafka.soonelu.plugins.entity.gen;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 全局配置分组
 *
 * @author soonelu
 */
@Data
public class GlobalConfigGroup implements AbstractGroup<GlobalConfig> {
    @ApiModelProperty(value = "分组名称")
    private String name;
    @ApiModelProperty(value = "元素对象集合")
    private List<GlobalConfig> elementList;
}
