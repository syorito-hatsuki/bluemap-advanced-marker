package dev.syoritohatsuki.bluemapadvancedmarker.util

import de.bluecolored.bluemap.api.BlueMapAPI
import net.minecraft.entity.player.PlayerEntity

object BlueMapHelper {
    private val blueMapAPI = BlueMapAPI.getInstance().get()

    fun createLine(playerEntity: PlayerEntity) {
        val world = blueMapAPI.getWorld(playerEntity.world).get().maps.toList()[0]
        TODO("Added marker adding")
    }

    fun removeLine() {
        TODO("Added marker removing")
    }
}
