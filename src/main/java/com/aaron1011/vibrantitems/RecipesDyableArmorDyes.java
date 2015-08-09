package com.aaron1011.vibrantitems;

import com.google.common.collect.Lists;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipesArmorDyes;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * This is exactly the same as {@link RecipesArmorDyes}, except it handles dying
 * armor *other* than leather.
 */
public class RecipesDyableArmorDyes extends RecipesArmorDyes {

    @Override
    public boolean matches(InventoryCrafting p_77569_1_, World worldIn)
    {
        ItemStack itemstack = null;
        ArrayList arraylist = Lists.newArrayList();

        for (int i = 0; i < p_77569_1_.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = p_77569_1_.getStackInSlot(i);

            if (itemstack1 != null)
            {
                if (itemstack1.getItem() instanceof ItemArmor)
                {
                    ItemArmor itemarmor = (ItemArmor)itemstack1.getItem();

                    if (itemarmor.getArmorMaterial() == ItemArmor.ArmorMaterial.LEATHER || itemstack != null)
                    {
                        return false;
                    }

                    itemstack = itemstack1;
                }
                else
                {
                    if (itemstack1.getItem() != Items.dye)
                    {
                        return false;
                    }

                    arraylist.add(itemstack1);
                }
            }
        }

        return itemstack != null && !arraylist.isEmpty();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
    {
        ItemStack itemstack = null;
        int[] aint = new int[3];
        int i = 0;
        int j = 0;
        ItemArmor itemarmor = null;
        int k;
        int l;
        float f;
        float f1;
        int l1;

        for (k = 0; k < p_77572_1_.getSizeInventory(); ++k)
        {
            ItemStack itemstack1 = p_77572_1_.getStackInSlot(k);

            if (itemstack1 != null)
            {
                if (itemstack1.getItem() instanceof ItemArmor)
                {

                    itemarmor = (ItemArmor)itemstack1.getItem();

                    if (itemarmor.getArmorMaterial() == ItemArmor.ArmorMaterial.LEATHER || itemstack != null)
                    {
                        return null;
                    }

                    itemstack = itemstack1.copy();
                    itemstack.stackSize = 1;

                    if (!(itemstack1.getItem() instanceof ItemDyeableArmor)) {
                        itemarmor = VibrantItems.ARMOR_MAP.get(itemstack.getItem());
                        itemstack.setItem(itemarmor);
                    }

                    if (itemarmor.hasColor(itemstack1))
                    {
                        l = itemarmor.getColor(itemstack);
                        f = (float)(l >> 16 & 255) / 255.0F;
                        f1 = (float)(l >> 8 & 255) / 255.0F;
                        float f2 = (float)(l & 255) / 255.0F;
                        i = (int)((float)i + Math.max(f, Math.max(f1, f2)) * 255.0F);
                        aint[0] = (int)((float)aint[0] + f * 255.0F);
                        aint[1] = (int)((float)aint[1] + f1 * 255.0F);
                        aint[2] = (int)((float)aint[2] + f2 * 255.0F);
                        ++j;
                    }
                }
                else
                {
                    if (itemstack1.getItem() != Items.dye)
                    {
                        return null;
                    }

                    float[] afloat = EntitySheep.func_175513_a(EnumDyeColor.func_176766_a(itemstack1.getMetadata()));
                    int j1 = (int)(afloat[0] * 255.0F);
                    int k1 = (int)(afloat[1] * 255.0F);
                    l1 = (int)(afloat[2] * 255.0F);
                    i += Math.max(j1, Math.max(k1, l1));
                    aint[0] += j1;
                    aint[1] += k1;
                    aint[2] += l1;
                    ++j;
                }
            }
        }

        if (itemarmor == null)
        {
            return null;
        }
        else
        {
            k = aint[0] / j;
            int i1 = aint[1] / j;
            l = aint[2] / j;
            f = (float)i / (float)j;
            f1 = (float)Math.max(k, Math.max(i1, l));
            k = (int)((float)k * f / f1);
            i1 = (int)((float)i1 * f / f1);
            l = (int)((float)l * f / f1);
            l1 = (k << 8) + i1;
            l1 = (l1 << 8) + l;
            itemarmor.func_82813_b(itemstack, l1);
            return itemstack;
        }
    }

}
