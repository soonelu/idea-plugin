package ltd.kafka.soonelu.plugins.ui.gson;

import javax.swing.*;

/**
 * @author soonelu
 */
public class ErrorDialog extends JFrame {

    private JPanel contentPane;
    private JTextPane editTp;
    private JButton okButton;
    private JScrollPane scrollPane;

    ErrorDialog(String errorInfo) {
        setContentPane(contentPane);
        setTitle("Error Info");
        getRootPane().setDefaultButton(okButton);
        this.setAlwaysOnTop(true);
        editTp.setText(errorInfo);
        okButton.addActionListener(e -> dispose());
        editTp.setCaretPosition(0);

    }
}
