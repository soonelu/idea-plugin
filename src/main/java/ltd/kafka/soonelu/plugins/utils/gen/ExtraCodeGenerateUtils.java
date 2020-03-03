package ltd.kafka.soonelu.plugins.utils.gen;


import io.swagger.annotations.ApiModelProperty;
import ltd.kafka.soonelu.plugins.config.GenSettings;
import ltd.kafka.soonelu.plugins.entity.gen.TableInfo;
import ltd.kafka.soonelu.plugins.entity.gen.Template;
import ltd.kafka.soonelu.plugins.service.gen.impl.CodeGenerateServiceImpl;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 额外的代码生成工具
 *
 * @author soonelu
 */
public class ExtraCodeGenerateUtils {
    @ApiModelProperty(value = "代码生成服务")
    private CodeGenerateServiceImpl codeGenerateService;
    @ApiModelProperty(value = "表信息对象")
    private TableInfo tableInfo;
    @ApiModelProperty(value = "文件覆盖提示")
    private Boolean title;
    @ApiModelProperty(value = "设置实例对象")
    private GenSettings genSettings = GenSettings.getInstance();


    public ExtraCodeGenerateUtils(CodeGenerateServiceImpl codeGenerateService, TableInfo tableInfo, Boolean title) {
        this.codeGenerateService = codeGenerateService;
        this.tableInfo = tableInfo;
        this.title = title;
    }

    /**
     * 生成代码
     *
     * @param templateName 模板名称
     * @param param        附加参数
     */
    public void run(String templateName, Map<String, Object> param) {
        // 获取到模板
        Template currTemplate = null;
        for (Template template :
                genSettings.getTemplateGroupMap().get(genSettings.getCurrTemplateGroupName()).getElementList()) {
            if (Objects.equals(template.getName(), templateName)) {
                currTemplate = template;
            }
        }
        if (currTemplate == null) {
            return;
        }
        // 生成代码
        codeGenerateService.generate(Collections.singletonList(currTemplate),
                Collections.singletonList(this.tableInfo), this.title, param);
    }
}
