package com.eithan.oreutils.commands;

import java.util.ArrayList;
import java.util.List;

import com.eithan.oreutils.config.BlockModClientConfigs;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;

public class RemoveBlockedWordCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("bl").then(Commands.literal("remove").then(Commands.argument("Word", StringArgumentType.string()).executes(command -> {
            List<? extends String> currentBlockedWords = BlockModClientConfigs.Blocked_Words.get();
            List<String> updatedBlockedWords = new ArrayList<>(currentBlockedWords);
            int index = updatedBlockedWords.indexOf(StringArgumentType.getString(command, "Word").toLowerCase());
            if(index != -1) {
            	updatedBlockedWords.remove(index);
            	Minecraft.getInstance().player.sendMessage(new TextComponent("removed: " + StringArgumentType.getString(command, "Word")), Util.NIL_UUID);
            } else {
            	Minecraft.getInstance().player.sendMessage(new TextComponent("failed: word not found"), Util.NIL_UUID);
            }
            BlockModClientConfigs.Blocked_Words.set(updatedBlockedWords);
            BlockModClientConfigs.SPEC.save();
            return Command.SINGLE_SUCCESS;
        }))));
    }
}
