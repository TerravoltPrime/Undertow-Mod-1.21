package net.terra.undertow.item;

import net.terra.undertow.UnderTow;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UnderTow.MOD_ID);




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}