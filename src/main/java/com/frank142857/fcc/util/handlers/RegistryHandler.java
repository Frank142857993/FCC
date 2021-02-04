package com.frank142857.fcc.util.handlers;

import com.frank142857.fcc.init.BlockInit;
import com.frank142857.fcc.init.ItemInit;
import com.frank142857.fcc.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(ItemInit.REGISTER_ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(BlockInit.REGISTER_BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event){
        for(Item item : ItemInit.REGISTER_ITEMS){
            if(item instanceof IHasModel){
                ((IHasModel)item).registerModel();
            }
        }

        for(Block block : BlockInit.REGISTER_BLOCKS){
            if(block instanceof IHasModel){
                ((IHasModel) block).registerModel();
            }
        }
    }
}
