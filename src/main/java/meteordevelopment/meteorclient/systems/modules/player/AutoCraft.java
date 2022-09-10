package meteordevelopment.meteorclient.systems.modules.player;

import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.ItemListSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.orbit.EventHandler;

import net.minecraft.item.Item;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;

import java.util.ArrayList;
import java.util.List;



public class AutoCraft extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<List<Item>> items = sgGeneral.add(new ItemListSetting.Builder()
        .name("items")
        .description("Items to craft.")
        .defaultValue(new ArrayList<>(0))
        .build()
    );
    
    private final Setting<Boolean> antiDesync = sgGeneral.add(new BoolSetting.Builder()
        .name("anti-desync")
        .description("Prevents desyncing when crafting.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Boolean> craftAll = sgGeneral.add(new BoolSetting.Builder()
        .name("craft-all")
        .description("Crafts maximum possible amount amount per craft. (shift-clicking)")
        .defaultValue(false)
        .build()
    );

    private final Setting<Boolean> drop = sgGeneral.add(new BoolSetting.Builder()
        .name("drop")
        .description("Automatically drops crafted items. (useful for when not enough inventory space)")
        .defaultValue(false)
        .build()
    );

    public AutoCraft() {
        super(Categories.Player, "auto-craft", "Automatically crafts items.");
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (mc.interactionManager == null) return;
        if (items.get().isEmpty()) return;

        if (!(mc.player.currentScreenHandler instanceof CraftingScreenHandler)) return;
        if (antiDesync.get()) mc.player.getInventory().updateItems();

        CraftingScreenHandler currentScreenHandler = (CraftingScreenHandler) mc.player.currentScreenHandler;
        List<Item> itemList = items.get();
        List<RecipeResultCollection> recipeResultCollectionList  = mc.player.getRecipeBook().getOrderedResults();
        for (RecipeResultCollection recipeResultCollection : recipeResultCollectionList) {
            for (Recipe<?> recipe : recipeResultCollection.getRecipes(true)) {
                if (!itemList.contains(recipe.getOutput().getItem())) continue;
                mc.interactionManager.clickRecipe(currentScreenHandler.syncId, recipe, craftAll.get());
                mc.interactionManager.clickSlot(currentScreenHandler.syncId, 0, 1, drop.get() ? SlotActionType.THROW : SlotActionType.QUICK_MOVE, mc.player);
            }
        }
    }
}