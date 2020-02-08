package ltd.kafka.soonelu.plugins.process.gson;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;
import ltd.kafka.soonelu.plugins.entity.gson.ClassEntity;

/**
 * @author soonelu
 */
class FastJsonProcessor extends Processor {

    @Override
    public void onStarProcess(ClassEntity classEntity, PsiElementFactory factory, PsiClass cls, IProcessor visitor) {
        super.onEndProcess(classEntity, factory, cls, visitor);
    }
}
