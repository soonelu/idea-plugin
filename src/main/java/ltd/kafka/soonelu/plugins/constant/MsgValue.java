package ltd.kafka.soonelu.plugins.constant;

import io.swagger.annotations.ApiModelProperty;

/**
 * 消息常量值
 *
 * @author soonelu
 */
public class MsgValue {
    @ApiModelProperty(value = "提示信息")
    public static final String TITLE_INFO = "AllGen Title Info";

    @ApiModelProperty(value = "删除分组二次确认信息")
    public static final String CONFIRM_DELETE_GROUP = "确认删除%s分组？";

    @ApiModelProperty(value = "删除二次确认信息")
    public static final String CONFIRM_DELETE_MESSAGE = "确认删除%s？";

    @ApiModelProperty(value = "分组名称标题")
    public static final String GROUP_NAME_LABEL = "分组名称：";

    @ApiModelProperty(value = "模板名称标题")
    public static final String ITEM_NAME_LABEL = "新名称：";

    @ApiModelProperty(value = "重置默认设置提示信息")
    public static final String RESET_DEFAULT_SETTING_MSG = "确认重置默认配置?\n" +
            "重置默认配置只会还原插件自带分组配置信息，不会删除用户新增分组信息。";
}
