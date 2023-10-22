package com.eithan.oreutils;

import com.eithan.oreutils.commands.*;
import com.eithan.oreutils.config.BlockModClientConfigs;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.client.event.RenderPlayerEvent.Pre;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("oreutils")
public class oreutils {
	
	int kickDelay = 0;
	
    public oreutils() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, BlockModClientConfigs.SPEC, "blockmod-client.toml");

        // IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) { 
//    	Minecraft.getInstance().player.sendMessage(new TextComponent(event.getMessage().getContents()), Util.NIL_UUID);
//    	Minecraft.getInstance().player.sendMessage(new TextComponent(event.getMessage().getString().split(":")[0].split(" ")[2]), Util.NIL_UUID);
		if(Minecraft.getInstance().player == null)
			return;
		if (event.getMessage().getString().contains("has joined the network") && BlockModClientConfigs.AutoWB.get()){
			for(String blocked_word : BlockModClientConfigs.Blocked_Words.get())
				if (event.getMessage().getString().toLowerCase().split(" ")[0].contains(blocked_word))
					return;
			Minecraft.getInstance().player.chat("wb");
			return;
		}
		if(event.getSenderUUID() == null)
			return;
		String[] message = event.getMessage().getString().split(":")[0].split(" ");
		if(message.length != 3)
			return;
    	if(!message[0].contains("Visitor") && !message[0].contains("Student") && !message[0].contains("Builder") &&
           !message[0].contains("Mod") && !message[0].contains("Admin") && !message[0].contains("Discord") && !message[0].contains("Engineer"))
    		return;
        for(String blocked_word : BlockModClientConfigs.Blocked_Words.get()){
            if(message[2].toLowerCase().contains(blocked_word)){
                event.setCanceled(true);
                return;
            }
        }
    }
    
    @SubscribeEvent
    public void ticker(ClientTickEvent event) {
    	if (Minecraft.getInstance().player == null)
    		return;
		if (BlockModClientConfigs.FirstPosX.get() == 0 && BlockModClientConfigs.FirstPosZ.get() == 0 && BlockModClientConfigs.SecondPosX.get() == 0 && BlockModClientConfigs.SecondPosZ.get() ==0)
			return;
    	AABB box = new AABB(BlockModClientConfigs.FirstPosX.get(), -64, BlockModClientConfigs.FirstPosZ.get(), BlockModClientConfigs.SecondPosX.get(), 256, BlockModClientConfigs.SecondPosZ.get());
    	for(Entity entity : Minecraft.getInstance().level.getEntities(Minecraft.getInstance().player, box)) {
    		if(!(entity instanceof Player))
    			continue;
    		String name = entity.getName().getString();
//    		Vec3 coords = player.getPacketCoordinates();
//			boolean inXA = BlockModClientConfigs.FirstPosX.get() < coords.x && coords.x < BlockModClientConfigs.SecondPosX.get();
//			boolean inXB = BlockModClientConfigs.SecondPosX.get() < coords.x && coords.x < BlockModClientConfigs.FirstPosX.get();
//			if(!(inXA || inXB))
//				continue;
//			boolean inZA = BlockModClientConfigs.FirstPosZ.get() < coords.z && coords.z < BlockModClientConfigs.SecondPosZ.get();
//			boolean inZB = BlockModClientConfigs.SecondPosZ.get() < coords.z && coords.z < BlockModClientConfigs.FirstPosZ.get();
//			if(!(inZA || inZB))
//				continue;
    		for(String blocked_word : BlockModClientConfigs.Blocked_Words.get()) {
    			if(name.toLowerCase().equals(blocked_word) && kickDelay == 0) {
    				Minecraft.getInstance().player.chat("/p k "+name);
    				Minecraft.getInstance().player.sendMessage(new TextComponent("kicked player "+name), Util.NIL_UUID);
    				kickDelay = 13;
    			}
    		}
    	}
    	kickDelay -= 1;
    	if(kickDelay < 0)
    		kickDelay = 0;
    }
    
    @SubscribeEvent
    public void renderCancel(Pre event) {
    	String name = event.getPlayer().getName().getString();
    	for(String blocked_word : BlockModClientConfigs.Blocked_Words.get()){
    		if(name.toLowerCase().equals(blocked_word)) {
//    			Vec3 coords = event.getPlayer().getPacketCoordinates();
//    			boolean inXA = BlockModClientConfigs.FirstPosX.get() < coords.x && coords.x < BlockModClientConfigs.SecondPosX.get();
//    			boolean inXB = BlockModClientConfigs.SecondPosX.get() < coords.x && coords.x < BlockModClientConfigs.FirstPosX.get();
//    			boolean inZA = BlockModClientConfigs.FirstPosZ.get() < coords.z && coords.z < BlockModClientConfigs.SecondPosZ.get();
//    			boolean inZB = BlockModClientConfigs.SecondPosZ.get() < coords.z && coords.z < BlockModClientConfigs.FirstPosZ.get();
//				Minecraft.getInstance().player.sendMessage(new TextComponent(BlockModClientConfigs.FirstPosX.get().toString() + BlockModClientConfigs.FirstPosZ.get().toString() + BlockModClientConfigs.SecondPosX.get().toString() + BlockModClientConfigs.SecondPosZ.get().toString()), Util.NIL_UUID);
//				Minecraft.getInstance().player.sendMessage(new TextComponent(String.valueOf(inXA) + String.valueOf(inXB) + String.valueOf(inZA) + String.valueOf(inZB)), Util.NIL_UUID);
//    			if((inXA || inXB) && (inZA || inZB) && BlockModClientConfigs.KickOnSight.get() && kickDelay == 0) {
//        	        Minecraft.getInstance().player.chat("/p k "+name);
//    				Minecraft.getInstance().player.sendMessage(new TextComponent("kicked player"), Util.NIL_UUID);
//    				kickDelay = 12;
//    			}
    			if(BlockModClientConfigs.HideBlocked.get() && event.getPlayer() instanceof AbstractClientPlayer p1) {
        			event.setCanceled(true);
					Component p2 = event.getPlayer().getName();
    				PoseStack p3 = event.getPoseStack();
    				MultiBufferSource p4 = event.getMultiBufferSource();
    				int p5 = event.getPackedLight();
        			event.getRenderer().renderNameTag(p1, p2, p3, p4, p5);
    			}
                return;
            }
        }
    }
    

    @SubscribeEvent
    public void listen(RegisterClientCommandsEvent event) {
        AddBlockedWordCommand.register(event.getDispatcher());
        ShowBlockedWordCommand.register(event.getDispatcher());        
        RemoveBlockedWordCommand.register(event.getDispatcher());
        PlotFirstPositionCommand.register(event.getDispatcher());        
        PlotSecondPositionCommand.register(event.getDispatcher());
        HideBlockedSetCommand.register(event.getDispatcher());
        KickOnSightSetCommand.register(event.getDispatcher());
		CompetitionCommand.register(event.getDispatcher());
		SeasonalCommand.register(event.getDispatcher());
		BuildCommand.register(event.getDispatcher());
		SchoolCommand.register(event.getDispatcher());
		AutoWBCommand.register(event.getDispatcher());
    }
}
