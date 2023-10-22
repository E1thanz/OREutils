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

public class AutoWBCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("autowb").then(Commands.argument("bool", BoolArgumentType.bool()).executes(command -> {
            BlockModClientConfigs.AutoWB.set(BoolArgumentType.getBool(command, "bool"));
            Minecraft.getInstance().player.sendMessage(new TextComponent("auto wb set to " + BlockModClientConfigs.AutoWB.get()), Util.NIL_UUID);
            BlockModClientConfigs.SPEC.save();
            return Command.SINGLE_SUCCESS;
        })));
    }
}
