package dev.syoritohatsuki.bluemapadvancedmarker.config

import de.bluecolored.bluemap.api.gson.MarkerGson
import de.bluecolored.bluemap.api.markers.MarkerSet
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Paths

object ConfigManager {
    private val json = Json { encodeDefaults = true; prettyPrint = true; ignoreUnknownKeys = true }
    private val bluemapMarkerSetsDir: File = Paths.get("", "config", "bam", "markersets").toFile()
    private val configDir: File = Paths.get("", "config", "bam").toFile()
    private val configFile = File(configDir, "config.json")

    init {
        if (!bluemapMarkerSetsDir.exists()) bluemapMarkerSetsDir.mkdirs()
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
        File(bluemapMarkerSetsDir, "$worldId/$uuid.json").apply {
            createNewFile()
            writeText(MarkerGson.INSTANCE.toJson(markerSet))
        }
    }

    fun readMarkerSet(worldId: String) = mutableMapOf<String, MarkerSet>().apply {
        File(bluemapMarkerSetsDir, worldId).listFiles()?.map {
            put(
                it.nameWithoutExtension,
                MarkerGson.INSTANCE.fromJson(it.readText(), MarkerSet::class.java)
            )
        }
    }
}