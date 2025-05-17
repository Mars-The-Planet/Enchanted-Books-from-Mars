package com.mars.ebooks.mixin;

import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemRenderer.class)
public interface ModelShaper{
    @Accessor("itemModelShaper")
    ItemModelShaper itemModelShaper();
}
