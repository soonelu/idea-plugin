package ltd.kafka.soonelu.plugins.process.gson;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import ltd.kafka.soonelu.plugins.config.GsonConfig;
import ltd.kafka.soonelu.plugins.constant.Constant;
import ltd.kafka.soonelu.plugins.entity.gson.ClassEntity;
import ltd.kafka.soonelu.plugins.entity.gson.ConvertLibrary;
import ltd.kafka.soonelu.plugins.entity.gson.FieldEntity;
import ltd.kafka.soonelu.plugins.utils.gson.FieldHelper;
import ltd.kafka.soonelu.plugins.utils.gson.GsonPsiClassUtil;
import ltd.kafka.soonelu.plugins.utils.StringUtils;
import ltd.kafka.soonelu.plugins.utils.gson.Try;
import org.apache.http.util.TextUtils;

import java.util.HashMap;


/**
 * @author soonelu
 */
public abstract class Processor {

    private static HashMap<ConvertLibrary, Processor> sProcessorMap = new HashMap<>();

    static {
        sProcessorMap.put(ConvertLibrary.Gson, new GsonProcessor());
        sProcessorMap.put(ConvertLibrary.Jack, new JackProcessor());
        sProcessorMap.put(ConvertLibrary.FastJson, new FastJsonProcessor());
        sProcessorMap.put(ConvertLibrary.AutoValue, new AutoValueProcessor());
        sProcessorMap.put(ConvertLibrary.LoganSquare, new LoganSquareProcessor());
        sProcessorMap.put(ConvertLibrary.Other, new OtherProcessor());
        sProcessorMap.put(ConvertLibrary.Lombok, new LombokProcessor());
    }


    static Processor getProcessor(ConvertLibrary convertLibrary) {
        return sProcessorMap.get(convertLibrary);
    }

    private String mainPackage;

    public void process(ClassEntity classEntity, PsiElementFactory factory, PsiClass cls, IProcessor visitor) {
        mainPackage = GsonPsiClassUtil.getPackage(cls);
        onStarProcess(classEntity, factory, cls, visitor);

        for (FieldEntity fieldEntity : classEntity.getFields()) {
            generateField(factory, fieldEntity, cls, classEntity);
        }
        for (ClassEntity innerClass : classEntity.getInnerClasss()) {
            generateClass(factory, innerClass, cls, visitor);
        }
        generateGetterAndSetter(factory, cls, classEntity);
        generateConvertMethod(factory, cls, classEntity);
        onEndProcess(classEntity, factory, cls, visitor);
    }

    void onEndProcess(ClassEntity classEntity, PsiElementFactory factory, PsiClass cls, IProcessor visitor) {
        if (visitor != null) {
            visitor.onEndProcess(classEntity, factory, cls);
        }
        formatJavCode(cls);
    }

    private void formatJavCode(PsiClass cls) {
        if (cls == null) {
            return;
        }
        JavaCodeStyleManager styleManager = JavaCodeStyleManager.getInstance(cls.getProject());
        styleManager.optimizeImports(cls.getContainingFile());
        styleManager.shortenClassReferences(cls);
    }

    protected void onStarProcess(ClassEntity classEntity, PsiElementFactory factory, PsiClass cls, IProcessor visitor) {
        if (visitor != null) {
            visitor.onStarProcess(classEntity, factory, cls);
        }
    }

    protected void generateConvertMethod(PsiElementFactory factory, PsiClass cls, ClassEntity classEntity) {
        if (cls == null || cls.getName() == null) {
            return;
        }
        if (GsonConfig.getInstant().isObjectFromData()) {
            createMethod(factory, GsonConfig.getInstant().getObjectFromDataStr().replace("$ClassName$", cls.getName()).trim(), cls);
        }
        if (GsonConfig.getInstant().isObjectFromData1()) {
            createMethod(factory, GsonConfig.getInstant().getObjectFromDataStr1().replace("$ClassName$", cls.getName()).trim(), cls);
        }
        if (GsonConfig.getInstant().isArrayFromData()) {
            createMethod(factory, GsonConfig.getInstant().getArrayFromDataStr().replace("$ClassName$", cls.getName()).trim(), cls);
        }
        if (GsonConfig.getInstant().isArrayFromData1()) {
            createMethod(factory, GsonConfig.getInstant().getArrayFromData1Str().replace("$ClassName$", cls.getName()).trim(), cls);
        }
    }

