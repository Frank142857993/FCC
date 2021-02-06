package com.frank142857.fcc.item;

import com.frank142857.fcc.FCC;
import com.frank142857.fcc.init.CreativeTabInit;
import com.frank142857.fcc.init.ItemInit;
import com.frank142857.fcc.interfaces.IHasModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ItemFortuneGainer extends Item implements IHasModel {
    public String name = "fortune_gainer";

    public ItemFortuneGainer(){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabInit.TAB_FCC);
        ItemInit.REGISTER_ITEMS.add(this);
    }

    @Override
    public void registerModel() {
        FCC.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
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

        EntityItem fc = new EntityItem(worldIn, x, y, z, ItemStack.EMPTY);

        int type = rand.nextInt(100);

        if (type >= 90) ((EntityItem) fc).setItem(new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 5));
        else if (type >= 70) ((EntityItem) fc).setItem(new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 1));
        else if (type >= 50) ((EntityItem) fc).setItem(new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 3));
        else if (type >= 25) ((EntityItem) fc).setItem(new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 2));
        else ((EntityItem) fc).setItem(new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 4));

        if (!worldIn.isRemote) worldIn.spawnEntity(fc);

        player.getHeldItem(hand).shrink(1);
        return EnumActionResult.SUCCESS;
    }
}
