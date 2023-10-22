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

public class KickOnSightSetCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("kos").then(Commands.argument("bool", BoolArgumentType.bool()).executes(command -> {
        	BlockModClientConfigs.KickOnSight.set(BoolArgumentType.getBool(command, "bool"));
            Minecraft.getInstance().player.sendMessage(new TextComponent("kick on sight set to " + BlockModClientConfigs.KickOnSight.get()), Util.NIL_UUID);
            BlockModClientConfigs.SPEC.save();
            return Command.SINGLE_SUCCESS;
        })));
    }
}
