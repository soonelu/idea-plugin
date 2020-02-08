package ltd.kafka.soonelu.plugins.constant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author soonelu
 */
@ApiModel(value = "系统常量")
public class SysValue {
    @ApiModelProperty(value = "static")
    private static final String STATIC = "static";

    @ApiModelProperty(value = "final")
    private static final String FINAL = "final";

    @ApiModelProperty(value = "public")
    private static final String PUBLIC = "public";

    @ApiModelProperty(value = "private")
    private static final String PRIVATE = "private";
}
