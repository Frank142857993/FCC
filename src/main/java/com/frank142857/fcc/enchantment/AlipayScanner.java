package com.frank142857.fcc.enchantment;

import com.frank142857.fcc.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class AlipayScanner extends Enchantment {

    public final String name = "alipay_scanner";

    public AlipayScanner(){
        super(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER,
                new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
        this.setName(name);
        this.setRegistryName(name);
        EnchantmentInit.REGISTER_ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int level) {
        return 11 + level;
    }

    @Override
    public int getMaxEnchantability(int level) {
        return 29 + level;
    }
}
