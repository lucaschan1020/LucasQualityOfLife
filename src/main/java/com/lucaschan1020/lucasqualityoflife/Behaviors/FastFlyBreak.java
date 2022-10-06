package com.lucaschan1020.lucasqualityoflife.Behaviors;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class FastFlyBreak {
    public FastFlyBreak() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBlockBreak(PlayerEvent.BreakSpeed event) {
        if (!event.entityPlayer.onGround) {
            event.newSpeed = event.originalSpeed * 5;
        }
    }
}
