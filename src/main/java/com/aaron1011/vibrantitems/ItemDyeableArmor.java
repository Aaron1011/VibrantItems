package com.aaron1011.vibrantitems;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemDyeableArmor extends ItemArmor {

    public ItemDyeableArmor(ArmorMaterial material, int renderIndex, int armorType) {
        super(material, renderIndex, armorType);
    }

    @Override
    public boolean hasColor(ItemStack itemStack) {
        return (!itemStack.hasTagCompound() ? false : (!itemStack.getTagCompound().hasKey("display", 10) ? false : itemStack.getTagCompound().getCompoundTag("display").hasKey("color", 3)));
    }

    @Override
    public int getColor(ItemStack itemStack) {
        NBTTagCompound nbttagcompound = itemStack.getTagCompound();

        if (nbttagcompound != null)
        {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (nbttagcompound1 != null && nbttagcompound1.hasKey("color", 3))
            {
                return nbttagcompound1.getInteger("color");
            }
        }

        return 10511680;
    }

    @Override
    public void removeColor(ItemStack itemStack) {
        NBTTagCompound nbttagcompound = itemStack.getTagCompound();

        if (nbttagcompound != null)
        {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (nbttagcompound1.hasKey("color"))
            {
                nbttagcompound1.removeTag("color");
            }
        }
    }

    @Override
    public void func_82813_b(ItemStack itemStack, int color) {
        NBTTagCompound nbttagcompound = itemStack.getTagCompound();

        if (nbttagcompound == null)
        {
            nbttagcompound = new NBTTagCompound();
            itemStack.setTagCompound(nbttagcompound);
        }

        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

        if (!nbttagcompound.hasKey("display", 10))
        {
            nbttagcompound.setTag("display", nbttagcompound1);
        }

        nbttagcompound1.setInteger("color", color);
    }
}
