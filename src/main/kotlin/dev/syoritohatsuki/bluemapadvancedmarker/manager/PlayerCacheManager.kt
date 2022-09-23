package dev.syoritohatsuki.bluemapadvancedmarker.manager

import dev.syoritohatsuki.bluemapadvancedmarker.dto.PlayerCache
import dev.syoritohatsuki.bluemapadvancedmarker.dto.PlayerCache.Player
import dev.syoritohatsuki.bluemapadvancedmarker.util.cacheDir
import dev.syoritohatsuki.bluemapadvancedmarker.util.mkdirs
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*
import kotlin.io.path.exists

object PlayerCacheManager {

    private val json = Json { encodeDefaults = true; prettyPrint = true; ignoreUnknownKeys = true }
    private val cacheFile = File(cacheDir.toFile(), "players.json")

    init {
        if (!cacheFile.exists()) {
            if (!cacheDir.exists()) cacheDir.mkdirs()
            cacheFile.apply {
                createNewFile()
                writeText(json.encodeToString(PlayerCache()))
            }
        } else cacheFile.writeText(json.encodeToString(read()))
    }

    private fun read(): PlayerCache = json.decodeFromString(cacheFile.readText())

    fun savePlayer(uuid: UUID, name: String) {
        read().players.toMutableSet().apply {
            add(Player(uuid.toString(), name))
            cacheFile.writeText(json.encodeToString(PlayerCache(this)))
        }
    }

    fun getPlayer(uuid: UUID): Player {
        val player = read().players.filter { it.uuid == uuid.toString() }
        return when {
            player.isNotEmpty() -> player[0]
            else -> return Player()
        }
    }

    fun getPlayer(name: String): Player {
        val player = read().players.filter { it.name == name }
        return when {
            player.isNotEmpty() -> player[0]
            else -> return Player()
        }
    }
}
