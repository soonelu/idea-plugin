package ltd.kafka.soonelu.plugins.constant;

/**
 * @author soonelu
 */
public class ComEnum {

    /**
     * 时间转换工具
     */
    public enum UtTime {
        /**
         * 秒
         */
        SECONDS("秒(s)", "1"),
        /**
         * 毫秒
         */
        MILLS("毫秒(ms)", "2");

        private String name;
        private String value;

        /**
         * @param name  描述
         * @param value 值
         */
        UtTime(String name, String value) {
            this.name = name;
            this.value = value;
        }

        /**
         * 获取枚举值
         *
         * @return 值
         */
        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
