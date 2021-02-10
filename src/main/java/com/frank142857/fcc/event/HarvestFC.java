package com.frank142857.fcc.event;

import com.frank142857.fcc.FCC;
import com.frank142857.fcc.init.*;
import com.frank142857.fcc.item.ItemFortuneCharacter;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = FCC.MODID)
public class HarvestFC{

    @SubscribeEvent
    public static void onScanningFC(BlockEvent.HarvestDropsEvent event){
        Entity harvester = event.getHarvester();
        List<ItemStack> drops = event.getDrops();
        Material target = event.getState().getMaterial();

        if (harvester instanceof EntityPlayer && !harvester.world.isRemote){

            ItemStack stackInHand = ((EntityPlayer)harvester).getHeldItemMainhand();
            Random rand = new Random();

            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ALIPAY_SCANNER, stackInHand);

            LocalDate date = LocalDate.now();
            int month = date.getMonthValue(), day = date.getDayOfMonth();
            boolean isTimeValid = (month == 1 && day >= 21) || (month == 2 && day <= 20);

            boolean isPickaxe = stackInHand.getItem() instanceof ItemPickaxe;
            boolean isAxe = stackInHand.getItem() instanceof ItemAxe;
            boolean isShovel = stackInHand.getItem() instanceof ItemSpade;

            boolean isProperlyUsed =
                    (isPickaxe && target == Material.ROCK)
                    || (isAxe && target == Material.WOOD)
                    || (isShovel && target == Material.SAND);

            ItemStack harmony = new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 1),
                    prosperity = new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 2),
                    friendship = new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 3),
                    patriotism = new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 4),
                    dedication = new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 5);

            if (stackInHand.isEmpty()) return;

            if (isTimeValid && isProperlyUsed && level > 0){

                for (int i = 0; i < 9; i++){
                    ItemStack stack = ((EntityPlayer) harvester).inventory.getStackInSlot(i);
                    if(stack.getItem() == ItemInit.ITEM_FORTUNE_CHARACTER && stack.getMetadata() == 0){
                        int result = rand.nextInt(5000);//5000
                        //TODO modify this bound?

                        if (result >= 4990) {//4990
                            drops.add(dedication);
                            stack.shrink(1);
                        } else if (result >= 4970){//4970
                            drops.add(harmony);
                            stack.shrink(1);
                        } else if (result >= 4950){//4950
                            drops.add(friendship);
                            stack.shrink(1);
                        } else if (result >= 4925){//4925
                            drops.add(prosperity);
                            stack.shrink(1);
                        } else if (result >= 4900){//4900
                            drops.add(patriotism);
                            stack.shrink(1);
                        }
                        break;
                    }
                }
            }
        }
    }
}
