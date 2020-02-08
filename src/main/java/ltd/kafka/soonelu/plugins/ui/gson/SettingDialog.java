package ltd.kafka.soonelu.plugins.ui.gson;

import com.intellij.openapi.project.Project;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.codeStyle.VariableKind;
import ltd.kafka.soonelu.plugins.config.GsonConfig;
import ltd.kafka.soonelu.plugins.constant.Constant;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import java.awt.event.*;

public class SettingDialog extends JFrame {

    private JPanel contentPane;
    private JRadioButton fieldPublicRadioButton;
    private JRadioButton fieldPrivateRadioButton;
    private JCheckBox useSerializedNameCheckBox;
    private JButton objectButton;
    private JButton object1Button;
    private JButton arrayButton;
    private JButton array1Button;
    private JTextField suffixEdit;
    private JCheckBox objectFromDataCB;
    private JCheckBox objectFromData1CB;
    private JCheckBox arrayFromDataCB;
    private JCheckBox arrayFromData1CB;
    private JCheckBox reuseEntityCB;
    private JButton cancelButton;
    private JButton okButton;
    private JTextField filedPrefixTF;
    private JCheckBox filedPrefixCB;
    private JRadioButton gsonJRB;
    private JRadioButton jackRB;
    private JRadioButton fastJsonRB;
    private JRadioButton otherRB;
    private JTextField annotationFT;
    private JCheckBox virgoModelCB;
    private JCheckBox generateCommentsCT;
    private JRadioButton loganSquareCB;
    private JRadioButton autoValueRadioButton;
    private JCheckBox splitGenerateMode;
    private JRadioButton lombokRB;
    private JCheckBox useWrapperClassCB;

    SettingDialog(Project project) {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(okButton);
        this.setAlwaysOnTop(true);
        setTitle("Setting");
        okButton.addActionListener(e -> onOk());
        cancelButton.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        GsonConfig gsonConfig = GsonConfig.getInstant();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> onCancel()
                , KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0)
                , JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        if (gsonConfig.isFieldPrivateMode()) {
            fieldPrivateRadioButton.setSelected(true);
        } else {
            fieldPublicRadioButton.setSelected(true);
        }

