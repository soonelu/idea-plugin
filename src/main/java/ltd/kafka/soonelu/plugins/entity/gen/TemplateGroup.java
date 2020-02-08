package ltd.kafka.soonelu.plugins.entity.gen;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 模板分组类
 *
 * @author soonelu
 */
@Data
public class TemplateGroup implements AbstractGroup<Template> {
    @ApiModelProperty(value = "分组名称")
    private String name;
    @ApiModelProperty(value = "元素对象")
    private List<Template> elementList;
}
