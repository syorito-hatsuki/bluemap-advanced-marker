package dev.syoritohatsuki.bluemapadvancedmarker.util

import com.flowpowered.math.vector.Vector2d
import com.flowpowered.math.vector.Vector3d
import de.bluecolored.bluemap.api.BlueMapAPI
import de.bluecolored.bluemap.api.markers.LineMarker
import de.bluecolored.bluemap.api.markers.MarkerSet
import de.bluecolored.bluemap.api.markers.POIMarker
import de.bluecolored.bluemap.api.markers.ShapeMarker
import de.bluecolored.bluemap.api.math.Color
import de.bluecolored.bluemap.api.math.Line
import de.bluecolored.bluemap.api.math.Shape
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.logger
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

object MarkerHelper {
    private val blueMapAPI = BlueMapAPI.getInstance()

    fun createExtrude() {}

    fun createLine(title: String, color: Int, world: World, playerEntity: PlayerEntity, positions: List<Vector3d>) {
        blueMapAPI.ifPresentOrElse({ mapAPI ->
            mapAPI.getWorld(world).ifPresentOrElse({ blueMapWorld ->
                MarkerSet.builder().label(playerEntity.displayName.string).build().apply {
                    val playerUUID = playerEntity.uuid.toString()
                    markers["$playerUUID/$title"] = LineMarker.builder()
                        .label(title)
                        .line(Line(positions))
                        .lineWidth(5)
                        .lineColor(Color(color, 1F))
                        .depthTestEnabled(true)
                        .build()
                    blueMapWorld.maps.toList()[0].markerSets[playerUUID] = this
                }
            }, { logger.info("BlueMapWorld not present") })
        }, { logger.info("MapAPI not present") })
    }

    fun createPoint(title: String, icon: String, world: World, playerEntity: PlayerEntity, positions: List<Vector3d>) {
        blueMapAPI.ifPresentOrElse({ mapAPI ->
            mapAPI.getWorld(world).ifPresentOrElse({ blueMapWorld ->
                MarkerSet.builder().label(playerEntity.displayName.string).build().apply {
                    val playerUUID = playerEntity.uuid.toString()
                    markers["$playerUUID/$title"] = POIMarker.toBuilder()
                        .label(title)
                        .position(positions.last())
                        .icon(icon, 0, 0)
                        .build()
                    blueMapWorld.maps.toList()[0].markerSets[playerUUID] = this
                }
            }, { logger.info("BlueMapWorld not present") })
        }, { logger.info("MapAPI not present") })
    }

    fun createShape(
        title: String,
        color: Int,
        world: World,
        playerEntity: PlayerEntity,
        positions: List<Vector2d>,
        height: Float
    ) {
        blueMapAPI.ifPresentOrElse({ mapAPI ->
            mapAPI.getWorld(world).ifPresentOrElse({ blueMapWorld ->
                MarkerSet.builder().label(playerEntity.displayName.string).build().apply {
                    val playerUUID = playerEntity.uuid.toString()
                    markers["$playerUUID/$title"] = ShapeMarker.builder()
                        .label(title)
                        .lineColor(Color(color, 1F))
                        .fillColor(Color(color, 0.3F))
                        .lineWidth(5)
                        .shape(Shape(positions), height)
                        .build()
                    blueMapWorld.maps.toList()[0].markerSets[playerUUID] = this
                }
            }, { logger.info("BlueMapWorld not present") })
        }, { logger.info("MapAPI not present") })
    }
}
