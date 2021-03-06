package ltd.kafka.soonelu.plugins.utils.gson;

/**
 * @author soonelu
 */
public class Try {

    public static void run(TryListener tryListener) {
        try {
            tryListener.run();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                tryListener.runAgain();
            } catch (Exception e1) {
                e1.printStackTrace();
                try {
                    tryListener.error();
                }catch (Exception e3){
                    e3.printStackTrace();
                }
            }
        }
    }

    public  interface TryListener {
        void run();
        void runAgain();
        void error();
    }

}
