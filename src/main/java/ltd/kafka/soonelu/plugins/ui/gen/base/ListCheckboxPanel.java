package ltd.kafka.soonelu.plugins.ui.gen.base;

import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.ui.components.JBCheckBox;
import io.swagger.annotations.ApiModelProperty;
import ltd.kafka.soonelu.plugins.utils.gen.CollectionUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author soonelu
 */
public class ListCheckboxPanel extends JPanel {
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "原属列表")
    private Collection<String> items;
    @ApiModelProperty(value = "复选框列表")
    private List<JBCheckBox> checkBoxList;

    /**
     * 默认构造方法
     */
    public ListCheckboxPanel(String title, Collection<String> items) {
        // 使用垂直流式布局
        super(new VerticalFlowLayout());
        this.title = title;
        this.items = items;
        this.init();
    }

    /**
     * 初始化操作
     */
    private void init() {
        JTextPane textPane = new JTextPane();
        textPane.setText(title);
        add(textPane);
        if (CollectionUtil.isEmpty(items)) {
            return;
        }
        checkBoxList = new ArrayList<>(items.size());
        for (String item : items) {
            JBCheckBox checkBox = new JBCheckBox(item);
            checkBoxList.add(checkBox);
            add(checkBox);
        }
    }

    /**
     * 获取已选中的元素
     *
     * @return 已选中的元素
     */
    @SuppressWarnings("unchecked")
    public List<String> getSelectedItems() {
        if (CollectionUtil.isEmpty(checkBoxList)) {
            return Collections.EMPTY_LIST;
        }
        List<String> result = new ArrayList<>();
        checkBoxList.forEach(checkBox -> {
            if (checkBox.isSelected()) {
                result.add(checkBox.getText());
            }
        });
        return result;
    }
}
