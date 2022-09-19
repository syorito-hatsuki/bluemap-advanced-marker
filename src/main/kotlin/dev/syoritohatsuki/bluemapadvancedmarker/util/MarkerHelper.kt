package dev.syoritohatsuki.bluemapadvancedmarker.util

import de.bluecolored.bluemap.api.BlueMapAPI
import de.bluecolored.bluemap.api.markers.MarkerSet
import de.bluecolored.bluemap.api.markers.POIMarker
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.logger
import dev.syoritohatsuki.bluemapadvancedmarker.manager.ConfigManager
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World
import kotlin.io.path.exists

object MarkerHelper {
    private val blueMapAPI = BlueMapAPI.getInstance()

    init {
        if (!bluemapMarkerSetsDir.exists()) bluemapMarkerSetsDir.mkdirs()
    }

    fun createPoint(title: String, icon: String, world: World, playerEntity: PlayerEntity) {
        blueMapAPI.ifPresentOrElse({ mapAPI ->
            mapAPI.getWorld(world).ifPresentOrElse({ blueMapWorld ->
                playerEntity.uuid.apply {
                    if (blueMapWorld.maps.toList()[0].markerSets["$this"] == null)
                        blueMapWorld.maps.toList()[0].markerSets["$this"] =
                            MarkerSet.builder().label(playerEntity.displayName.string).build()

                    blueMapWorld.maps.toList()[0].markerSets["$this"]!!.markers["$this/$title"] = POIMarker.toBuilder()
                        .label(title)
                        .icon(ConfigManager.read().icons[icon] ?: ConfigManager.read().icons["mansion"], 0, 0)
                        .position(playerEntity.pos.toVector3d())
                        .build()
                }
            }, { logger.info("BlueMapWorld not present") })
        }, { logger.info("MapAPI not present") })
        TODO("Add verify that name not already in use")
    }
}
