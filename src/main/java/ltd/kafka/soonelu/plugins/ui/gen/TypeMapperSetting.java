package ltd.kafka.soonelu.plugins.ui.gen;

import com.fasterxml.jackson.core.type.TypeReference;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.ui.Messages;
import io.swagger.annotations.ApiModelProperty;
import ltd.kafka.soonelu.plugins.config.GenSettings;
import ltd.kafka.soonelu.plugins.constant.MsgValue;
import ltd.kafka.soonelu.plugins.entity.gen.TypeMapper;
import ltd.kafka.soonelu.plugins.entity.gen.TypeMapperGroup;
import ltd.kafka.soonelu.plugins.entity.gen.TypeMapperModel;
import ltd.kafka.soonelu.plugins.utils.StringUtils;
import ltd.kafka.soonelu.plugins.utils.gen.CloneUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * 类型映射设置
 *
 * @author soonelu
 */
public class TypeMapperSetting implements Configurable {
    @ApiModelProperty(value = "主面板")
    private JPanel mainPanel;
    @ApiModelProperty(value = "类型映射分组切换下拉框")
    private JComboBox<String> typeMapperComboBox;
    @ApiModelProperty(value = "分组复制按钮")
    private JButton typeMapperCopyButton;
    @ApiModelProperty(value = "类型映射表")
    private JTable typeMapperTable;
    @ApiModelProperty(value = "添加映射按钮")
    private JButton addButton;
    @ApiModelProperty(value = "移除映射按钮")
    private JButton removeButton;
    @ApiModelProperty(value = "删除分组按钮")
    private JButton deleteButton;
    @ApiModelProperty(value = "是否初始化完成")
    private boolean init;
    @ApiModelProperty(value = "类型映射表模型")
    private TypeMapperModel typeMapperModel;
    @ApiModelProperty(value = "当前选中分组")
    private String currGroupName;
    @ApiModelProperty(value = "类型映射分组集合")
    private Map<String, TypeMapperGroup> typeMapperGroupMap;
    @ApiModelProperty(value = "全局配置服务")
    private GenSettings genSettings;


    TypeMapperSetting(GenSettings genSettings) {
        this.genSettings = genSettings;
        this.typeMapperGroupMap = CloneUtils.cloneByJson(genSettings.getTypeMapperGroupMap(),
                new TypeReference<Map<String, TypeMapperGroup>>() {
                });
        this.currGroupName = genSettings.getCurrTypeMapperGroupName();
        //添加类型
        addButton.addActionListener(e -> typeMapperModel.addRow(new TypeMapper("demoColumn", "java.lang.Object")));

        //移除类型
        removeButton.addActionListener(e -> {
            int[] selectRows = typeMapperTable.getSelectedRows();
            if (selectRows == null || selectRows.length == 0) {
                return;
            }
            if (!MessageDialogBuilder.yesNo(MsgValue.TITLE_INFO, "Confirm Delete Selected Item?").isYes()) {
                return;
            }
            // 从后面往前面移除，防止下标错位问题。
            for (int i = selectRows.length - 1; i >= 0; i--) {
                typeMapperModel.removeRow(selectRows[i]);
            }
        });

        //切换分组
        typeMapperComboBox.addActionListener(e -> {
            if (!init) {
                return;
            }
            String value = (String) typeMapperComboBox.getSelectedItem();
            if (value == null) {
                return;
            }
            if (currGroupName.equals(value)) {
                return;
            }
            currGroupName = value;
            refresh();
        });

        //复制分组按钮
        typeMapperCopyButton.addActionListener(e -> {
            String value = Messages.showInputDialog("Group Name:", "Input Group Name:", Messages.getQuestionIcon(),
                    currGroupName + " Copy", new InputValidator() {
                        @Override
                        public boolean checkInput(String inputString) {
                            return !StringUtils.isEmpty(inputString) && !typeMapperGroupMap.containsKey(inputString);
                        }

                        @Override
                        public boolean canClose(String inputString) {
                            return this.checkInput(inputString);
                        }
                    });
            if (value == null) {
                return;
            }
            // 克隆对象
            TypeMapperGroup typeMapperGroup = CloneUtils.cloneByJson(typeMapperGroupMap.get(currGroupName));
            typeMapperGroup.setName(value);
            typeMapperGroupMap.put(value, typeMapperGroup);
            currGroupName = value;
            refresh();
        });

        //删除分组
        deleteButton.addActionListener(e -> {
            if (MessageDialogBuilder.yesNo(MsgValue.TITLE_INFO,
                    "Confirm Delete Group " + typeMapperComboBox.getSelectedItem() + "?").isYes()) {
                if (GenSettings.DEFAULT_NAME.equals(currGroupName)) {
                    Messages.showWarningDialog("Can't Delete Default Group!", MsgValue.TITLE_INFO);
                    return;
                }
                typeMapperGroupMap.remove(currGroupName);
                currGroupName = GenSettings.DEFAULT_NAME;
                refresh();
            }
        });

        // 初始化操作
        init();
    }


    /**
     * 初始化方法
     */
    private void init() {
        //初始化表格
        this.typeMapperModel = new TypeMapperModel();
        this.typeMapperTable.setModel(typeMapperModel);
        refresh();
    }

    /**
     * 刷新方法
     */
    private void refresh() {
        init = false;
        //初始化下拉框
        this.typeMapperComboBox.removeAllItems();
        typeMapperGroupMap.keySet().forEach(this.typeMapperComboBox::addItem);
        this.typeMapperComboBox.setSelectedItem(this.currGroupName);
        this.typeMapperModel.init(this.typeMapperGroupMap.get(currGroupName).getElementList());
        init = true;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Type Mapper";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return !typeMapperGroupMap.equals(genSettings.getTypeMapperGroupMap()) || !currGroupName.equals(genSettings.getCurrTypeMapperGroupName());
    }

    @Override
    public void apply() {
        genSettings.setCurrTypeMapperGroupName(currGroupName);
        genSettings.setTypeMapperGroupMap(typeMapperGroupMap);
    }

    @Override
    public void reset() {
        this.typeMapperGroupMap = CloneUtils.cloneByJson(genSettings.getTypeMapperGroupMap(),
                new TypeReference<Map<String, TypeMapperGroup>>() {
                });
        this.currGroupName = genSettings.getCurrTypeMapperGroupName();
        init();
    }
}
