package dev.syoritohatsuki.bluemapadvancedmarker.util

import com.flowpowered.math.vector.Vector2d
import com.flowpowered.math.vector.Vector3d
import de.bluecolored.bluemap.api.BlueMapAPI
import de.bluecolored.bluemap.api.markers.LineMarker
import de.bluecolored.bluemap.api.markers.MarkerSet
import de.bluecolored.bluemap.api.markers.ShapeMarker
import de.bluecolored.bluemap.api.math.Color
import de.bluecolored.bluemap.api.math.Line
import de.bluecolored.bluemap.api.math.Shape
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.logger
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Text
import net.minecraft.world.World

object BlueMapHelper {
    private val blueMapAPI = BlueMapAPI.getInstance()

    fun createLine(title: String, color: Int, world: World, playerEntity: PlayerEntity, vararg positions: Vector3d) {
        val playerUUID = playerEntity.uuid.toString()

        blueMapAPI.ifPresentOrElse({ mapAPI ->
            mapAPI.getWorld(world).ifPresentOrElse({ blueMapWorld ->
                val markerSet = MarkerSet.builder().label(playerUUID).build()

                val pos = positions.map { it.toVector2d() }.toMutableList()
                pos.add(Vector2d(150F, 150F))
                playerEntity.sendMessage(Text.literal(pos.toString()))
                markerSet.markers["$playerUUID/$title"] = ShapeMarker.builder()
                    .label(title)
                    .shape(Shape(pos), positions[0].y.toFloat())
                    .position(positions[0])
                    .lineColor(Color(color))
                    .fillColor(Color(color, 1F))
                    .depthTestEnabled(true)
                    .build()

                blueMapWorld.maps.toList()[0].markerSets[playerUUID] = markerSet
            }, { logger.info("BlueMapWorld not present") })
        }, { logger.info("MapAPI not present") })
    }

    fun removeLine() {

    }
}