        virgoModelCB.setSelected(gsonConfig.isVirgoMode());
        generateCommentsCT.setSelected(gsonConfig.isGenerateComments());
        filedPrefixCB.setSelected(gsonConfig.isUseFieldNamePrefix());
        filedPrefixTF.setEnabled(gsonConfig.isUseFieldNamePrefix());
        useSerializedNameCheckBox.setSelected(gsonConfig.isUseSerializedName());
        objectFromDataCB.setSelected(gsonConfig.isObjectFromData());
        objectFromData1CB.setSelected(gsonConfig.isObjectFromData1());
        arrayFromDataCB.setSelected(gsonConfig.isArrayFromData());
        arrayFromData1CB.setSelected(gsonConfig.isArrayFromData1());
        reuseEntityCB.setSelected(gsonConfig.isReuseEntity());
        objectButton.setEnabled(objectFromDataCB.isSelected());
        object1Button.setEnabled(objectFromData1CB.isSelected());
        arrayButton.setEnabled(arrayFromDataCB.isSelected());
        array1Button.setEnabled(arrayFromData1CB.isSelected());
        suffixEdit.setText(gsonConfig.getSuffixStr());
        splitGenerateMode.setSelected(gsonConfig.isSplitGenerate());
        useWrapperClassCB.setSelected(gsonConfig.isUseWrapperClass());
        objectFromDataCB.addActionListener(actionEvent ->
                objectButton.setEnabled(objectFromDataCB.isSelected()));
        objectFromData1CB.addActionListener(actionEvent ->
                object1Button.setEnabled(objectFromData1CB.isSelected()));
        arrayFromDataCB.addActionListener(actionEvent ->
                arrayButton.setEnabled(arrayFromDataCB.isSelected()));
        arrayFromData1CB.addActionListener(actionEvent ->
                array1Button.setEnabled(arrayFromData1CB.isSelected()));
        filedPrefixCB.addActionListener(actionEvent ->
                filedPrefixTF.setEnabled(filedPrefixCB.isSelected()));
        otherRB.addActionListener(actionEvent -> {
            annotationFT.setText("@{filed}");
            annotationFT.setEnabled(otherRB.isSelected());
            objectFromDataCB.setEnabled(false);
            objectFromData1CB.setEnabled(false);
            arrayFromDataCB.setEnabled(false);
            arrayFromData1CB.setEnabled(false);
            objectFromDataCB.setSelected(false);
            objectFromData1CB.setSelected(false);
            arrayFromDataCB.setSelected(false);
            arrayFromData1CB.setSelected(false);
            objectButton.setEnabled(false);
            object1Button.setEnabled(false);
            arrayButton.setEnabled(false);
            array1Button.setEnabled(false);

        });
        loganSquareCB.addActionListener(actionEvent -> {
            if (loganSquareCB.isSelected()) {
                annotationFT.setText(Constant.loganSquareAnnotation);
            }
            annotationFT.setEnabled(otherRB.isSelected());
            objectFromDataCB.setEnabled(false);
            objectFromData1CB.setEnabled(false);
            arrayFromDataCB.setEnabled(false);
            arrayFromData1CB.setEnabled(false);
            objectFromDataCB.setSelected(false);
            objectFromData1CB.setSelected(false);
            arrayFromDataCB.setSelected(false);
            arrayFromData1CB.setSelected(false);
            objectButton.setEnabled(false);
            object1Button.setEnabled(false);
            arrayButton.setEnabled(false);
            array1Button.setEnabled(false);
        });
        lombokRB.addActionListener(actionEvent -> {
            if (lombokRB.isSelected()) {
                annotationFT.setText(Constant.lombokAnnotation);
            }
            annotationFT.setEnabled(otherRB.isSelected());
            objectFromDataCB.setEnabled(false);
            objectFromData1CB.setEnabled(false);
            arrayFromDataCB.setEnabled(false);
            arrayFromData1CB.setEnabled(false);
            objectFromDataCB.setSelected(false);
            objectFromData1CB.setSelected(false);
            arrayFromDataCB.setSelected(false);
            arrayFromData1CB.setSelected(false);
            objectButton.setEnabled(false);
            object1Button.setEnabled(false);
            arrayButton.setEnabled(false);
            array1Button.setEnabled(false);
        });
        String filedPrefix;
        filedPrefix = gsonConfig.getFiledNamePreFixStr();
        if (TextUtils.isEmpty(filedPrefix)) {
            JavaCodeStyleManager styleManager = JavaCodeStyleManager.getInstance(project);
            filedPrefix = styleManager.getPrefixByVariableKind(VariableKind.FIELD
            );
        }
        filedPrefixTF.setText(filedPrefix);
        gsonJRB.addActionListener(actionEvent -> {
            if (gsonJRB.isSelected()) {
                annotationFT.setText(Constant.gsonAnnotation);
            }
            objectFromDataCB.setEnabled(true);
            objectFromData1CB.setEnabled(true);
            arrayFromDataCB.setEnabled(true);
            arrayFromData1CB.setEnabled(true);
            annotationFT.setEnabled(false);
        });
        fastJsonRB.addActionListener(actionEvent -> {

            if (fastJsonRB.isSelected()) {
                annotationFT.setText(Constant.fastAnnotation);
            }
            objectFromDataCB.setEnabled(false);
            objectFromData1CB.setEnabled(false);
            arrayFromDataCB.setEnabled(false);
            arrayFromData1CB.setEnabled(false);
            annotationFT.setEnabled(false);
            objectFromDataCB.setSelected(false);
            objectFromData1CB.setSelected(false);
            arrayFromDataCB.setSelected(false);
            arrayFromData1CB.setSelected(false);
            objectButton.setEnabled(false);
            object1Button.setEnabled(false);
            arrayButton.setEnabled(false);
            array1Button.setEnabled(false);
        });
        jackRB.addActionListener(actionEvent -> {
            if (jackRB.isSelected()) {
                annotationFT.setText(Constant.jackAnnotation);
            }
            annotationFT.setEnabled(false);
            objectFromDataCB.setEnabled(false);
            objectFromData1CB.setEnabled(false);
            arrayFromDataCB.setEnabled(false);
            arrayFromData1CB.setEnabled(false);
            annotationFT.setEnabled(false);
            objectFromDataCB.setSelected(false);
            objectFromData1CB.setSelected(false);
            arrayFromDataCB.setSelected(false);
            arrayFromData1CB.setSelected(false);
            objectButton.setEnabled(false);
            object1Button.setEnabled(false);
            arrayButton.setEnabled(false);
            array1Button.setEnabled(false);
        });
        autoValueRadioButton.addActionListener(e -> {
            if (autoValueRadioButton.isSelected()) {
                annotationFT.setText(Constant.autoValueAnnotation);
            }
        });

