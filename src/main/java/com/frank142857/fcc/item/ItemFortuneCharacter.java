package com.frank142857.fcc.item;

import com.frank142857.fcc.FCC;
import com.frank142857.fcc.init.CreativeTabInit;
import com.frank142857.fcc.init.ItemInit;
import com.frank142857.fcc.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

public class ItemFortuneCharacter extends Item implements IHasModel {

    public String name = "fortune_character";

    public ItemFortuneCharacter(){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabInit.TAB_FCC);
        this.setHasSubtypes(true);
        ItemInit.REGISTER_ITEMS.add(this);
    }

    @Override
    public void registerModel(){
        for(int i = 0; i < EnumType.values().length; i++){
            FCC.proxy.registerItemRenderer(this, i, "fortune_character_" + EnumType.values()[i].getName(), "inventory");
        }
    }

    @Override
    public void getSubItems(CreativeTabs tabs, NonNullList<ItemStack> stacks) {
        for(EnumType variant : EnumType.values()){
            if (tabs == this.getCreativeTab()){
                stacks.add(new ItemStack(this, 1, variant.getMeta()));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        for(int i = 0; i < EnumType.values().length; i++){
            if(stack.getItemDamage() == i){
                return this.getUnlocalizedName() + "_" + EnumType.values()[i].getName();
            }
        }
        return this.getUnlocalizedName() + "_" + EnumType.EMPTY.getName();
    }

    public static enum EnumType implements IStringSerializable {
        EMPTY(0, "empty"),
        HARMONY(1, "harmony"),
        PROSPERITY(2, "prosperity"),
        FRIENDSHIP(3, "friendship"),
        PATRIOTISM(4, "patriotism"),
        DEDICATION(5, "dedication");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        private final int meta;
        private final String name, nameU;

        private EnumType(int meta, String name){
            this(meta, name, name);
        }

        private EnumType(int meta, String name, String nameU){
            this.meta = meta;
            this.name = name;
            this.nameU = nameU;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getMeta() {
            return this.meta;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static EnumType byMetaData(int meta){
            return META_LOOKUP[meta];
        }

        static {
            for(EnumType enumType : values()){
                META_LOOKUP[enumType.getMeta()] = enumType;
            }
        }
    }
}
