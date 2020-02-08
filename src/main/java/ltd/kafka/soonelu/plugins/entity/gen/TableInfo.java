package ltd.kafka.soonelu.plugins.entity.gen;

import com.intellij.database.psi.DbTable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 表信息
 *
 * @author soonelu
 */
@Data
public class TableInfo {
    @ApiModelProperty(value = "原始对象")
    private DbTable obj;
    @ApiModelProperty(value = "表名（首字母大写）")
    private String name;
    @ApiModelProperty(value = "注释")
    private String comment;
    @ApiModelProperty(value = "所有列")
    private List<ColumnInfo> fullColumn;
    @ApiModelProperty(value = "主键列")
    private List<ColumnInfo> pkColumn;
    @ApiModelProperty(value = "其他列")
    private List<ColumnInfo> otherColumn;
    @ApiModelProperty(value = "保存的包名称")
    private String savePackageName;
    @ApiModelProperty(value = "保存路径")
    private String savePath;
    @ApiModelProperty(value = "保存的model名称")
    private String saveModelName;
}
