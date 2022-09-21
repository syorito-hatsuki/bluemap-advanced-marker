package dev.syoritohatsuki.bluemapadvancedmarker.manager

import de.bluecolored.bluemap.api.BlueMapAPI
import de.bluecolored.bluemap.api.markers.MarkerSet
import de.bluecolored.bluemap.api.markers.POIMarker
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.logger
import dev.syoritohatsuki.bluemapadvancedmarker.util.bluemapMarkerSetsDir
import dev.syoritohatsuki.bluemapadvancedmarker.util.mkdirs
import dev.syoritohatsuki.bluemapadvancedmarker.util.toVector3d
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Text
import net.minecraft.world.World
import kotlin.io.path.exists

object MarkersManager {
    private val blueMapAPI = BlueMapAPI.getInstance()

    init {
        if (!bluemapMarkerSetsDir.exists()) bluemapMarkerSetsDir.mkdirs()
    }


    fun createPoint(title: String, icon: String, world: World, playerEntity: PlayerEntity) {
        blueMapAPI.ifPresentOrElse({ mapAPI ->
            mapAPI.getWorld(world).ifPresentOrElse({ blueMapWorld ->
                if (exist("${playerEntity.uuid}", title))
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
    }

    fun removeMarker() {

    }

    private fun exist(playerUUID: String, markerName: String): Boolean {
        blueMapAPI.get().maps.onEach { map ->
            map.markerSets.onEach { if (it.value.markers.keys.contains("$playerUUID/$markerName")) return true }
        }
        return false
    }
}