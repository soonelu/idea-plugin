package ltd.kafka.soonelu.plugins.utils.gen;

import com.intellij.database.psi.DbTable;
import lombok.Data;

import java.util.List;

/**
 * 缓存数据工具类
 *
 * @author soonelu
 */
@Data
public class CacheDataUtils {
    private volatile static CacheDataUtils cacheDataUtils;

    /**
     * 单例模式
     */
    public static CacheDataUtils getInstance() {
        if (cacheDataUtils == null) {
            synchronized (CacheDataUtils.class) {
                if (cacheDataUtils == null) {
                    cacheDataUtils = new CacheDataUtils();
                }
            }
        }
        return cacheDataUtils;
    }

    private CacheDataUtils() {
    }

    /**
     * 当前选中的表
     */
    private DbTable selectDbTable;
    /**
     * 所有选中的表
     */
    private List<DbTable> dbTableList;
}
