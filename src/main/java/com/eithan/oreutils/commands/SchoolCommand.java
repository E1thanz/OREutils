package com.eithan.oreutils.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class SchoolCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("school").executes(command -> {
            assert Minecraft.getInstance().player != null;
            Minecraft.getInstance().player.chat("/school");
            return Command.SINGLE_SUCCESS;
        }));
        dispatcher.register(Commands.literal("s").executes(command -> {
            assert Minecraft.getInstance().player != null;
            Minecraft.getInstance().player.chat("/school");
            return Command.SINGLE_SUCCESS;
        }));
    }
}
