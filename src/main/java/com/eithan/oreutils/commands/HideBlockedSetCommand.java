package com.eithan.oreutils.commands;

import com.eithan.oreutils.config.BlockModClientConfigs;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;

public class HideBlockedSetCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("hb").then(Commands.argument("bool", BoolArgumentType.bool()).executes(command -> {
        	BlockModClientConfigs.HideBlocked.set(BoolArgumentType.getBool(command, "bool"));
            BlockModClientConfigs.SPEC.save();
            Minecraft.getInstance().player.sendMessage(new TextComponent("hide blocked set to " + BlockModClientConfigs.HideBlocked.get()), Util.NIL_UUID);
            return Command.SINGLE_SUCCESS;
        })));
    }
}
