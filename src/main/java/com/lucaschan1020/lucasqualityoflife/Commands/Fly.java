package com.lucaschan1020.lucasqualityoflife.Commands;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;

public class Fly extends CommandBase {
    public Fly() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    @Override
    public String getCommandName() {
        return "fly";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/fly [true|false] Toggle flight mode.";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) {
            return;
        }

        EntityPlayerMP player = (EntityPlayerMP) sender;

        if (args.length == 0) {
            player.capabilities.allowFlying = !player.capabilities.allowFlying;
        } else {
            player.capabilities.allowFlying = Boolean.parseBoolean(args[0]);
        }

        if (!player.onGround) {
            player.capabilities.isFlying = player.capabilities.allowFlying;
        }

        player.sendPlayerAbilities();

        player.addChatComponentMessage(
                new ChatComponentText("Flying " + (player.capabilities.allowFlying ? "enabled" : "disabled")));
    }

    @SubscribeEvent
    public void onLogin(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;

        player.capabilities.allowFlying = true;
        if (!player.onGround) player.capabilities.isFlying = true;
        player.sendPlayerAbilities();
    }

    @SubscribeEvent
    public void onDimensionChanged(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event) {
        event.player.sendPlayerAbilities();
    }
}
