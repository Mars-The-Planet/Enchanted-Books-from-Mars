package com.mars.ebooks;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import static com.mars.ebooks.Constants.MOD_ID;

@Mod(MOD_ID)
public class EnchantedBooks {
    public EnchantedBooks(IEventBus eventBus) {
        CommonClass.init();
    }
}
