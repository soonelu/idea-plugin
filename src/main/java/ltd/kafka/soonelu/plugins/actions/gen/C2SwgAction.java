package ltd.kafka.soonelu.plugins.actions.gen;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.impl.PsiJavaParserFacadeImpl;
import com.intellij.psi.impl.source.codeStyle.CodeStyleManagerImpl;
import ltd.kafka.soonelu.plugins.constant.StrState;
import ltd.kafka.soonelu.plugins.entity.gen.select.cate.ILoadPsiClass;
import ltd.kafka.soonelu.plugins.entity.gen.select.cate.ShortcutHandler;
import ltd.kafka.soonelu.plugins.entity.gen.select.cate.MouseEventHandler;
import ltd.kafka.soonelu.plugins.utils.gen.NameUtils;
import ltd.kafka.soonelu.plugins.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author soonelu
 */
@ApiModel(value = "将注释转为swagger的属性配置")
@NoArgsConstructor
public class C2SwgAction extends AnAction {
    private static Map<String, ILoadPsiClass> handlerMap = new HashMap<>(4);

    static {
        handlerMap.put("ProjectViewPopup", new MouseEventHandler());
        handlerMap.put("keyboard shortcut", new ShortcutHandler());
    }

    /**
     * 将属性的注释替换为swagger的 @ApiModelProperty(value = "")
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {

        PsiClass selectClass = handlerMap.get(event.getPlace()).loadPsiClass(event);
        if (null == selectClass) {
            return;
        }
        /*获取所有属性*/
        PsiField[] psiFields = selectClass.getAllFields();
        WriteCommandAction.runWriteCommandAction(selectClass.getProject(), () -> {
            for (PsiField psiField : psiFields) {
                /*包含注释和属性内容*/
                String textField = psiField.getText();
                /*块注释*/
                if (StringUtils.isEmpty(textField)) {
                    continue;
                }
                textField = textField.trim();
                String blockOpen = StrState.COMMENT_BLOCK_OPEN;
                String blockEnd = StrState.COMMENT_BLOCK_END;
                String lineOpen = StrState.COMMENT_LINE_OPEN;
                /*注释内容*/
                StringBuilder cmtText = new StringBuilder();
                /*属性前注释处理 begin*/

                if (textField.startsWith(blockOpen)) {
                    /*属性前块注释处理*/
                    String tmpText = StringUtils.strBf(textField, blockEnd);
                    tmpText = tmpText.substring(1, tmpText.length() - 1);
                    String[] cmtArr = tmpText.split(StrState.KEY_ENTER);
                    for (String s : cmtArr) {
                        s = s.trim().replace(StrState.KEY_ENTER, StrState.EMPTY_STRING);
                        s = StringUtils.trimStr(s, StrState.KEY_START).trim();
                        cmtText.append(StrState.KEY_SPACE);
                        cmtText.append(s);
                    }
                } else if (textField.startsWith(lineOpen)) {
                    /*属性前行注释处理*/
                    String[] cmtArr = textField.split(StrState.KEY_ENTER);
                    List<String> cmtList = Arrays.asList(cmtArr);
                    cmtList = cmtList.subList(0, cmtList.size() - 1);
                    for (String s : cmtList) {
                        s = s.trim().replace(StrState.KEY_ENTER, StrState.EMPTY_STRING);
                        s = StringUtils.trimStr(s, StrState.COMMENT_LINE_OPEN).trim();
                        cmtText.append(StrState.KEY_SPACE);
                        cmtText.append(s);
                    }
                }
                /*属性前注释处理 end*/
                /*属性后注释处理 begin*/
                String[] cmtArr = textField.split(StrState.KEY_ENTER);
                List<String> cmtList = Arrays.asList(cmtArr);
                String fieldContentFull = cmtList.get(cmtList.size() - 1).trim();
                String fieldCmtAf = StringUtils.strAfEx(fieldContentFull, StrState.KEY_SEMICOLON).trim();
                if (fieldCmtAf.startsWith(blockOpen)) {
                    /*属性后块注释*/
                    String tmpText = StringUtils.strAfEx(textField, blockOpen);
                    tmpText = StringUtils.trimStr(tmpText, StrState.KEY_SLASH);
                    tmpText = StringUtils.trimStr(tmpText, StrState.KEY_START);
                    cmtText.append(StrState.KEY_SPACE);
                    cmtText.append(tmpText);
                } else if (fieldCmtAf.startsWith(lineOpen)) {
                    /*属性后行注释*/
                    String tmpText = StringUtils.strAfEx(textField, lineOpen);
                    cmtText.append(StrState.KEY_SPACE);
                    cmtText.append(tmpText);
                }
                /*属性后注释处理 end*/

                /*swagger注解*/
                String swaggerPty = NameUtils.getInstance().getSwagProperty(cmtText.toString());
                /*字段内容*/
                String fileContent = StringUtils.strBf(fieldContentFull, StrState.KEY_SEMICOLON);
                PsiJavaParserFacade facade = new PsiJavaParserFacadeImpl(psiField.getManager());
                PsiAnnotation annotation = facade.createAnnotationFromText(swaggerPty, psiField);
                PsiField field = facade.createFieldFromText(fileContent, psiField);
                annotation.add(field);
                psiField.replace(annotation);
            }
            CodeStyleManager codeStyleManager = new CodeStyleManagerImpl(selectClass.getProject());
            PsiElement formatter = codeStyleManager.reformat(selectClass);
            selectClass.replace(formatter);
        });
    }

}
