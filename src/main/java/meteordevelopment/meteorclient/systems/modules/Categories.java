/*

 */

package meteordevelopment.meteorclient.systems.modules;

import meteordevelopment.meteorclient.MeteorClient;
import meteordevelopment.meteorclient.addons.AddonManager;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.utils.render.color.Color;
import net.minecraft.item.Items;

public class Categories {
    public static final Category Combat = new Category("Combat", Items.GOLDEN_SWORD.getDefaultStack(), Color.fromRGBA(225, 0, 0, 255));
    public static final Category Player = new Category("Player", Items.ARMOR_STAND.getDefaultStack(), Color.fromRGBA(0, 125, 255, 255));
    public static final Category Movement = new Category("Movement", Items.DIAMOND_BOOTS.getDefaultStack(), Color.fromRGBA(125, 255, 255, 255));
    public static final Category Render = new Category("Render", Items.GLASS.getDefaultStack(), Color.fromRGBA(245, 255, 100, 255));
    public static final Category World = new Category("World", Items.GRASS_BLOCK.getDefaultStack(), Color.fromRGBA(0, 150, 0, 255));
    public static final Category Misc = new Category("Misc", Items.LAVA_BUCKET.getDefaultStack(), Color.fromRGBA(255, 255, 255, 255));
    public static final Category Client = new Category("Client", Items.COMMAND_BLOCK.getDefaultStack(), Color.fromRGBA(MeteorClient.INSTANCE.SERTA_COLOR.r, MeteorClient.INSTANCE.SERTA_COLOR.g, MeteorClient.INSTANCE.SERTA_COLOR.b, 255));

    public static boolean REGISTERING;

    public static void init() {
        REGISTERING = true;

        // Meteor
        Modules.registerCategory(Combat);
        Modules.registerCategory(Player);
        Modules.registerCategory(Movement);
        Modules.registerCategory(Render);
        Modules.registerCategory(World);
        Modules.registerCategory(Misc);
        Modules.registerCategory(Client);

        // Addons
        AddonManager.ADDONS.forEach(MeteorAddon::onRegisterCategories);

        REGISTERING = false;
    }
}
