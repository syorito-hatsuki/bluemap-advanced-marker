package dev.syoritohatsuki.bluemapadvancedmarker.manager

import de.bluecolored.bluemap.api.gson.MarkerGson
import de.bluecolored.bluemap.api.markers.MarkerSet
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.logger
import dev.syoritohatsuki.bluemapadvancedmarker.dto.Config
import dev.syoritohatsuki.bluemapadvancedmarker.util.bluemapMarkerSetsDir
import dev.syoritohatsuki.bluemapadvancedmarker.util.configDir
import dev.syoritohatsuki.bluemapadvancedmarker.util.mkdirs
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*
import kotlin.io.path.exists

object ConfigManager {
    private val json = Json { encodeDefaults = true; prettyPrint = true; ignoreUnknownKeys = true }
    private val configFile = File(configDir.toFile(), "config.json")

    init {
        if (!configFile.exists()) {
            if (!configDir.exists()) configDir.mkdirs()
            configFile.apply {
                createNewFile()
                writeText(json.encodeToString(Config()))
            }
        } else configFile.writeText(json.encodeToString(read()))
    }

    fun read(): Config = json.decodeFromString(configFile.readText())

    fun writeMarkerSet(worldId: String, uuid: String, markerSet: MarkerSet) {
        File(bluemapMarkerSetsDir.toFile(), worldId).apply {
            if (!exists()) mkdir()
            File(this, "$uuid.json").apply {
                if (!exists()) createNewFile()
                writeText(MarkerGson.INSTANCE.toJson(markerSet))
            }
        }
    }

    fun readMarkerSet(worldId: String) = mutableMapOf<String, MarkerSet>().apply {
        File(bluemapMarkerSetsDir.toFile(), worldId).listFiles()?.map {
            try {
                put(
                    it.nameWithoutExtension,
                    MarkerGson.INSTANCE.fromJson(it.readText(), MarkerSet::class.java)
                )
            } catch (e: Exception) {
                val name = PlayerCacheManager.getPlayer(UUID.fromString(it.nameWithoutExtension)).name
                val localizeUUID = if (name != "") {
                    "($name) "
                } else {
                    ""
                }
                logger.error("$worldId/${it.name} ${localizeUUID}is empty or corrupted")
                logger.error(e.message)
            }
        }
    }
}
