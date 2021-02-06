package com.frank142857.fcc.init;

import com.frank142857.fcc.item.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<Item> REGISTER_ITEMS = new ArrayList<Item>();

    public static final ItemFortuneCharacter ITEM_FORTUNE_CHARACTER = new ItemFortuneCharacter();
    public static final ItemFortuneGainer ITEM_FORTUNE_GAINER = new ItemFortuneGainer();
}
