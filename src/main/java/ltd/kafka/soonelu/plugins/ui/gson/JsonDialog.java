package ltd.kafka.soonelu.plugins.ui.gson;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import ltd.kafka.soonelu.plugins.service.gson.ConvertBridge;
import ltd.kafka.soonelu.plugins.config.GsonConfig;
import ltd.kafka.soonelu.plugins.constant.StrState;
import ltd.kafka.soonelu.plugins.utils.gen.JsonUtils;
import ltd.kafka.soonelu.plugins.utils.gson.GsonPsiClassUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author soonelu
 */
public class JsonDialog extends JFrame implements ConvertBridge.Operator {

    private CardLayout cardLayout;
    private JPanel contentPane2;
    @ApiModelProperty(value = "ok按钮")
    private JButton okButton;
    @ApiModelProperty(value = "cancel按钮")
    private JButton cancelButton;
    private JLabel errorLb;
    private JTextPane editTp;
    @ApiModelProperty(value = "设置按钮")
    private JButton settingButton;
    private JLabel generateClassLb;
    private JTextField generateClassTf;
    private JPanel generateClassP;
    private JButton formatBtn;
    @Getter
    @Setter
    private PsiClass cls;
    @Setter
    private PsiFile file;
    @Setter
    private Project project;
    private String errorInfo = null;
    private String currentClass = null;

    public JsonDialog(PsiClass cls, PsiFile file, Project project) throws HeadlessException {
        this.cls = cls;
        this.file = file;
        this.project = project;
        setContentPane(contentPane2);
        setTitle("JsonFormat");
        getRootPane().setDefaultButton(okButton);
        this.setAlwaysOnTop(true);
        initGeneratePanel(file);
        initListener();
    }

    private void initListener() {

        okButton.addActionListener(e -> {
            if (generateClassTf.isFocusOwner()) {
                editTp.requestFocus(true);
            } else {
                onOk();
            }
        });

        formatBtn.addActionListener(e -> editTp.setText(JsonUtils.formatJson(editTp.getText())));
        editTp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    onOk();
                }
            }
        });

        generateClassP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    editTp.requestFocus(true);
                }
            }
        });
        errorLb.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (errorInfo != null) {
                    ErrorDialog errorDialog = new ErrorDialog(errorInfo);
                    errorDialog.setSize(800, 600);
                    errorDialog.setLocationRelativeTo(null);
                    errorDialog.setVisible(true);
                }
            }
        });
        cancelButton.addActionListener(e -> onCancel());
        settingButton.addActionListener(e -> openSettingDialog());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane2.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void initGeneratePanel(PsiFile file) {

        cardLayout = (CardLayout) generateClassP.getLayout();
        generateClassTf.setBackground(errorLb.getBackground());
        currentClass = ((PsiJavaFileImpl) file).getPackageName() + "." + file.getName().split("\\.")[0];
        generateClassLb.setText(currentClass);
        generateClassTf.setText(currentClass);
        generateClassTf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                cardLayout.next(generateClassP);
                if (TextUtils.isEmpty(generateClassTf.getText())) {
                    generateClassLb.setText(currentClass);
                    generateClassTf.setText(currentClass);
                } else {
                    generateClassLb.setText(generateClassTf.getText());
                }
            }
        });

        generateClassLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                cardLayout.next(generateClassP);
                if (generateClassLb.getText().equals(currentClass)
                        && !TextUtils.isEmpty(GsonConfig.getInstant().getEntityPackName())
                        && !"null".equals(GsonConfig.getInstant().getEntityPackName())) {

                    generateClassLb.setText(GsonConfig.getInstant().getEntityPackName());
                    generateClassTf.setText(GsonConfig.getInstant().getEntityPackName());
                }
                generateClassTf.requestFocus(true);
            }

        });
    }

    /**
     * ok按钮调用
     */
    private void onOk() {

        this.setAlwaysOnTop(false);
        String jsonStr = editTp.getText().trim();
        if (TextUtils.isEmpty(jsonStr)) {
            return;
        }
        String generateClassName = generateClassTf.getText().replaceAll(" ", "").replaceAll(".java$", "");
        if (TextUtils.isEmpty(generateClassName) || generateClassName.endsWith(StrState.KEY_DOT)) {
            Toast.make(project, generateClassP, MessageType.ERROR, "the path is not allowed");
            return;
        }
        PsiClass generateClass;
        if (!currentClass.equals(generateClassName)) {
            generateClass = GsonPsiClassUtil.exist(file, generateClassTf.getText());
        } else {
            generateClass = cls;
        }

        new ConvertBridge(this, jsonStr, file, project, generateClass,
                cls, generateClassName).run();
    }

    /**
     * cancel按钮回调
     */
    private void onCancel() {
        dispose();
    }

    /**
     * 打开设置对话框
     */
    private void openSettingDialog() {
        SettingDialog settingDialog = new SettingDialog(project);
        settingDialog.setSize(800, 720);
        settingDialog.setLocationRelativeTo(null);
        settingDialog.setVisible(true);
    }

    @Override
    public void cleanErrorInfo() {
        errorInfo = null;
    }

    @Override
    public void setErrorInfo(String error) {
        errorInfo = error;
    }

    @Override
    public void showError(ConvertBridge.Error err) {
        switch (err) {
            case DATA_ERROR:
                errorLb.setText("data err !!");
                if (GsonConfig.getInstant().isToastError()) {
                    Toast.make(project, errorLb, MessageType.ERROR, "click to see details");
                }
                break;
            case PARSE_ERROR:
                errorLb.setText("parse err !!");
                if (GsonConfig.getInstant().isToastError()) {
                    Toast.make(project, errorLb, MessageType.ERROR, "click to see details");
                }
                break;
            case PATH_ERROR:
                Toast.make(project, generateClassP, MessageType.ERROR, "the path is not allowed");
                break;
            default:
                break;
        }
    }

}
