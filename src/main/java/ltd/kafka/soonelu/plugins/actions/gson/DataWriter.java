package ltd.kafka.soonelu.plugins.actions.gson;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiFile;
import ltd.kafka.soonelu.plugins.entity.gson.ClassEntity;
import ltd.kafka.soonelu.plugins.process.gson.ClassProcessor;
import ltd.kafka.soonelu.plugins.process.gson.IProcessor;
import ltd.kafka.soonelu.plugins.ui.gson.Toast;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soonelu
 */
public class DataWriter extends WriteCommandAction.Simple {

    private PsiClass cls;
    private PsiElementFactory factory;
    private Project project;
    private PsiFile file;
    private ClassEntity targetClass;
    private List<String> generateClassList = new ArrayList<>();

    public DataWriter(PsiFile file, Project project, PsiClass cls) {
        super(project, file);
        factory = JavaPsiFacade.getElementFactory(project);
        this.file = file;
        this.project = project;
        this.cls = cls;
    }

    public void execute(ClassEntity targetClass) {
        this.targetClass = targetClass;
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "GsonFormat") {

            @Override
            public void run(@NotNull ProgressIndicator progressIndicator) {
                progressIndicator.setIndeterminate(true);
                long currentTimeMillis = System.currentTimeMillis();
                execute();
                progressIndicator.setIndeterminate(false);
                progressIndicator.setFraction(1.0);
                Toast.make(project, MessageType.INFO,
                        "GsonFormat [" + (System.currentTimeMillis() - currentTimeMillis) + " ms]\n");
            }
        });
    }


    @Override
    protected void run() {
        if (targetClass == null) {
            return;
        }
        generateClassList.clear();
        new ClassProcessor(factory, cls).generate(targetClass, new IProcessor() {
            @Override
            public void onStarProcess(ClassEntity classEntity, PsiElementFactory factory, PsiClass cls) {
                generateClassList.add(cls.getQualifiedName());
            }

            @Override
            public void onEndProcess(ClassEntity classEntity, PsiElementFactory factory, PsiClass cls) {

            }

            @Override
            public void onStartGenerateClass(PsiElementFactory factory, ClassEntity classEntity, PsiClass parentClass) {

            }

            @Override
            public void onEndGenerateClass(PsiElementFactory factory, ClassEntity classEntity, PsiClass parentClass,
                                           PsiClass generateClass) {
                generateClassList.add(generateClass.getQualifiedName());

            }
        });
    }

}
