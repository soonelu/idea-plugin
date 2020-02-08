package ltd.kafka.soonelu.plugins.third;

/**
 * @author soonelu
 */
public interface CellProvider {

    String getCellTitle(int index);

    void setValueAt(int column, String text);
}
