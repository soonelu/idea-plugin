package ltd.kafka.soonelu.plugins.ui.ut;

import io.swagger.annotations.ApiModelProperty;
import ltd.kafka.soonelu.plugins.constant.ComEnum;
import ltd.kafka.soonelu.plugins.utils.DateUtils;
import ltd.kafka.soonelu.plugins.utils.StringUtils;
import ltd.kafka.soonelu.plugins.utils.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author soonelu
 */
public class TimeFormatDialog extends JFrame {
    @ApiModelProperty(value = "秒对应的码值")
    private final static String V_SECONDS
            = ComEnum.UtTime.SECONDS.getValue();

    @ApiModelProperty(value = "毫秒对应的码值")
    private final static String V_MILLS
            = ComEnum.UtTime.MILLS.getValue();

    @ApiModelProperty(value = "暂停按钮状态"
            , notes = "初始false,即运行不暂停")
    private boolean stp;

    @ApiModelProperty(value = "主面板")
    private JPanel mainPanel;
    @ApiModelProperty(value = "当前时间戳")
    private JTextField curtTimeField;
    @ApiModelProperty(value = "暂停按钮")
    private JButton stopBtn;

    @ApiModelProperty(value = "时间戳")
    private JTextField tSourceField;
    @ApiModelProperty(value = "时间戳转字符串可选操作")
    private JComboBox<ComEnum.UtTime> t2sBox;
    @ApiModelProperty(value = "转换后的时间串")
    private JTextField sTargetField;
    @ApiModelProperty(value = "时间错转字符串按钮")
    private JButton t2sBtn;

    @ApiModelProperty(value = "时间串")
    private JTextField sSourceField;
    @ApiModelProperty(value = "转换后的时间戳")
    private JTextField tTargetField;
    @ApiModelProperty(value = "字符串转时间戳可选操作")
    private JComboBox<ComEnum.UtTime> s2tBox;
    @ApiModelProperty(value = "字符串转时间戳按钮")
    private JButton s2tBtn;

    public TimeFormatDialog() {
        init();
        registerListener();
        timer();
    }

    /**
     * 初始化面板
     */
    private void init() {
        String currentTimeMills = String.valueOf(System.currentTimeMillis());
        /*当前时间不可输入*/
        curtTimeField.setEditable(false);
        /*初始化四个时间框*/
        curtTimeField.setText(currentTimeMills);
        tSourceField.setText(currentTimeMills);
        sTargetField.setText(DateUtils.getNowTime());
        sSourceField.setText(DateUtils.getNowTime());
        tTargetField.setText(currentTimeMills);
        /*初始化两个下拉框*/
        s2tBox.addItem(ComEnum.UtTime.MILLS);
        s2tBox.addItem(ComEnum.UtTime.SECONDS);
        t2sBox.addItem(ComEnum.UtTime.MILLS);
        t2sBox.addItem(ComEnum.UtTime.SECONDS);
        /*渲染ui*/
        Dimension dim = new Dimension();
        dim.setSize(150 * 5, 50 * 2.5);
        mainPanel.setPreferredSize(dim);
        setContentPane(mainPanel);
        setTitle("TimeFormat");
        getRootPane().setDefaultButton(s2tBtn);
    }

    /**
     * 注册监听
     */
    private void registerListener() {
        this.setFocusable(true);
        /*按esc退出*/
        this.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        super.keyPressed(e);
                        if (KeyEvent.VK_ESCAPE == e.getKeyChar()) {
                            close();
                        }
                    }
                }
        );
        /*暂停按钮*/
        stopBtn.addActionListener(
                e -> {
                    stopBtn.setText(stp ? "暂停" : "开始");
                    stp = !stp;
                }
        );

        /*当前时间鼠标点击将当前时间替换到时间戳中*/
        curtTimeField.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        tSourceField.setText(curtTimeField.getText());
                    }
                }
        );

        /*时间戳转字符串下拉选中*/
        t2sBox.addActionListener(e -> setCurtT(t2sBox));

        /*时间戳转字符串按钮*/
        t2sBtn.addActionListener(e -> {
            /*操作类型*/
            ComEnum.UtTime operaType = t2sBox.getItemAt(t2sBox.getSelectedIndex());
            if (V_SECONDS.equals(operaType.getValue())) {
                /*选中s*/
                String timestamp = tSourceField.getText();
                timestamp += "000";
                sTargetField.setText(DateUtils.stamp2String(timestamp));
            } else if (V_MILLS.equals(operaType.getValue())) {
                /*选中ms*/
                String timestamp = tSourceField.getText();
                sTargetField.setText(DateUtils.stamp2String(timestamp));
            }
        });

        /*字符串转时间戳按钮*/
        s2tBtn.addActionListener(e -> {
            String time = sSourceField.getText();
            if (StringUtils.isEmpty(time)) {
                return;
            }
            Date date = DateUtils.string2Date(time);

            /*操作类型*/
            ComEnum.UtTime operaType = s2tBox.getItemAt(s2tBox.getSelectedIndex());
            if (V_SECONDS.equals(operaType.getValue())) {
                /*选中s*/
                tTargetField.setText(String.valueOf(date.getTime() / 1000));
            } else if (V_MILLS.equals(operaType.getValue())) {
                /*选中ms*/
                tTargetField.setText(String.valueOf(date.getTime()));
            }
        });
    }

    /**
     * 定时器 1s执行一次
     * 修改当前时间
     */
    private void timer() {

        Timer timer = new Timer();
        long delay = 0;
        long intervalPeriod = 1000;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (stp) {
                    return;
                }
                setCurtT(t2sBox);
            }
        }, delay, intervalPeriod);
    }

    /**
     * 开启面板
     */
    public void open() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * 关闭面板
     */
    public void close() {
        this.dispose();
    }

    /**
     * 获取当前下拉框中选中的项目
     */
    private void setCurtT(JComboBox<ComEnum.UtTime> box) {
        ComEnum.UtTime operaType = UiUtils.getDropListSelect(box);
        if (V_SECONDS.equals(operaType.getValue())) {
            /*选中s*/
            curtTimeField.setText(String.valueOf(System.currentTimeMillis() / 1000));
        } else if (V_MILLS.equals(operaType.getValue())) {
            /*选中ms*/
            curtTimeField.setText(String.valueOf(System.currentTimeMillis()));
        }
    }


}
