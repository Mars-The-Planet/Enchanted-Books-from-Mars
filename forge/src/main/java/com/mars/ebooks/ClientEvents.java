package com.mars.ebooks;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

import static com.mars.ebooks.CommonClass.getTextures;
import static com.mars.ebooks.Constants.BOOK_FOLDER;
import static com.mars.ebooks.Constants.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onRegisterModel(ModelEvent.RegisterAdditional event) {
        Set<ResourceLocation> ids = getTextures(Minecraft.getInstance().getResourceManager());
        for (ResourceLocation id : ids){
            event.register(id.withPrefix(BOOK_FOLDER));
        }
    }
}
