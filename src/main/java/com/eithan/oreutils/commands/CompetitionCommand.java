package com.eithan.oreutils.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CompetitionCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("competition").executes(command -> {
            assert Minecraft.getInstance().player != null;
            Minecraft.getInstance().player.chat("/competition");
            return Command.SINGLE_SUCCESS;
        }));
    }
}
