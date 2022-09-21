package dev.syoritohatsuki.bluemapadvancedmarker.manager

import de.bluecolored.bluemap.api.BlueMapAPI
import net.minecraft.world.World

object MapManager {
    fun BlueMapAPI.saveWorld(world: World) {
        getWorld(world).ifPresent {
            it.maps.toList()[0].markerSets.forEach { (uuid, markerSet) ->
                ConfigManager.writeMarkerSet(
                    it.maps.toList()[0].id,
                    uuid,
                    markerSet
                )
            }
        }
    }

    fun BlueMapAPI.loadWorld(world: World) {
        getWorld(world).ifPresent {
            ConfigManager.readMarkerSet(it.maps.toList()[0].id).forEach { (name, markerSet) ->
                it.maps.toList()[0].markerSets[name] = markerSet
            }
        }
    }
}