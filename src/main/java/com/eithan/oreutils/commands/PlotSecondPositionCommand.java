package com.eithan.oreutils.commands;

import com.eithan.oreutils.config.BlockModClientConfigs;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;

public class PlotSecondPositionCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("bpos2").then(Commands.argument("x", DoubleArgumentType.doubleArg()).then(Commands.argument("z", DoubleArgumentType.doubleArg()).executes(command -> {
        	BlockModClientConfigs.SecondPosX.set(DoubleArgumentType.getDouble(command, "x"));
        	BlockModClientConfigs.SecondPosZ.set(DoubleArgumentType.getDouble(command, "z"));
            Minecraft.getInstance().player.sendMessage(new TextComponent("second position set"), Util.NIL_UUID);
            BlockModClientConfigs.SPEC.save();
            return Command.SINGLE_SUCCESS;
        }))));
    }
}