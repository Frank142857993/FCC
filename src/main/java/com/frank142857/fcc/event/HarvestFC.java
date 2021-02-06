package com.frank142857.fcc.event;

import com.frank142857.fcc.FCC;
import com.frank142857.fcc.init.*;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = FCC.MODID)
public class HarvestFC{

    public static List<Item> pickaxes = Arrays.asList(
            Items.DIAMOND_PICKAXE,
            Items.GOLDEN_PICKAXE,
            Items.IRON_PICKAXE,
            Items.STONE_PICKAXE,
            Items.WOODEN_PICKAXE);

    public static List<Item> axes = Arrays.asList(
            Items.DIAMOND_AXE,
            Items.GOLDEN_AXE,
            Items.IRON_AXE,
            Items.STONE_AXE,
            Items.WOODEN_AXE
    );

    public static List<Item> shovels = Arrays.asList(
            Items.DIAMOND_SHOVEL,
            Items.GOLDEN_SHOVEL,
            Items.IRON_SHOVEL,
            Items.STONE_SHOVEL,
            Items.WOODEN_SHOVEL
    );

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

            boolean isProperlyUsed =
                    (pickaxes.contains(stackInHand.getItem()) && target == Material.ROCK)
                    || (axes.contains(stackInHand.getItem()) && target == Material.WOOD)
                    || (shovels.contains(stackInHand.getItem()) && target == Material.SAND);

            ItemStack harmony = new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 1),
                    prosperity = new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 2),
                    friendship = new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 3),
                    patriotism = new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 4),
                    dedication = new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 5),
                    gainer = new ItemStack(ItemInit.ITEM_FORTUNE_GAINER, 1);

            if (stackInHand.isEmpty()) return;

            if (isTimeValid && isProperlyUsed && level > 0){
                int result = rand.nextInt(1000);

                if (result >= 995) {
                    drops.add(dedication);
                } else if (result >= 990){
                    drops.add(gainer);
                } else if (result >= 970){
                    drops.add(harmony);
                } else if (result >= 950){
                    drops.add(friendship);
                } else if (result >= 925){
                    drops.add(prosperity);
                } else if (result >= 900){
                    drops.add(patriotism);
                }
            }
        }
    }
}
