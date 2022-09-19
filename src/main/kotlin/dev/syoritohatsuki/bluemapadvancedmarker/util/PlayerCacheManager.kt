package dev.syoritohatsuki.bluemapadvancedmarker.util

import kotlinx.serialization.json.Json
import java.util.*

object PlayerCacheManager {

    private val json = Json { encodeDefaults = true; prettyPrint = true; ignoreUnknownKeys = true }

    init {

    }

    fun savePlayer(uuid: UUID, name: String) {

    }

    fun getPlayer(uuid: UUID) {

    }

    fun getPlayer(name: String) {

    }
}