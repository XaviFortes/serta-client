/*

 */

package meteordevelopment.meteorclient.events.game;

public class WindowResizedEvent {
    private static final WindowResizedEvent INSTANCE = new WindowResizedEvent();

    public static WindowResizedEvent get() {
        return INSTANCE;
    }
}
