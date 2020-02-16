package ltd.kafka.soonelu.plugins.utils.gen;


import ltd.kafka.soonelu.plugins.config.GenSettings;
import ltd.kafka.soonelu.plugins.entity.gen.ColumnConfigGroup;
import ltd.kafka.soonelu.plugins.entity.gen.GlobalConfigGroup;
import ltd.kafka.soonelu.plugins.entity.gen.TemplateGroup;
import ltd.kafka.soonelu.plugins.entity.gen.TypeMapperGroup;

/**
 * 当前分组配置获取工具
 *
 * @author soonelu
 */
public final class CurrGroupUtils {
    /**
     * 禁用构造方法
     */
    private CurrGroupUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取当前模板组对象
     *
     * @return 模板组对象
     */
    public static TemplateGroup getCurrTemplateGroup() {
        GenSettings genSettings = GenSettings.getInstance();
        String groupName = genSettings.getCurrTemplateGroupName();
        return genSettings.getTemplateGroupMap().get(groupName);
    }

    /**
     * 覆盖或添加模板组
     *
     * @param groupName     组名
     * @param templateGroup 模板组对象
     */
    public static void setCurrTemplateGroup(String groupName, TemplateGroup templateGroup) {
        templateGroup.setName(groupName);
        GenSettings.getInstance().getTemplateGroupMap().put(groupName, templateGroup);
    }

    /**
     * 获取当前全局配置组对象
     *
     * @return 全局配置组对象
     */
    static GlobalConfigGroup getCurrGlobalConfigGroup() {
        GenSettings genSettings = GenSettings.getInstance();
        String groupName = genSettings.getCurrGlobalConfigGroupName();
        return genSettings.getGlobalConfigGroupMap().get(groupName);
    }

    /**
     * 覆盖或添加全局配置组
     *
     * @param groupName         组名
     * @param globalConfigGroup 全局配置组对象
     */
    public static void setGlobalConfigGroup(String groupName, GlobalConfigGroup globalConfigGroup) {
        globalConfigGroup.setName(groupName);
        GenSettings.getInstance().getGlobalConfigGroupMap().put(groupName, globalConfigGroup);
    }

    /**
     * 获取当前类型映射组对象
     *
     * @return 类型映射组对象
     */
    public static TypeMapperGroup getCurrTypeMapperGroup() {
        GenSettings genSettings = GenSettings.getInstance();
        String groupName = genSettings.getCurrTypeMapperGroupName();
        return genSettings.getTypeMapperGroupMap().get(groupName);
    }

    /**
     * 覆盖或添加类型映射组
     *
     * @param groupName         组名
     * @param typeMapperGroup 类型映射组对象
     */
    public static void setTypeMapperGroup(String groupName, TypeMapperGroup typeMapperGroup) {
        typeMapperGroup.setName(groupName);
        GenSettings.getInstance().getTypeMapperGroupMap().put(groupName, typeMapperGroup);
    }

    /**
     * 获取当前列配置组对象
     *
     * @return 列配置组对象
     */
    public static ColumnConfigGroup getCurrColumnConfigGroup() {
        GenSettings genSettings = GenSettings.getInstance();
        String groupName = genSettings.getCurrColumnConfigGroupName();
        return genSettings.getColumnConfigGroupMap().get(groupName);
    }

    /**
     * 覆盖或添加列配置组
     *
     * @param groupName         组名
     * @param tolumnConfigGroup 列配置组对象
     */
    public static void setColumnConfigGroup(String groupName, ColumnConfigGroup tolumnConfigGroup) {
        tolumnConfigGroup.setName(groupName);
        GenSettings.getInstance().getColumnConfigGroupMap().put(groupName, tolumnConfigGroup);
    }
}
