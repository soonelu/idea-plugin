package ltd.kafka.soonelu.plugins.ui.gen;

import com.fasterxml.jackson.core.type.TypeReference;
import com.intellij.openapi.options.Configurable;
import ltd.kafka.soonelu.plugins.config.GenSettings;
import ltd.kafka.soonelu.plugins.entity.gen.ColumnConfig;
import ltd.kafka.soonelu.plugins.entity.gen.ColumnConfigGroup;
import ltd.kafka.soonelu.plugins.entity.gen.ColumnConfigType;
import ltd.kafka.soonelu.plugins.utils.gen.CloneUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * 表设置面板
 *
 * @author soonelu
 */
public class TableSettingPanel extends AbstractTableGroupPanel<ColumnConfigGroup, ColumnConfig> implements Configurable {
    private GenSettings genSettings = GenSettings.getInstance();

    TableSettingPanel() {
        super(CloneUtils.cloneByJson(GenSettings.getInstance().getColumnConfigGroupMap(), new TypeReference<Map<String, ColumnConfigGroup>>() {}), GenSettings.getInstance().getCurrColumnConfigGroupName());
    }

    @Override
    protected Object[] toRow(ColumnConfig item) {
        return new Object[]{item.getTitle(), item.getType().name(), item.getSelectValue()};
    }

    @Override
    protected ColumnConfig toItem(Object[] rowData) {
        return new ColumnConfig((String) rowData[0], ColumnConfigType.valueOf((String) rowData[1]), (String) rowData[2]);
    }

    @Override
    protected String getItemName(ColumnConfig item) {
        return item.getTitle();
    }

    @Override
    protected ColumnConfig createItem(String value) {
        return new ColumnConfig(value, ColumnConfigType.TEXT);
    }

    @Override
    protected ColumnConfig[] initColumn() {
        ColumnConfig[] columnConfigs = new ColumnConfig[3];
        columnConfigs[0] = new ColumnConfig("title", ColumnConfigType.TEXT);
        columnConfigs[1] = new ColumnConfig("type", ColumnConfigType.SELECT, "TEXT,SELECT,BOOLEAN");
        columnConfigs[2] = new ColumnConfig("selectValue", ColumnConfigType.TEXT);
        return columnConfigs;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Table Editor Config";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return getMainPanel();
    }

    @Override
    public boolean isModified() {
        refresh();
        return !genSettings.getColumnConfigGroupMap().equals(group) || !genSettings.getCurrColumnConfigGroupName().equals(currGroupName);
    }

    @Override
    public void apply() {
        genSettings.setColumnConfigGroupMap(group);
        genSettings.setCurrColumnConfigGroupName(currGroupName);
    }

    @Override
    public void reset() {
        this.group = CloneUtils.cloneByJson(genSettings.getColumnConfigGroupMap(), new TypeReference<Map<String, ColumnConfigGroup>>() {});
        this.currGroupName = genSettings.getCurrColumnConfigGroupName();
        init();
    }
}
