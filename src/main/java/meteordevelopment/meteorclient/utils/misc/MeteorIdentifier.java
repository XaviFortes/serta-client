/*

 */

package meteordevelopment.meteorclient.utils.misc;

import meteordevelopment.meteorclient.MeteorClient;
import net.minecraft.util.Identifier;

public class MeteorIdentifier extends Identifier {
    public MeteorIdentifier(String path) {
        super(MeteorClient.MOD_ID, path);
    }
}