        String annotationStr = gsonConfig.getAnnotationStr();
        switch (annotationStr) {
            case Constant.gsonAnnotation:
                gsonJRB.setSelected(true);
                annotationFT.setEnabled(false);
                break;
            case Constant.fastAnnotation:
                fastJsonRB.setSelected(true);
                annotationFT.setEnabled(false);
                break;
            case Constant.jackAnnotation:
                jackRB.setSelected(true);
                annotationFT.setEnabled(false);
                break;
            case Constant.loganSquareAnnotation:
                loganSquareCB.setSelected(true);
                annotationFT.setEnabled(false);
                break;
            case Constant.autoValueAnnotation:
                autoValueRadioButton.setSelected(true);
                annotationFT.setEnabled(false);
                break;
            default:
                otherRB.setSelected(true);
                annotationFT.setEnabled(true);
                break;
        }
        annotationFT.setText(annotationStr);
        objectButton.addActionListener(actionEvent -> {
            EditDialog editDialog = new EditDialog(EditDialog.Type.OBJECT_FROM_DATA);
            editDialog.setSize(600, 360);
            editDialog.setLocationRelativeTo(null);
            editDialog.setResizable(false);
            editDialog.setVisible(true);
        });
        object1Button.addActionListener(actionEvent -> {
            EditDialog editDialog = new EditDialog(EditDialog.Type.OBJECT_FROM_DATA1);
            editDialog.setSize(600, 360);
            editDialog.setLocationRelativeTo(null);
            editDialog.setResizable(false);
            editDialog.setVisible(true);
        });
        arrayButton.addActionListener(actionEvent -> {
            EditDialog editDialog = new EditDialog(EditDialog.Type.ARRAY_FROM_DATA);
            editDialog.setSize(600, 600);
            editDialog.setLocationRelativeTo(null);
            editDialog.setResizable(false);
            editDialog.setVisible(true);
        });
        array1Button.addActionListener(actionEvent -> {
            EditDialog editDialog = new EditDialog(EditDialog.Type.ARRAY_FROM_DATA1);
            editDialog.setSize(600, 600);
            editDialog.setLocationRelativeTo(null);
            editDialog.setResizable(false);
            editDialog.setVisible(true);
        });
    }


    private void onOk() {
        GsonConfig gsonConfig = GsonConfig.getInstant();
        gsonConfig.setFieldPrivateMode(fieldPrivateRadioButton.isSelected());
        gsonConfig.setUseSerializedName(useSerializedNameCheckBox.isSelected());
        gsonConfig.setArrayFromData(arrayFromDataCB.isSelected());
        gsonConfig.setArrayFromData1(arrayFromData1CB.isSelected());
        gsonConfig.setObjectFromData(objectFromDataCB.isSelected());
        gsonConfig.setObjectFromData1(objectFromData1CB.isSelected());
        gsonConfig.setReuseEntity(reuseEntityCB.isSelected());
        gsonConfig.setSuffixStr(suffixEdit.getText());
        gsonConfig.setVirgoMode(virgoModelCB.isSelected());
        gsonConfig.setGenerateComments(generateCommentsCT.isSelected());
        gsonConfig.setFiledNamePreFixStr(filedPrefixTF.getText());
        gsonConfig.setAnnotationStr(annotationFT.getText());
        gsonConfig.setUseFieldNamePrefix(filedPrefixCB.isSelected());
        gsonConfig.setSplitGenerate(splitGenerateMode.isSelected());
        gsonConfig.setUseWrapperClass(useWrapperClassCB.isSelected());
        gsonConfig.save();
        dispose();
    }

    private void createUIComponents() {
    }

    private void onCancel() {
        dispose();
    }

}