    protected void generateGetterAndSetter(PsiElementFactory factory, PsiClass cls, ClassEntity classEntity) {

        if (GsonConfig.getInstant().isFieldPrivateMode()) {
            for (FieldEntity field : classEntity.getFields()) {
                createGetAndSetMethod(factory, cls, field);
            }
        }
    }

    private void createMethod(PsiElementFactory factory, String method, PsiClass cls) {
        Try.run(new Try.TryListener() {
            @Override
            public void run() {
                cls.add(factory.createMethodFromText(method, cls));
            }

            @Override
            public void runAgain() {

            }

            @Override
            public void error() {

            }
        });
    }

    protected void createGetAndSetMethod(PsiElementFactory factory, PsiClass cls, FieldEntity field) {
        if (field.isGenerate()) {
            String fieldName = field.getGenerateFieldName();
            String typeStr = field.getRealType();
            if (GsonConfig.getInstant().isUseFieldNamePrefix()) {
                String temp = fieldName.replaceAll("^" + GsonConfig.getInstant().getFiledNamePreFixStr(), "");
                if (!TextUtils.isEmpty(temp)) {
                    fieldName = temp;
                }
            }
            if (typeStr.equals("boolean") || typeStr.equals("Boolean")) {
                String method = "public ".concat(typeStr).concat("   is").concat(
                        StringUtils.captureName(fieldName)).concat("() {   return ").concat(
                        field.getGenerateFieldName()).concat(" ;} ");
                cls.add(factory.createMethodFromText(method, cls));
            } else {
                String method = "public ".concat(typeStr).concat("   get").concat(
                        StringUtils.captureName(fieldName)).concat(
                        "() {   return ").concat(
                        field.getGenerateFieldName()).concat(" ;} ");
                cls.add(factory.createMethodFromText(method, cls));
            }

            String arg = fieldName;
            if (GsonConfig.getInstant().isUseFieldNamePrefix()) {
                String temp = fieldName.replaceAll("^" + GsonConfig.getInstant().getFiledNamePreFixStr(), "");
                if (!TextUtils.isEmpty(temp)) {
                    fieldName = temp;
                    arg = fieldName;
                    if (arg.length() > 0) {

                        if (arg.length() > 1) {
                            arg = (arg.charAt(0) + "").toLowerCase() + arg.substring(1);
                        } else {
                            arg = arg.toLowerCase();
                        }
                    }
                }
            }

            String method =
                    "public void  set".concat(StringUtils.captureName(fieldName)).concat("( ").concat(typeStr).concat(" ").concat(arg).concat(") {   ");
            if (field.getGenerateFieldName().equals(arg)) {
                method = method.concat("this.").concat(field.getGenerateFieldName()).concat(" = ").concat(arg).concat(";} ");
            } else {
                method = method.concat(field.getGenerateFieldName()).concat(" = ").concat(arg).concat(";} ");
            }

            String finalMethod = method;
            String finalFieldName = fieldName;
            Try.run(new Try.TryListener() {
                @Override
                public void run() {
                    cls.add(factory.createMethodFromText(finalMethod, cls));
                }

                @Override
                public void runAgain() {
                    cls.addBefore(factory.createCommentFromText("// FIXME generate failure  method  set and get " + StringUtils.captureName(finalFieldName), cls), cls.getChildren()[0]);

                }

                @Override
                public void error() {

                }
            });
        }
    }

