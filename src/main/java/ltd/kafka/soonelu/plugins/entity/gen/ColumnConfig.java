package ltd.kafka.soonelu.plugins.entity.gen;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 列配置信息
 *
 * @author soonelu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnConfig {
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "类型")
    private ColumnConfigType type;
    @ApiModelProperty(value = "可选值，逗号分割")
    private String selectValue;

    public ColumnConfig(String title, ColumnConfigType type) {
        this.title = title;
        this.type = type;
    }
}
