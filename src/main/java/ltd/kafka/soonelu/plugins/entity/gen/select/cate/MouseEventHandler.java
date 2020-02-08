package ltd.kafka.soonelu.plugins.entity.gen.select.cate;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;

/**
 * @author soonelu
 */
public class MouseEventHandler implements ILoadPsiClass {

    /**
     * 获取psiClass对象
     *
     * @return psiClass
     */
    @Override
    public PsiClass loadPsiClass(AnActionEvent e) {
        PsiElement psiElement = e.getData(LangDataKeys.PSI_ELEMENT);
        if (!(psiElement instanceof PsiClass)) {
            return null;
        }
        return (PsiClass) psiElement;
    }
}
