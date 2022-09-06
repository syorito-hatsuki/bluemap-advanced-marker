package dev.syoritohatsuki.bluemapadvancedmarker.util

import de.bluecolored.bluemap.api.BlueMapAPI
import de.bluecolored.bluemap.api.markers.*
import de.bluecolored.bluemap.api.math.Color
import de.bluecolored.bluemap.api.math.Line
import de.bluecolored.bluemap.api.math.Shape
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.logger
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.positionManager
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

object MarkerHelper {
    private val blueMapAPI = BlueMapAPI.getInstance()

    fun createExtrude(
        title: String,
        color: Int,
        world: World,
        playerEntity: PlayerEntity
    ) {
        blueMapAPI.ifPresentOrElse({ blueAPI ->
            blueAPI.getWorld(world).ifPresentOrElse({ blueMapWorld ->
                MarkerSet.builder().label(playerEntity.displayName.string).build().apply {
                    val uuid = playerEntity.uuid
                    markers["$uuid/$title"] = ExtrudeMarker.builder()
                        .label(title)
                        .shape(
                            Shape(positionManager.getBlueMapPositions(uuid).map { it.toVector2() }),
                            positionManager.getMinHeight(uuid),
                            positionManager.getMaxHeight(uuid)
                        )
                        .lineWidth(5)
                        .lineColor(Color(color, 1F))
                        .fillColor(Color(color, 0.3F))
                        .build()
                    blueMapWorld.maps.toList()[0].markerSets["$uuid"] = this
                }
            }, { logger.info("BlueMapWorld not present") })
        }, { logger.info("MapAPI not present") })
    }

    fun createLine(title: String, color: Int, world: World, playerEntity: PlayerEntity) {
        blueMapAPI.ifPresentOrElse({ mapAPI ->
            mapAPI.getWorld(world).ifPresentOrElse({ blueMapWorld ->
                MarkerSet.builder().label(playerEntity.displayName.string).build().apply {
                    val uuid = playerEntity.uuid
                    markers["$uuid/$title"] = LineMarker.builder()
                        .label(title)
                        .line(Line(positionManager.getBlueMapPositions(uuid)))
                        .lineWidth(5)
                        .lineColor(Color(color, 1F))
                        .depthTestEnabled(true)
                        .build()
                    blueMapWorld.maps.toList()[0].markerSets["$uuid"] = this
                }
            }, { logger.info("BlueMapWorld not present") })
        }, { logger.info("MapAPI not present") })
    }

    fun createPoint(title: String, world: World, playerEntity: PlayerEntity) {
        blueMapAPI.ifPresentOrElse({ mapAPI ->
            mapAPI.getWorld(world).ifPresentOrElse({ blueMapWorld ->
                MarkerSet.builder().label(playerEntity.displayName.string).build().apply {
                    val uuid = playerEntity.uuid
                    markers["$uuid/$title"] = POIMarker.toBuilder()
                        .label(title)
                        .position(positionManager.getBlueMapPositions(uuid).last())
                        .build()
                    blueMapWorld.maps.toList()[0].markerSets["$uuid"] = this
                }
            }, { logger.info("BlueMapWorld not present") })
        }, { logger.info("MapAPI not present") })
    }

    fun createShape(
        title: String,
        color: Int,
        world: World,
        playerEntity: PlayerEntity
    ) {
        blueMapAPI.ifPresentOrElse({ mapAPI ->
            mapAPI.getWorld(world).ifPresentOrElse({ blueMapWorld ->
                MarkerSet.builder().label(playerEntity.displayName.string).build().apply {
                    val uuid = playerEntity.uuid
                    markers["$uuid/$title"] = ShapeMarker.builder()
                        .label(title)
                        .lineColor(Color(color, 1F))
                        .fillColor(Color(color, 0.3F))
                        .lineWidth(5)
                        .depthTestEnabled(true)
                        .shape(
                            Shape(positionManager.getBlueMapPositions(uuid).map { it.toVector2() }),
                            positionManager.getAverageHeight(uuid)
                        )
                        .build()
                    blueMapWorld.maps.toList()[0].markerSets["$uuid"] = this
                }
            }, { logger.info("BlueMapWorld not present") })
        }, { logger.info("MapAPI not present") })
    }
}
