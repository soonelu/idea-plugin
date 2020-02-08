package ltd.kafka.soonelu.plugins.entity.gen;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltd.kafka.soonelu.plugins.ui.gen.base.Item;

/**
 * 模板信息类
 *
 * @author soonelu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Template implements Item {
    @ApiModelProperty(value = "模板名称")
    private String name;
    @ApiModelProperty(value = "模板代码")
    private String code;
}
