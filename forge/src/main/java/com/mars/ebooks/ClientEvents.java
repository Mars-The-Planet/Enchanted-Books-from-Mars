package com.mars.ebooks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

import static com.mars.ebooks.CommonClass.getTextures;
import static com.mars.ebooks.Constants.BOOK_FOLDER;
import static com.mars.ebooks.Constants.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onRegisterModel(ModelRegistryEvent event) {
        ResourceManager rm = Minecraft.getInstance().getResourceManager();
        Set<ResourceLocation> ids = getTextures(rm);
        for (ResourceLocation id : ids) {
            ResourceLocation modelLoc = new ResourceLocation(id.getNamespace(), BOOK_FOLDER + id.getPath());
            ForgeModelBakery.addSpecialModel(modelLoc);
        }
    }
}
