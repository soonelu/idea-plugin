package ltd.kafka.soonelu.plugins.service.gen;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import ltd.kafka.soonelu.plugins.entity.gen.TableInfo;
import ltd.kafka.soonelu.plugins.entity.gen.Template;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * 代码生成服务，Project级别Service
 *
 * @author soonelu
 */
public interface CodeGenerateService {
    /**
     * 获取实例对象
     *
     * @param project 项目对象
     * @return 实例对象
     */
    static CodeGenerateService getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, CodeGenerateService.class);
    }

    /**
     * 生成代码，并自动保存到对应位置，使用统一配置
     *
     * @param templates     模板
     * @param unifiedConfig 是否使用统一配置
     * @param title         是否显示提示
     */
    void generateByUnifiedConfig(Collection<Template> templates, boolean unifiedConfig, boolean title);

    /**
     * 生成代码
     *
     * @param template  模板
     * @param tableInfo 表信息对象
     * @return 生成好的代码
     */
    String generate(Template template, TableInfo tableInfo);
}
