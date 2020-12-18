package com.github.crembluray.grogu.setup;

import com.github.crembluray.grogu.Grogu;
import com.github.crembluray.grogu.data.entities.GroguEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Grogu.MOD_ID);

    // Entity Types
    public static final RegistryObject<EntityType<GroguEntity>> GROGU = ENTITY_TYPES.register("grogu",
            () -> EntityType.Builder.create(GroguEntity::new, EntityClassification.CREATURE)
                    .size(0.5f, 0.9f)
                    .build(new ResourceLocation(Grogu.MOD_ID, "grogu").toString()));
}
