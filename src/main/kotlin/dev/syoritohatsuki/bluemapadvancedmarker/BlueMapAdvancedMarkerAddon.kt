package dev.syoritohatsuki.bluemapadvancedmarker

import com.mojang.logging.LogUtils
import de.bluecolored.bluemap.api.BlueMapAPI
import dev.syoritohatsuki.bluemapadvancedmarker.manager.MapManager.loadWorld
import dev.syoritohatsuki.bluemapadvancedmarker.manager.MapManager.saveWorld
import dev.syoritohatsuki.bluemapadvancedmarker.manager.PlayerCacheManager
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarted
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import org.slf4j.Logger

object BlueMapAdvancedMarkerAddon : ModInitializer {
    val logger: Logger = LogUtils.getLogger()
    const val MOD_ID = "bluemapadvancedmarker"

    override fun onInitialize() {
        logger.info("${javaClass.simpleName} initialized")
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, _ ->
            CommandRegistry.register(dispatcher)
        })

        ServerLifecycleEvents.SERVER_STARTED.register(ServerStarted { server ->
            server.worlds.forEach { serverWorld ->
                BlueMapAPI.onEnable { it.loadWorld(serverWorld) }
                BlueMapAPI.onDisable { it.saveWorld(serverWorld) }
            }
        })

        ServerPlayConnectionEvents.JOIN.register(ServerPlayConnectionEvents.Join { handler, _, _ ->
            PlayerCacheManager.savePlayer(handler.player.uuid, handler.player.displayName.string)
        })
    }
}