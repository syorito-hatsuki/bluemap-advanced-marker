package dev.syoritohatsuki.bluemapadvancedmarker

import com.mojang.logging.LogUtils
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry
import dev.syoritohatsuki.bluemapadvancedmarker.util.PositionManager
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import org.slf4j.Logger

object BlueMapAdvancedMarkerAddon : ModInitializer {
    val logger: Logger = LogUtils.getLogger()
    val positionManager = PositionManager

    override fun onInitialize() {
        logger.info("${javaClass.simpleName} initialized")
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, _, _ ->
            CommandRegistry.register(dispatcher)
        })

        ServerPlayConnectionEvents.JOIN.register(ServerPlayConnectionEvents.Join { handler, sender, server ->

        })

        ServerPlayConnectionEvents.DISCONNECT.register(ServerPlayConnectionEvents.Disconnect { handler, server ->

        })
    }
}