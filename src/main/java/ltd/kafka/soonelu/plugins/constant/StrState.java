package ltd.kafka.soonelu.plugins.constant;

import io.swagger.annotations.ApiModelProperty;

/**
 * 字符串常量
 *
 * @author soonelu
 */
@SuppressWarnings("WeakerAccess")
public class StrState {


    @ApiModelProperty(value = "相对路径常量")
    public static final String RELATIVE_PATH = "./";

    @ApiModelProperty(value = "回车")
    public static final String KEY_ENTER = "\n";

    @ApiModelProperty(value = "回车")
    public static final String KEY_SLASH = "/";

    @ApiModelProperty(value = "*号")
    public static final String KEY_START = "*";

    @ApiModelProperty(value = "空白串")
    public static final String EMPTY_STRING = "";

    @ApiModelProperty(value = "空格")
    public static final String KEY_SPACE = " ";

    @ApiModelProperty(value = "点")
    public static final String KEY_DOT = ".";

    @ApiModelProperty(value = "分号")
    public static final String KEY_SEMICOLON = ";";

    @ApiModelProperty(value = "类型映射")
    public static final String TYPE_MAPPER = "typeMapper";

    @ApiModelProperty(value = "全局配置")
    public static final String GLOBAL_CONFIG = "globalConfig";

    @ApiModelProperty(value = "模板")
    public static final String TEMPLATE = "template";

    @ApiModelProperty(value = "列配置")
    public static final String COLUMN_CONFIG = "columnConfig";

    @ApiModelProperty(value = "括号匹配单个数值")
    public static final String BRACKETS_S_NUM = "(\\(\\d+\\))?";

    @ApiModelProperty(value = "括号匹配两个数值")
    public static final String BRACKETS_DB_NUM = "(\\(\\d+,\\d+\\))?";

    @ApiModelProperty(value = "java string 类型限定名")
    public static final String JAVA_TYPE_STRING = "java.lang.String";

    @ApiModelProperty(value = "java double 类型限定名")
    public static final String JAVA_TYPE_DOUBLE = "java.lang.Double";

    @ApiModelProperty(value = "java integer 类型限定名")
    public static final String JAVA_TYPE_INTEGER = "java.lang.Integer";

    @ApiModelProperty(value = "java long 类型限定名")
    public static final String JAVA_TYPE_LONG = "java.lang.Long";

    @ApiModelProperty(value = "java date 类型限定名")
    public static final String JAVA_TYPE_DATE = "java.util.Date";

    @ApiModelProperty(value = "java boolean 类型限定名")
    public static final String JAVA_TYPE_BOOL = "java.lang.Boolean";

    @ApiModelProperty(value = "数据库类型 varchar")
    public static final String DB_CLN_VARCHAR = "varchar";

    @ApiModelProperty(value = "数据库类型 char")
    public static final String DB_CLN_CHAR = "char";

    @ApiModelProperty(value = "数据库类型 text")
    public static final String DB_CLN_TEXT = "text";

    @ApiModelProperty(value = "数据库类型 decimal")
    public static final String DB_CLN_DECIMAL = "decimal";

    @ApiModelProperty(value = "数据库类型 integer")
    public static final String DB_CLN_INTEGER = "integer";

    @ApiModelProperty(value = "数据库类型 int")
    public static final String DB_CLN_INT = "int";

    @ApiModelProperty(value = "数据库类型 int4")
    public static final String DB_CLN_INT4 = "int4";

    @ApiModelProperty(value = "数据库类型 int8")
    public static final String DB_CLN_INT8 = "int8";

    @ApiModelProperty(value = "数据库类型 bigint")
    public static final String DB_CLN_BIGINT = "bigint";

    @ApiModelProperty(value = "数据库类型 datetime")
    public static final String DB_CLN_DATETIME = "datetime";

    @ApiModelProperty(value = "数据库类型 timestamp")
    public static final String DB_CLN_TIMESTAMP = "timestamp";

    @ApiModelProperty(value = "数据库类型 datetime")
    public static final String DB_CLN_BOOLEAN = "boolean";

    @ApiModelProperty(value = "数据库类型 varchar 匹配")
    public static final String DB_CLN_MATCH_VARCHAR =
            DB_CLN_VARCHAR + BRACKETS_S_NUM;

    @ApiModelProperty(value = "数据库类型 char 匹配")
    public static final String DB_CLN_MATCH_CHAR =
            DB_CLN_CHAR + BRACKETS_S_NUM;

    @ApiModelProperty(value = "数据库类型 decimal 匹配")
    public static final String DB_CLN_MATCH_S_DECIMAL =
            DB_CLN_DECIMAL + BRACKETS_S_NUM;

    @ApiModelProperty(value = "数据库类型 decimal 匹配")
    public static final String DB_CLN_MATCH_DB_DECIMAL =
            DB_CLN_DECIMAL + BRACKETS_DB_NUM;

    @ApiModelProperty(value = "数据库类型 int 匹配")
    public static final String DB_CLN_MATCH_DB_INT =
            DB_CLN_INT + BRACKETS_S_NUM;

    @ApiModelProperty(value = "数据库类型 int4 匹配")
    public static final String DB_CLN_MATCH_DB_INT4 =
            DB_CLN_INT4 + BRACKETS_S_NUM;

    @ApiModelProperty(value = "数据库类型 int8 匹配")
    public static final String DB_CLN_MATCH_DB_INT8 =
            DB_CLN_INT8 + BRACKETS_S_NUM;

    @ApiModelProperty(value = "数据库类型 bigint 匹配")
    public static final String DB_CLN_MATCH_DB_BIGINT =
            DB_CLN_BIGINT + BRACKETS_S_NUM;

    @ApiModelProperty(value = "块注释开头", notes = "/*")
    public static final String COMMENT_BLOCK_OPEN = "/*";

    @ApiModelProperty(value = "行注释开头", notes = "//")
    public static final String COMMENT_LINE_OPEN = "//";

    @ApiModelProperty(value = "块注释结束", notes = "*/")
    public static final String COMMENT_BLOCK_END = "*/";

    @ApiModelProperty(value = "swagger属性注释L")
    public static final String SWAG_PTY_CMT_L = "@ApiModelProperty(value = \"";

    @ApiModelProperty(value = "swagger属性注释R")
    public static final String SWAG_PTY_CMT_R = "\")";
}
