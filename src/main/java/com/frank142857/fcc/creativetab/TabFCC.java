package com.frank142857.fcc.creativetab;

import com.frank142857.fcc.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabFCC extends CreativeTabs {
    public TabFCC(){
        super("fcc");
    }

    @Override
    public ItemStack getTabIconItem(){
        return new ItemStack(ItemInit.ITEM_FORTUNE_CHARACTER, 1, 5);
    }
}
