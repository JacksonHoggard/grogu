package com.github.crembluray.grogu.setup;

import com.github.crembluray.grogu.Grogu;
import com.github.crembluray.grogu.data.blocks.BlockItemBase;
import com.github.crembluray.grogu.data.blocks.MetalBallBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Grogu.MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Grogu.MOD_ID);

    // Blocks
    public static final RegistryObject<Block> METAL_BALL_BLOCK = BLOCKS.register("metal_ball", MetalBallBlock::new);

    // Block Items
    public static final RegistryObject<Item> METAL_BALL_BLOCK_ITEM = BLOCK_ITEMS.register("metal_ball", () -> new BlockItemBase(METAL_BALL_BLOCK.get()));
}
