package com.github.jacksonhoggard.grogu.data.client;

import com.github.jacksonhoggard.grogu.Grogu;
import com.github.jacksonhoggard.grogu.data.client.render.GroguRenderer;
import com.github.jacksonhoggard.grogu.setup.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Grogu.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.GROGU.get(), GroguRenderer::new);
    }

}
