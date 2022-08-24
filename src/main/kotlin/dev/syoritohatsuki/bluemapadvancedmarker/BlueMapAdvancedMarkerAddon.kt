package dev.syoritohatsuki.bluemapadvancedmarker

import com.mojang.logging.LogUtils
import de.bluecolored.bluemap.api.BlueMapAPI
import de.bluecolored.bluemap.api.markers.*
import dev.syoritohatsuki.bluemapadvancedmarker.command.ExtrudeMarkerCommand
import dev.syoritohatsuki.bluemapadvancedmarker.command.LineMarkerCommand
import dev.syoritohatsuki.bluemapadvancedmarker.command.ShapeMarkerCommand
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import org.slf4j.Logger

object BlueMapAdvancedMarkerAddon : ModInitializer {
    val logger: Logger = LogUtils.getLogger()

    override fun onInitialize() {
        logger.info("${javaClass.simpleName} initialized")

        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, registryAccess, environment ->
            ShapeMarkerCommand.register(dispatcher)
            LineMarkerCommand.register(dispatcher)
            ExtrudeMarkerCommand.register(dispatcher)
        })

        BlueMapAPI.getInstance().ifPresent {
            POIMarker.toBuilder()
            HtmlMarker.builder()
            LineMarker.builder()
            ExtrudeMarker.builder()
            ShapeMarker.builder()
        }
    }
}