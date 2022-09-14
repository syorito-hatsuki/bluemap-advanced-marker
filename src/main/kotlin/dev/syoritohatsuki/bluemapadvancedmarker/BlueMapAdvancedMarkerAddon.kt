package dev.syoritohatsuki.bluemapadvancedmarker

import com.mojang.logging.LogUtils
import de.bluecolored.bluemap.api.BlueMapAPI
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry
import dev.syoritohatsuki.bluemapadvancedmarker.util.MapManager.loadWorld
import dev.syoritohatsuki.bluemapadvancedmarker.util.MapManager.saveWorld
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarted
import org.slf4j.Logger

object BlueMapAdvancedMarkerAddon : ModInitializer {
    val logger: Logger = LogUtils.getLogger()

    override fun onInitialize() {
        logger.info("${javaClass.simpleName} initialized")
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, _, _ ->
            CommandRegistry.register(dispatcher)
        })
        ServerLifecycleEvents.SERVER_STARTED.register(ServerStarted { server ->
            server.worlds.forEach { serverWorld ->
                BlueMapAPI.onEnable { it.loadWorld(serverWorld) }
                BlueMapAPI.onDisable { it.saveWorld(serverWorld) }
            }
        })
    }
}