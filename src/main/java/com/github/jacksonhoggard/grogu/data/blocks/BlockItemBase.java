package com.github.jacksonhoggard.grogu.data.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;

public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block blockIn) {
        super(blockIn, new Properties().group(ItemGroup.MISC));
    }

}
