package com.github.jacksonhoggard.grogu;

import com.github.jacksonhoggard.grogu.data.entities.GroguEntity;
import com.github.jacksonhoggard.grogu.setup.ModEntities;
import com.github.jacksonhoggard.grogu.setup.Registration;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod(Grogu.MOD_ID)
public class Grogu
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "grogu";

    public Grogu() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupEntities);

        Registration.register();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setupEntities(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.GROGU.get(), GroguEntity.setCustomAttributes().create());
        });
    }

    // As biomes are loading in, add Grogu entity to certain spawn locations
    @SubscribeEvent(priority = EventPriority.HIGH)
    void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        List<MobSpawnInfo.Spawners> spawns = event.getSpawns().getSpawner(EntityClassification.AMBIENT);

        spawns.add(new MobSpawnInfo.Spawners(ModEntities.GROGU.get(), 1, 0, 1));
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}