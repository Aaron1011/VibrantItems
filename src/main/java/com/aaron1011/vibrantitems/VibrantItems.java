package com.aaron1011.vibrantitems;

import com.google.common.collect.ImmutableMap;
import com.google.common.eventbus.Subscribe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Map;

@Mod(modid = "vibrantitems", name = "VibrantItems", version = "1.0.0")
public class VibrantItems {

    public static ItemDyeableArmor DYABLE_CHAINMAIL_HELMET = (ItemDyeableArmor) new ItemDyeableArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 0).setUnlocalizedName("Dyable Chain");
    public static ItemDyeableArmor DYABLE_CHAINMAIL_CHESTPLATE = (ItemDyeableArmor) new ItemDyeableArmor(ItemArmor.ArmorMaterial.CHAIN, 3, 1).setUnlocalizedName("Dyable Chain Chestplate");
    public static ItemDyeableArmor DYABLE_DIAMOND_CHESTPLATE = (ItemDyeableArmor) new ItemDyeableArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 1).setUnlocalizedName("Dyable Diamond");

    public static Map<ItemArmor, ItemDyeableArmor> ARMOR_MAP = new ImmutableMap.Builder<ItemArmor, ItemDyeableArmor>()
            .put(Items.chainmail_helmet, DYABLE_CHAINMAIL_HELMET)
            .put(Items.chainmail_chestplate, DYABLE_CHAINMAIL_CHESTPLATE)
            .put(Items.diamond_chestplate, DYABLE_DIAMOND_CHESTPLATE)
            .build();

    @Mod.EventHandler
    public void onStart(FMLInitializationEvent event) {
        System.out.println("Server started!");

        this.registerItems(event.getSide());

        CraftingManager.getInstance().getRecipeList().add(new RecipesDyableArmorDyes());
    }

    private void registerItems(Side side) {
        GameRegistry.registerItem(DYABLE_CHAINMAIL_HELMET, "dyable_chainmail_helmet");
        GameRegistry.registerItem(DYABLE_CHAINMAIL_CHESTPLATE, "dyable_chainmail_chestplate");
        GameRegistry.registerItem(DYABLE_DIAMOND_CHESTPLATE, "dyable_diamond_chestplate");

        if (side == Side.CLIENT) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DYABLE_CHAINMAIL_HELMET, 0, new ModelResourceLocation("chainmail_helmet", "inventory"));
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DYABLE_CHAINMAIL_CHESTPLATE, 0, new ModelResourceLocation("chainmail_chestplate", "inventory"));
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DYABLE_DIAMOND_CHESTPLATE, 0, new ModelResourceLocation("diamond_chestplate", "inventory"));
        }

    }

}
