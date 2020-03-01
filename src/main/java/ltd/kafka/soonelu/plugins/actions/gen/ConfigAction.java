package ltd.kafka.soonelu.plugins.actions.gen;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import ltd.kafka.soonelu.plugins.ui.gen.ConfigTableDialog;
import org.jetbrains.annotations.Nullable;

/**
 * 表配置菜单
 *
 * @author soonelu
 */
public class ConfigAction extends AnAction {
    /**
     * 构造方法
     *
     * @param text 菜单名称
     */
    ConfigAction(@Nullable String text) {
        super(text);
    }

    /**
     * 处理方法
     *
     * @param event 事件对象
     */
    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();
        if (project == null) {
            return;
        }
        new ConfigTableDialog(project).open();
    }
}
