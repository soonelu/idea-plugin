package ltd.kafka.soonelu.plugins.entity.gen;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltd.kafka.soonelu.plugins.ui.gen.base.Item;

/**
 * 全局配置实体类
 *
 * @author soonelu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalConfig implements Item {
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "值")
    private String value;
}
