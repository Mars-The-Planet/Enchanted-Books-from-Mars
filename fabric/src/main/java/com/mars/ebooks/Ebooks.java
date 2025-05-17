package com.mars.ebooks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.Set;
import java.util.function.Consumer;

import static com.mars.ebooks.CommonClass.getTextures;
import static com.mars.ebooks.Constants.BOOK_FOLDER;

public class Ebooks implements ModInitializer, ClientModInitializer{
    @Override
    public void onInitialize() {
        CommonClass.init();
    }

    @Override
    public void onInitializeClient() {
        ModelLoadingRegistry.INSTANCE.registerModelProvider(
                (ResourceManager resourceManager, Consumer<ResourceLocation> out) -> {
                    Set<ResourceLocation> textures = getTextures(resourceManager);
                    for (ResourceLocation texId : textures) {
                        ResourceLocation modelId = new ResourceLocation(
                                texId.getNamespace(),
                                BOOK_FOLDER + texId.getPath()
                        );
                        out.accept(modelId);
                    }
                }
        );
    }
}
