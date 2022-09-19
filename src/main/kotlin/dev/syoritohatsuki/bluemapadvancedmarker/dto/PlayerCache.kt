package dev.syoritohatsuki.bluemapadvancedmarker.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlayerCache(
    val players: Set<Player> = emptySet(),
) {
    @Serializable
    data class Player(
        val uuid: String,
        val name: String,
    )
}
