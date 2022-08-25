package dev.syoritohatsuki.bluemapadvancedmarker.util

import com.flowpowered.math.vector.Vector3d
import de.bluecolored.bluemap.api.BlueMapAPI
import de.bluecolored.bluemap.api.markers.LineMarker
import de.bluecolored.bluemap.api.markers.MarkerSet
import de.bluecolored.bluemap.api.math.Color
import de.bluecolored.bluemap.api.math.Line
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.logger
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

object BlueMapHelper {
    private val blueMapAPI = BlueMapAPI.getInstance()

    fun createLine(title: String, color: Int, world: World, playerEntity: PlayerEntity, vararg positions: Vector3d) {
        val playerUUID = playerEntity.uuid.toString()

        blueMapAPI.ifPresentOrElse({ mapAPI ->
            mapAPI.getWorld(world).ifPresentOrElse({ blueMapWorld ->
                val markerSet = MarkerSet.builder().label(playerUUID).build()
                val marker = LineMarker.builder()
                    .label(title)
                    .line(Line(*positions))
                    .lineColor(Color(color))
                    .depthTestEnabled(true)
                    .build()

                markerSet.markers["$playerUUID/$title"] = marker

                blueMapWorld.maps.forEach {
                    logger.info("====================")
                    logger.info(it.id)
                    logger.info(it.name)
                    logger.info(it.world.toString())
                    logger.info(it.markerSets.toString())
                    logger.info("====================")
                }

                blueMapWorld.maps.toList()[0].markerSets[playerUUID] = markerSet
            }, { logger.info("BlueMapWorld not present") })
        }, { logger.info("MapAPI not present") })
    }

    fun removeLine() {

    }
}
