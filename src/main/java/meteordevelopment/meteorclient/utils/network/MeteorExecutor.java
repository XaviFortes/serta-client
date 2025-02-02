/*

 */

package meteordevelopment.meteorclient.utils.network;

import meteordevelopment.meteorclient.utils.PreInit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MeteorExecutor {
    public static ExecutorService executor;

    @PreInit
    public static void init() {
        executor = Executors.newSingleThreadExecutor();
    }

    public static void execute(Runnable task) {
        executor.execute(task);
    }
}
