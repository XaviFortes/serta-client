/*

 */

package meteordevelopment.meteorclient.systems.modules;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Category {
    public final String name;
    public final ItemStack icon;
    public final int color;
    private final int nameHash;

    public Category(String name, ItemStack icon, int color) {
        this.name = name;
        this.nameHash = name.hashCode();
        this.icon = icon == null ? Items.AIR.getDefaultStack() : icon;
        this.color = color;
    }
    public Category(String name, int color) {
        this(name, null, color);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return nameHash == category.nameHash;
    }

    @Override
    public int hashCode() {
        return nameHash;
    }
}
