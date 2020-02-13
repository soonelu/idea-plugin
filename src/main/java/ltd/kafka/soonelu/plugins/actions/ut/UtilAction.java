package ltd.kafka.soonelu.plugins.actions.ut;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import ltd.kafka.soonelu.plugins.ui.ut.TimeFormatDialog;
import org.jetbrains.annotations.NotNull;

/**
 * @author soonelu
 */
public class UtilAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        new TimeFormatDialog().open();
    }
}
