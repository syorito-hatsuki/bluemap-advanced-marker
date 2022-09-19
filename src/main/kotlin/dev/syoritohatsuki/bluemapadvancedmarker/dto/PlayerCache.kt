package dev.syoritohatsuki.bluemapadvancedmarker.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlayerCache(
    val map: Set<Player> = emptySet(),
) {
    @Serializable
    data class Player(
        val name: String,
        val uuid: String,
    )
}
