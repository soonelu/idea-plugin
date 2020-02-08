package ltd.kafka.soonelu.plugins.provider;

import com.intellij.psi.CommonClassNames;
import com.intellij.velocity.VtlGlobalVariableProvider;
import com.intellij.velocity.psi.VtlLightVariable;
import com.intellij.velocity.psi.VtlVariable;
import com.intellij.velocity.psi.files.VtlFile;
import ltd.kafka.soonelu.plugins.entity.gen.Callback;
import ltd.kafka.soonelu.plugins.entity.gen.TableInfo;
import ltd.kafka.soonelu.plugins.ui.gen.base.TemplateEditor;
import ltd.kafka.soonelu.plugins.utils.gen.ExtraCodeGenerateUtils;
import ltd.kafka.soonelu.plugins.utils.gen.GlobalTool;
import ltd.kafka.soonelu.plugins.utils.gen.TimeUtils;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * AllGen 专用Velocity全局变量
 *
 * @author soonelu
 */
public class GenGlobalVariableProvider extends VtlGlobalVariableProvider {

    @NotNull
    @Override
    public Collection<VtlVariable> getGlobalVariables(@NotNull VtlFile file) {
        // 非AllGen模板，不提供支持。
        if (!Objects.equals(file.getName(), TemplateEditor.ALL_GEN_TEMPLATE)) {
            //noinspection unchecked
            return Collections.EMPTY_LIST;
        }
        final List<VtlVariable> result = new ArrayList<>();
        result.add(new VtlLightVariable("author", file, CommonClassNames.JAVA_LANG_STRING));
        result.add(new VtlLightVariable("encode", file, CommonClassNames.JAVA_LANG_STRING));
        result.add(new VtlLightVariable("packageName", file, CommonClassNames.JAVA_LANG_STRING));
        result.add(new VtlLightVariable("modulePath", file, CommonClassNames.JAVA_LANG_STRING));
        result.add(new VtlLightVariable("importList", file, CommonClassNames.JAVA_UTIL_SET + "<" + CommonClassNames.JAVA_LANG_STRING + ">"));
        result.add(new VtlLightVariable("callback", file, Callback.class.getTypeName()));
        result.add(new VtlLightVariable("tool", file, GlobalTool.class.getTypeName()));
        result.add(new VtlLightVariable("time", file, TimeUtils.class.getTypeName()));
        result.add(new VtlLightVariable("tableInfo", file, TableInfo.class.getTypeName()));
        result.add(new VtlLightVariable("tableInfoList", file, CommonClassNames.JAVA_UTIL_LIST + "<" + TableInfo.class.getTypeName() + ">"));
        result.add(new VtlLightVariable("generateService", file, ExtraCodeGenerateUtils.class.getTypeName()));
        return result;
    }

}
