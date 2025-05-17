package com.mars.ebooks;

import com.mars.deimos.config.DeimosConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.HashSet;
import java.util.Set;

import static com.mars.ebooks.Constants.MOD_ID;

public class CommonClass {
    static Set<ResourceLocation> getTextures(ResourceManager resourceManager){
        Set<ResourceLocation> IDs = new HashSet<>();
        String folder = "textures/item/ebooks";
        resourceManager.listResources(folder, path -> path.endsWith(".png"))
                .forEach(resLoc -> {
                    String fullPath = resLoc.getPath();
                    String name     = fullPath
                            .substring(folder.length() + 1,
                                    fullPath.length() - ".png".length());
                    IDs.add(new ResourceLocation(resLoc.getNamespace(), name));
                });

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
    public static void init() {
        DeimosConfig.init(MOD_ID, EnchantedBooksConfig.class);
    }
}
