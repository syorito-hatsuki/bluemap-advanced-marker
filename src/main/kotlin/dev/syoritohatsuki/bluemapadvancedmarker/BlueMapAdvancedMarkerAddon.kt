package dev.syoritohatsuki.bluemapadvancedmarker

import com.mojang.logging.LogUtils
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import org.slf4j.Logger

object BlueMapAdvancedMarkerAddon : ModInitializer {
    val logger: Logger = LogUtils.getLogger()

    override fun onInitialize() {
        logger.info("${javaClass.simpleName} initialized")
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, _, _ ->
            CommandRegistry.register(dispatcher)
        })
    }
}