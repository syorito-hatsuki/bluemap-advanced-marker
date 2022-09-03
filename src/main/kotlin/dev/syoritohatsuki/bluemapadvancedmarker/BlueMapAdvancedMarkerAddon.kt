package dev.syoritohatsuki.bluemapadvancedmarker

import com.mojang.logging.LogUtils
import dev.syoritohatsuki.bluemapadvancedmarker.command.*
import dev.syoritohatsuki.bluemapadvancedmarker.util.PositionManager
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.util.ActionResult
import org.slf4j.Logger

object BlueMapAdvancedMarkerAddon : ModInitializer {
    val logger: Logger = LogUtils.getLogger()
    val positionManager = PositionManager

    override fun onInitialize() {
        logger.info("${javaClass.simpleName} initialized")

        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, registryAccess, environment ->
            PositionCommand.register(dispatcher)
            PointMarkerCommand.register(dispatcher)
            LineMarkerCommand.register(dispatcher)
            ShapeMarkerCommand.register(dispatcher)
            ExtrudeMarkerCommand.register(dispatcher)
        })
        ActionResult.PASS
    }
}