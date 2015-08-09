package com.aaron1011.vibrantitems;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "vibrantitems", name = "VibrantItems", version = "1.0.0", clientSideOnly = true)
public class VibrantItems {

    @SubscribeEvent
    public void onStart(FMLServerStartedEvent event) {
        System.out.println("Server started!");
    }

}
