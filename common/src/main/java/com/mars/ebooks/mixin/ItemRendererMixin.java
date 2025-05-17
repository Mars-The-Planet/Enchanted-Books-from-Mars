package com.mars.ebooks.mixin;

import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

import static com.mars.ebooks.Constants.BOOK_FOLDER;


@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Redirect(
            method = "getModel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/ItemModelShaper;getItemModel(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/client/resources/model/BakedModel;"
            )
    )
    private BakedModel redirectGetItemModel(ItemModelShaper self, ItemStack stack) {
        if (stack.is(Items.ENCHANTED_BOOK)) {
            Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(stack);
            if (!enchants.isEmpty()) {
                Enchantment first = enchants.keySet().iterator().next();
                ResourceLocation enchRL = BuiltInRegistries.ENCHANTMENT.getKey(first);
                String name = enchRL.getPath();
                ResourceLocation modelID = new ResourceLocation(enchRL.getNamespace(), BOOK_FOLDER + name);

                ModelManager mm = self.getModelManager();
                Map<ResourceLocation, BakedModel> bakedRegistry = ((ModelManagerMixin) mm).getBakedRegistry();
                BakedModel custom = bakedRegistry.get(modelID);

                if (custom != null && custom != mm.getMissingModel()) {
                    return custom;
                }
            }
        }
        return ((ModelShaper) this).itemModelShaper().getItemModel(stack);
    }
}
