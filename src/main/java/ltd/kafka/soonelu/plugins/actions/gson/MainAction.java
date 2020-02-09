package ltd.kafka.soonelu.plugins.actions.gson;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;
import ltd.kafka.soonelu.plugins.ui.gson.JsonDialog;
import org.jetbrains.annotations.NotNull;

/**
 * @author soonelu
 */
public class MainAction extends BaseGenerateAction {

    @SuppressWarnings("unused")
    public MainAction() {
        super(null);
    }

    @SuppressWarnings("unused")
    public MainAction(CodeInsightActionHandler handler) {
        super(handler);
    }

    @Override
    protected boolean isValidForClass(final PsiClass targetClass) {
        return super.isValidForClass(targetClass);
    }

    @Override
    public boolean isValidForFile(@NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        return super.isValidForFile(project, editor, file);
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        if (null == project || null == editor) {
            return;
        }
        PsiFile mFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        if (null == mFile) {
            return;
        }
        PsiClass psiClass = getTargetClass(editor, mFile);
        JsonDialog jsonD = new JsonDialog(psiClass, mFile, project);
        jsonD.setCls(psiClass);
        jsonD.setFile(mFile);
        jsonD.setProject(project);
        jsonD.setSize(600, 400);
        jsonD.setLocationRelativeTo(null);
        jsonD.setVisible(true);

    }

}