    protected void generateClass(PsiElementFactory factory, ClassEntity classEntity, PsiClass parentClass, IProcessor visitor) {

        onStartGenerateClass(factory, classEntity, parentClass, visitor);
        PsiClass generateClass = null;
        if (classEntity.isGenerate()) {
            //// TODO: 16/11/9  待重构
            if (GsonConfig.getInstant().isSplitGenerate()) {
               try {
                    generateClass = GsonPsiClassUtil.getPsiClass(
                            parentClass.getContainingFile(), parentClass.getProject(), classEntity.getQualifiedName());
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else {
                String classContent =
                        "public static class " + classEntity.getClassName() + "{}";
                generateClass = factory.createClassFromText(classContent, null).getInnerClasses()[0];
            }

            if (generateClass != null) {

                for (ClassEntity innerClass : classEntity.getInnerClasss()) {
                    generateClass(factory, innerClass, generateClass, visitor);
                }
                if (!GsonConfig.getInstant().isSplitGenerate()) {
                    generateClass = (PsiClass) parentClass.add(generateClass);
                }
                for (FieldEntity fieldEntity : classEntity.getFields()) {
                    generateField(factory, fieldEntity, generateClass, classEntity);
                }
                generateGetterAndSetter(factory, generateClass, classEntity);
                generateConvertMethod(factory, generateClass, classEntity);
            }
        }
        onEndGenerateClass(factory, classEntity, parentClass, generateClass, visitor);
        if (GsonConfig.getInstant().isSplitGenerate()) {
            formatJavCode(generateClass);
        }
    }

    protected void onStartGenerateClass(PsiElementFactory factory, ClassEntity classEntity, PsiClass parentClass, IProcessor visitor) {
        if (visitor != null) {
            visitor.onStartGenerateClass(factory, classEntity, parentClass);
        }
    }

    protected void onEndGenerateClass(PsiElementFactory factory, ClassEntity classEntity, PsiClass parentClass, PsiClass generateClass, IProcessor visitor) {
        if (visitor != null) {
            visitor.onEndGenerateClass(factory, classEntity, parentClass, generateClass);
        }
    }

    protected void generateField(PsiElementFactory factory, FieldEntity fieldEntity, PsiClass cls, ClassEntity classEntity) {

        if (fieldEntity.isGenerate()) {
            Try.run(new Try.TryListener() {
                @Override
                public void run() {
                    cls.add(factory.createFieldFromText(generateFieldText(classEntity, fieldEntity, null), cls));

                }

                @Override
                public void runAgain() {
                    fieldEntity.setFieldName(FieldHelper.generateLuckyFieldName(fieldEntity.getFieldName()));
                    cls.add(factory.createFieldFromText(generateFieldText(classEntity, fieldEntity, Constant.FIXME), cls));
                }

                @Override
                public void error() {
                    cls.addBefore(factory.createCommentFromText("// FIXME generate failure  field " + fieldEntity.getFieldName(), cls), cls.getChildren()[0]);
                }
            });

        }

    }

    private String generateFieldText(ClassEntity classEntity, FieldEntity fieldEntity, String fixme) {
        fixme = fixme == null ? "" : fixme;
        StringBuilder fieldSb = new StringBuilder();
        String filedName = fieldEntity.getGenerateFieldName();
        if (!TextUtils.isEmpty(classEntity.getExtra())) {
            fieldSb.append(classEntity.getExtra()).append("\n");
            classEntity.setExtra(null);
        }
        if (fieldEntity.getTargetClass() != null) {
            fieldEntity.getTargetClass().setGenerate(true);
        }
        if (!filedName.equals(fieldEntity.getKey()) || GsonConfig.getInstant().isUseSerializedName()) {
            fieldSb.append(GsonConfig.getInstant().geFullNameAnnotation().replaceAll("\\{filed\\}", fieldEntity.getKey()));
        }

        if (GsonConfig.getInstant().isFieldPrivateMode()) {
            fieldSb.append("private  ").append(fieldEntity.getFullNameType()).append(" ").append(filedName).append(" ; ");
        } else {
            fieldSb.append("public  ").append(fieldEntity.getFullNameType()).append(" ").append(filedName).append(" ; ");
        }
        return fieldSb.append(fixme).toString();
    }
}
