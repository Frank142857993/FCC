package com.frank142857.fcc.item;

import com.frank142857.fcc.FCC;
import com.frank142857.fcc.init.CreativeTabInit;
import com.frank142857.fcc.init.ItemInit;
import com.frank142857.fcc.interfaces.IHasModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ItemEnvelope extends Item implements IHasModel {
    public String name = "envelope";

    public ItemEnvelope(){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabInit.TAB_FCC);
        ItemInit.REGISTER_ITEMS.add(this);
    }

    public ItemStack[] getLoot(ItemStack[] stacks, int num){
        ItemStack[] res = new ItemStack[num];
        for (int i = 0; i < num; i++){
            res[i] = ItemStack.EMPTY;
        }

        for (int i = 0; i < num; i++){
            int a = new Random().nextInt(stacks.length);
            res[i] = stacks[a];
        }

        return res;
    }

    @Override
    public void registerModel() {
        FCC.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack coal = new ItemStack(Item.getItemFromBlock(Blocks.COAL_BLOCK), 1),
                iron = new ItemStack(Item.getItemFromBlock(Blocks.IRON_BLOCK), 1),
                gold = new ItemStack(Item.getItemFromBlock(Blocks.GOLD_BLOCK), 1),
                diamond = new ItemStack(Item.getItemFromBlock(Blocks.DIAMOND_BLOCK), 1),
                lapis = new ItemStack(Item.getItemFromBlock(Blocks.LAPIS_BLOCK), 1),
                redstone = new ItemStack(Item.getItemFromBlock(Blocks.REDSTONE_BLOCK), 1),
                emerald = new ItemStack(Item.getItemFromBlock(Blocks.EMERALD_BLOCK), 1);

        int amount = 5;

        Random rand = new Random();

        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        int side = facing.getIndex();

        switch(side) {
            case 0:
            default:
                y--;
                break;
            case 1:
                y++;
                break;
            case 2:
                z--;
                break;
            case 3:
                z++;
                break;
            case 4:
                x--;
                break;
            case 5:
                x++;
                break;
        }

        if (!player.canPlayerEdit(new BlockPos(x, y, z), facing, player.getHeldItem(hand))) {
            return EnumActionResult.FAIL;
        }

        ItemStack[] lootTable = new ItemStack[]{
                coal, iron, gold, diamond, lapis, redstone, emerald
        };

        for(ItemStack stack : this.getLoot(lootTable, amount)){
            EntityItem item = new EntityItem(worldIn, x, y, z, ItemStack.EMPTY);
            ((EntityItem) item).setItem(stack);
            if(!worldIn.isRemote) worldIn.spawnEntity(item);
        }

        player.getHeldItem(hand).shrink(1);
        return EnumActionResult.SUCCESS;
    }
}
