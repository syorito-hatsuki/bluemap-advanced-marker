package dev.syoritohatsuki.bluemapadvancedmarker.manager

import de.bluecolored.bluemap.api.BlueMapAPI
import de.bluecolored.bluemap.api.markers.MarkerSet
import de.bluecolored.bluemap.api.markers.POIMarker
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.logger
import dev.syoritohatsuki.bluemapadvancedmarker.util.bluemapMarkerSetsDir
import dev.syoritohatsuki.bluemapadvancedmarker.util.mkdirs
import dev.syoritohatsuki.bluemapadvancedmarker.util.toCordString
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
                if (exist(playerEntity.uuidAsString, title)) {
                    playerEntity.sendMessage(Text.of("$title marker with that name already exist"))
                } else {
                    if (blueMapWorld.maps.toList()[0].markerSets[playerEntity.uuidAsString] == null)
                        blueMapWorld.maps.toList()[0].markerSets[playerEntity.uuidAsString] =
                            MarkerSet.builder().label(playerEntity.displayName.string).build()

                    blueMapWorld.maps.toList()[0].markerSets[playerEntity.uuidAsString]!!.markers["${playerEntity.uuidAsString}/$title"] =
                        POIMarker.toBuilder()
                            .label(title)
                            .icon(ConfigManager.read().icons[icon] ?: "assets/poi.svg", 0, 0)
                            .position(playerEntity.pos.toVector3d())
                            .build()

                    playerEntity.sendMessage(
                        Text.of("Created marker at ${playerEntity.pos.toCordString()} with name $title"), false
                    )
                }
            }, { logger.info("BlueMapWorld not present") })
        }, { logger.info("MapAPI not present") })
    }

    fun removeMarker(title: String, playerEntity: PlayerEntity, playerUUID: String) {

        if (playerUUID == "") {
            playerEntity.sendMessage(Text.of("Player not found"))
            return
        }

        blueMapAPI.ifPresentOrElse({ mapAPI ->
            if (exist(playerUUID, title)) {
                mapAPI.maps.forEach { map ->
                    map.markerSets.values.forEach { set -> set.markers.remove("$playerUUID/$title") }
                }
                playerEntity.sendMessage(Text.of("Removed marker with name $title"), false)
            } else {
                playerEntity.sendMessage(Text.of("Marker not found"), false)
            }
        }, { logger.info("MapAPI not present") })
    }

    private fun exist(playerUUID: String, markerName: String): Boolean {
        blueMapAPI.get().maps.forEach { map ->
            map.markerSets.forEach { if (it.value.markers.keys.contains("$playerUUID/$markerName")) return true }
        }
        return false
    }
}