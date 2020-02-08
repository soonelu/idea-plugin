package ltd.kafka.soonelu.plugins.ui.gson;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;

import javax.swing.*;

/**
 * @author soonelu
 */
public class Toast {

    /**
     * Display simple notification of given type
     *
     * @param project prj
     * @param type    消息类型
     * @param text    消息内容
     */
    static void make(Project project, JComponent jComponent, MessageType type, String text) {

        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);

        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(text, type, null)
                .setFadeoutTime(7500)
                .createBalloon()
                .show(RelativePoint.getCenterOf(jComponent), Balloon.Position.above);
    }

    /**
     * Display simple notification of given type
     *
     * @param project prj
     * @param type    消息类型
     * @param text    消息内容
     */
    public static void make(Project project, MessageType type, String text) {

        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);

        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(text, type, null)
                .setFadeoutTime(7500)
                .createBalloon()
                .show(RelativePoint.getCenterOf(statusBar.getComponent()), Balloon.Position.atRight);
    }
}
