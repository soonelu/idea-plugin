package ltd.kafka.soonelu.plugins.ui.gson;


import ltd.kafka.soonelu.plugins.config.GsonConfig;
import ltd.kafka.soonelu.plugins.constant.Constant;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author soonelu
 */
public class EditDialog extends JFrame {

    private JPanel contentPane;
    private JButton okButton;
    private JButton cancelButton;
    private JTextPane editTp;
    private JButton resetButton;
    private JLabel titleLb;
    private String titleName;
    private String editStr;
    private Type type;

    EditDialog(Type type) {
        this.type = type;
        setContentPane(contentPane);
        this.setAlwaysOnTop(true);
        getRootPane().setDefaultButton(okButton);
        okButton.addActionListener(e -> onOk());
        cancelButton.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> onCancel()
                , KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0)
                , JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setTitle("Convert method");

        switch (type) {
            case OBJECT_FROM_DATA:
                titleName = "objectFromData(Object data)";
                editStr = GsonConfig.getInstant().getObjectFromDataStr();
                break;
            case OBJECT_FROM_DATA1:
                titleName = "objectFromData(Object data,String key)";
                editStr = GsonConfig.getInstant().getObjectFromDataStr1();
                break;
            case ARRAY_FROM_DATA:
                titleName = "arrayFromData(Object data)";
                editStr = GsonConfig.getInstant().getArrayFromDataStr();
                break;
            case ARRAY_FROM_DATA1:
                titleName = "arrayFromData(Object data,String key)";
                editStr = GsonConfig.getInstant().getArrayFromData1Str();
                break;
        }
        titleLb.setText(titleName);
        editTp.setText(editStr);
        resetButton.addActionListener(actionEvent -> resetAction());
    }

    private void resetAction() {

        switch (type) {
            case OBJECT_FROM_DATA:
                editTp.setText(Constant.objectFromObject);
                break;
            case OBJECT_FROM_DATA1:
                editTp.setText(Constant.objectFromObject1);
                break;
            case ARRAY_FROM_DATA:
                editTp.setText(Constant.arrayFromData);
                break;
            case ARRAY_FROM_DATA1:
                editTp.setText(Constant.arrayFromData1);
                break;
        }
    }

    private void onOk() {

        switch (type) {
            case OBJECT_FROM_DATA:
                GsonConfig.getInstant().saveObjectFromDataStr(editTp.getText());
                break;
            case OBJECT_FROM_DATA1:
                GsonConfig.getInstant().saveObjectFromDataStr1(editTp.getText());
                break;
            case ARRAY_FROM_DATA:
                GsonConfig.getInstant().saveArrayFromDataStr(editTp.getText());
                break;
            case ARRAY_FROM_DATA1:
                GsonConfig.getInstant().saveArrayFromData1Str(editTp.getText());
                break;
        }

        dispose();
    }

    private void onCancel() {

        dispose();
    }


    public enum Type {
        /**
         * 从数据中获取对象
         */
        OBJECT_FROM_DATA,
        /**
         * 从数据1中获取对象
         */
        OBJECT_FROM_DATA1,
        /**
         * 从数组中获取对象
         */
        ARRAY_FROM_DATA,
        /**
         * 从数组1中获取对象
         */
        ARRAY_FROM_DATA1
    }

}
