package com.frank142857.fcc.event;

import com.frank142857.fcc.FCC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = FCC.MODID)
public class EnterPrompt {
    @SubscribeEvent
    public static void enterPrompt(EntityJoinWorldEvent event)
    {
        World world = event.getWorld();
        Entity entity = event.getEntity();

        if (!entity.world.isRemote && entity instanceof EntityPlayer)
        {
            int Id = world.provider.getDimension();

            if(Id == 0)
            {
                entity.sendMessage(new TextComponentTranslation("message.fcc.playerJoinWorld", entity.getName()));
                entity.sendMessage(new TextComponentTranslation("message.fcc.playerJoinWorld1"));
            }
        }
    }
}
