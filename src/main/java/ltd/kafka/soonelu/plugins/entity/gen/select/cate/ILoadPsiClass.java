package ltd.kafka.soonelu.plugins.entity.gen.select.cate;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiClass;

/**
 * 获取class
 *
 * @author soonelu
 */
public interface ILoadPsiClass {
    /**
     * 获取psiClass对象
     *
     * @param e 事件
     * @return 类对象
     */
    PsiClass loadPsiClass(AnActionEvent e);
}
