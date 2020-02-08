package ltd.kafka.soonelu.plugins.entity.gen.select.cate;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SyntheticElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;

import java.util.Objects;

/**
 * @author soonelu
 */
public class ShortcutHandler implements ILoadPsiClass {

    /**
     * 获取psiClass对象
     *
     * @return psiClass
     */
    @Override
    public PsiClass loadPsiClass(AnActionEvent e) {
        final DataContext dataContext = e.getDataContext();
        final Editor editor = PlatformDataKeys.EDITOR.getData(dataContext);
        if (null != editor) {
            final PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, Objects.requireNonNull(e.getProject()));
            if (null != psiFile) {
                return getTargetClass(editor, psiFile);
            }
        }
        return null;
    }

    /**
     * 根据当前编辑器获取类
     *
     * @param editor editor
     * @param file   file
     * @return class
     */
    private PsiClass getTargetClass(Editor editor, PsiFile file) {
        int offset = editor.getCaretModel().getOffset();
        PsiElement element = file.findElementAt(offset);
        if (element == null) {
            return null;
        }
        final PsiClass target = PsiTreeUtil.getParentOfType(element, PsiClass.class);
        return target instanceof SyntheticElement ? null : target;
    }
}
