package com.terra.undertow;

import com.terra.undertow.UnderTow;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, UnderTow.MOD_ID);



    public static final Supplier<SoundEvent> SLEEPLESS = registerSoundEvent("sleepless");

    public static final Supplier<SoundEvent> BAR_BRAWL = registerSoundEvent("bar_brawl");


    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(UnderTow.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}