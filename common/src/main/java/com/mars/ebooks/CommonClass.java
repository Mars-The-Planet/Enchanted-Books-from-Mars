package com.mars.ebooks;

import com.mars.deimos.config.DeimosConfig;
import com.mars.ebooks.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Items;

import java.util.HashSet;
import java.util.Set;

import static com.mars.ebooks.Constants.MOD_ID;

public class CommonClass {
    public static String BOOK_FOLDER = "item/ebooks/";
    public static void init() {
        DeimosConfig.init(MOD_ID, EnchantedBooksConfig.class);
    }

    static Set<ResourceLocation> getTextures(ResourceManager resourceManager){
        Set<ResourceLocation> IDs = new HashSet<>();
        String folder = "textures/item/ebooks";
        for(ResourceLocation id : resourceManager.listResources(folder, id -> id.getPath().endsWith(".png")).keySet()){
            String path = id.getPath();
            path = path.substring(folder.length()+1, path.length()-".png".length());
            IDs.add(new ResourceLocation(id.getNamespace(), path));
        }
        return IDs;
    }

    public static String createItemModelJson(ResourceLocation id) {
        return "{\n" +
                "  \"parent\": \"minecraft:item/generated" + "\",\n" +
                "  \"textures\": {\n" +
                "    \"layer0\": \"" + id.toString() + "\"\n" +
                "  }\n" +
                "}";
    }
}
