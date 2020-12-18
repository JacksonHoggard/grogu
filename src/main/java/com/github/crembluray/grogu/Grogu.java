package com.github.crembluray.grogu;

import com.github.crembluray.grogu.data.entities.GroguEntity;
import com.github.crembluray.grogu.setup.ModEntities;
import com.github.crembluray.grogu.setup.Registration;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public static Logger getLogger() {
        return LOGGER;
    }
}