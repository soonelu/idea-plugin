package ltd.kafka.soonelu.plugins.process.gson;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;
import ltd.kafka.soonelu.plugins.entity.gson.ClassEntity;
import ltd.kafka.soonelu.plugins.entity.gson.ConvertLibrary;


/**
 * @author soonelu
 */
public class ClassProcessor {

    private PsiElementFactory factory;
    private PsiClass cls;
    private Processor processor;

    public ClassProcessor(PsiElementFactory factory, PsiClass cls) {
        this.factory = factory;
        this.cls = cls;
        processor = Processor.getProcessor(ConvertLibrary.from());
    }

    public void generate(ClassEntity classEntity, IProcessor visitor) {
        if (processor != null) {
            processor.process(classEntity, factory, cls, visitor);
        }
    }
}
