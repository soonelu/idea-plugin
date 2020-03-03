package ltd.kafka.soonelu.plugins.utils;

import javax.swing.*;

/**
 * ui工具类
 *
 * @author soonelu
 */
public class UiUtils {

    /**
     * 获取当前下拉框中选中的项目
     *
     * @return 选中项
     */
    public static <E> E getDropListSelect(JComboBox<E> box) {
        return box.getItemAt(box.getSelectedIndex());
    }
}
