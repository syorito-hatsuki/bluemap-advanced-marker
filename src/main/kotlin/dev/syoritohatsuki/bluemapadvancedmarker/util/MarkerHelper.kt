package dev.syoritohatsuki.bluemapadvancedmarker.util

import de.bluecolored.bluemap.api.BlueMapAPI
import de.bluecolored.bluemap.api.markers.MarkerSet
import de.bluecolored.bluemap.api.markers.POIMarker
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.logger
import dev.syoritohatsuki.bluemapadvancedmarker.manager.ConfigManager
import dev.syoritohatsuki.bluemapadvancedmarker.manager.MarkersManager
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Text
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
                if (MarkersManager.exist("${playerEntity.uuid}", title))
                    playerEntity.sendMessage(Text.of("Marker with name [$title] already exist")) else {
                    if (blueMapWorld.maps.toList()[0].markerSets["${playerEntity.uuid}"] == null)
                        blueMapWorld.maps.toList()[0].markerSets["${playerEntity.uuid}"] =
                            MarkerSet.builder().label(playerEntity.displayName.string).build()

                    blueMapWorld.maps.toList()[0].markerSets["${playerEntity.uuid}"]!!.markers["${playerEntity.uuid}/$title"] =
                        POIMarker.toBuilder()
                            .label(title)
                            .icon(ConfigManager.read().icons[icon] ?: "assets/poi.svg", 0, 0)
                            .position(playerEntity.pos.toVector3d())
                            .build()
                }
            }, { logger.info("BlueMapWorld not present") })
        }, { logger.info("MapAPI not present") })
        TODO("Add verify that name not already in use")
    }
}
