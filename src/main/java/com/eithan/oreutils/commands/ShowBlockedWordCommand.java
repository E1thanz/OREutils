package com.eithan.oreutils.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.eithan.oreutils.config.BlockModClientConfigs;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;

import java.util.List;

public class ShowBlockedWordCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("bl").then(Commands.literal("show").executes(command -> {
            List<? extends String> currentBlockedWords = BlockModClientConfigs.Blocked_Words.get();
            Minecraft.getInstance().player.sendMessage(new TextComponent(currentBlockedWords.toString()), Util.NIL_UUID);
            return Command.SINGLE_SUCCESS;
        })));
    }
}
