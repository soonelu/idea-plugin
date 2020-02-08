package ltd.kafka.soonelu.plugins.entity.gen;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 从磁盘上加载的模板
 *
 * @author soonelu
 */
@Data
public class TemplateLoadStore {
    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "模板名称")
    private List<String> templateNameList;
}
