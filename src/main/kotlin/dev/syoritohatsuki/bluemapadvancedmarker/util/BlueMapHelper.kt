package dev.syoritohatsuki.bluemapadvancedmarker.util

import com.flowpowered.math.vector.Vector3d
import de.bluecolored.bluemap.api.BlueMapAPI
import de.bluecolored.bluemap.api.markers.LineMarker
import de.bluecolored.bluemap.api.markers.MarkerSet
import de.bluecolored.bluemap.api.math.Color
import de.bluecolored.bluemap.api.math.Line
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World
import java.util.function.Consumer

object BlueMapHelper {
    private val blueMapAPI = BlueMapAPI.getInstance().get()

    fun createLine(title: String, color: Int, world: World, playerEntity: PlayerEntity, vararg positions: Vector3d) {
        BlueMapAdvancedMarkerAddon.logger.info("=======================")
        val playerUUID = playerEntity.uuid.toString()
        BlueMapAdvancedMarkerAddon.logger.info(playerUUID)
        blueMapAPI.getWorld(world).ifPresentOrElse({
            BlueMapAdvancedMarkerAddon.logger.info(it.toString())

            val markerSet = MarkerSet.builder().label(playerUUID).build()
            BlueMapAdvancedMarkerAddon.logger.info(markerSet.toString())

            val marker = LineMarker.builder()
                .label(title)
                .line(Line(*positions))
                .lineColor(Color(color))
                .depthTestEnabled(true)
                .build()
            BlueMapAdvancedMarkerAddon.logger.info(marker.toString())
            BlueMapAdvancedMarkerAddon.logger.info("=======================")


            BlueMapAdvancedMarkerAddon.logger.info("=======================")
            BlueMapAdvancedMarkerAddon.logger.info(playerUUID)
            BlueMapAdvancedMarkerAddon.logger.info(it.toString())
            BlueMapAdvancedMarkerAddon.logger.info(markerSet.toString())
            BlueMapAdvancedMarkerAddon.logger.info(marker.toString())
            BlueMapAdvancedMarkerAddon.logger.info("=======================")

            markerSet.markers["$playerUUID/$title"] = marker

            BlueMapAdvancedMarkerAddon.logger.info("=======================")
            BlueMapAdvancedMarkerAddon.logger.info(playerUUID)
            BlueMapAdvancedMarkerAddon.logger.info(it.toString())
            BlueMapAdvancedMarkerAddon.logger.info(markerSet.toString())
            BlueMapAdvancedMarkerAddon.logger.info(marker.toString())
            BlueMapAdvancedMarkerAddon.logger.info("=======================")

            it.maps.toList()[0].markerSets[playerUUID] = markerSet

            BlueMapAdvancedMarkerAddon.logger.info("=======================")
            BlueMapAdvancedMarkerAddon.logger.info(playerUUID)
            BlueMapAdvancedMarkerAddon.logger.info(it.toString())
            BlueMapAdvancedMarkerAddon.logger.info(markerSet.toString())
            BlueMapAdvancedMarkerAddon.logger.info(marker.toString())
            BlueMapAdvancedMarkerAddon.logger.info("=======================")

        }, {
            BlueMapAdvancedMarkerAddon.logger.info("ELSE")
        })

    }

    fun removeLine() {

    }
}
