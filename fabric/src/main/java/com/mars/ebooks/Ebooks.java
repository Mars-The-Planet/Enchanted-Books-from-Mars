package com.mars.ebooks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.PreparableModelLoadingPlugin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static com.mars.ebooks.CommonClass.getTextures;
import static com.mars.ebooks.Constants.BOOK_FOLDER;

public class Ebooks implements ModInitializer, ClientModInitializer, PreparableModelLoadingPlugin<Set<ResourceLocation>>, PreparableModelLoadingPlugin.DataLoader<Set<ResourceLocation>> {
    @Override
    public void onInitialize() {
        CommonClass.init();
    }

    @Override
    public void onInitializeClient() {
        PreparableModelLoadingPlugin.register(this, this);
    }

    @Override
    public void onInitializeModelLoader(Set<ResourceLocation> resourceLocations, ModelLoadingPlugin.Context context) {
        for (ResourceLocation id : resourceLocations){
            context.addModels(id.withPrefix(BOOK_FOLDER));
        }
    }

    @Override
    public CompletableFuture<Set<ResourceLocation>> load(ResourceManager resourceManager, Executor executor) {
        return CompletableFuture.supplyAsync(()-> getTextures(resourceManager), executor);
    }
}
