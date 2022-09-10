/*

 */

package meteordevelopment.meteorclient.utils.misc;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;

public class Version {
    private final String string;
    private final int[] numbers;

    public Version(String string) {
        this.string = string;
        this.numbers = new int[3];

        String[] split = string.split("\\.");
        if (split.length != 3) throw new IllegalArgumentException("[Serta] Version string needs to have 3 numbers.");

        for (int i = 0; i < 3; i++) {
            try {
                numbers[i] = Integer.parseInt(split[i]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[Serta] Failed to parse version string.");
            }
        }
    }

    public static void init() {
        meteordevelopment.meteorclient.MeteorClient.EVENT_BUS.subscribe(Version.class);
    }

    public boolean isHigherThan(Version version) {
        for (int i = 0; i < 3; i++) {
            if (numbers[i] > version.numbers[i]) return true;
            if (numbers[i] < version.numbers[i]) return false;
        }

        return false;
    }

    @Override
    public String toString() {
        return string;
    }

    public static Version get() {
        return new Version(FabricLoader.getInstance().getModContainer("meteor-client").get().getMetadata().getVersion().getFriendlyString());
    }

    public static Integer getDev() {
        return 0;
    }

    public static String getDevString() {
        if (getDev() < 1) return "";
        else return "Dev-" + getDev();
    }

    public static String getStylized() {
        if (getDev() < 1) return "v" + get();
        else return "v" + get() + " " + getDevString();
    }

    public static String getMinecraft(){
        return SharedConstants.getGameVersion().getName();
    }

    public static Integer getMinecraftProtocol(){
        return SharedConstants.getGameVersion().getProtocolVersion();
    }


}
