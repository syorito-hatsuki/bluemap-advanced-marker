package dev.syoritohatsuki.bluemapadvancedmarker.manager

import de.bluecolored.bluemap.api.BlueMapAPI

object MarkersManager {
    fun exist(playerUUID: String, markerName: String): Boolean {
        BlueMapAPI.getInstance().get().maps.onEach { map ->
            map.markerSets.onEach { if (it.value.markers.keys.contains("$playerUUID/$markerName")) return true }
        }
        return false
    }
}