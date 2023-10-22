package com.eithan.oreutils.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.eithan.oreutils.config.BlockModClientConfigs;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class AddBlockedWordCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("bl").then(Commands.literal("add").then(Commands.argument("Word", StringArgumentType.string()).executes(command -> {
            List<? extends String> currentBlockedWords = BlockModClientConfigs.Blocked_Words.get();
            List<String> updatedBlockedWords = new ArrayList<>(currentBlockedWords);
            updatedBlockedWords.add(StringArgumentType.getString(command, "Word").toLowerCase());
            Minecraft.getInstance().player.sendMessage(new TextComponent("added: " + StringArgumentType.getString(command, "Word")), Util.NIL_UUID);
            BlockModClientConfigs.Blocked_Words.set(updatedBlockedWords);
            BlockModClientConfigs.SPEC.save();
            return Command.SINGLE_SUCCESS;
        }))));
    }
}
