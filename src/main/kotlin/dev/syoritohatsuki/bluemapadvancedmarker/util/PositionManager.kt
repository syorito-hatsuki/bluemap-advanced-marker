package dev.syoritohatsuki.bluemapadvancedmarker.util

import com.flowpowered.math.vector.Vector3d
import net.minecraft.util.math.Vec3d
import java.util.*

object PositionManager {
    private val playerPositions = mutableMapOf<UUID, MutableSet<Vec3d>>()

    fun addPosition(uuid: UUID, vec3d: Vec3d) {
        (playerPositions[uuid] ?: mutableSetOf()).apply {
            add(vec3d)
            playerPositions[uuid] = this
        }
    }

    fun removePosition(uuid: UUID, vec3d: Vec3d) {
        (playerPositions[uuid] ?: mutableSetOf()).apply {
            remove(vec3d)
            playerPositions[uuid] = this
        }
    }

    fun clearPositions(uuid: UUID) {
        (playerPositions[uuid] ?: mutableSetOf()).apply {
            clear()
            playerPositions[uuid] = this
        }
    }

    fun getBlueMapPositions(uuid: UUID): Set<Vector3d> {
        return mutableSetOf<Vector3d>().apply {
            (playerPositions[uuid] ?: mutableSetOf()).forEach {
                add(it.toVector3d())
            }
        }
    }

    fun getCount(uuid: UUID): Int = (playerPositions[uuid] ?: mutableSetOf()).size

    fun getAverageHeight(uuid: UUID): Float {
        var value = 0.0
        (playerPositions[uuid] ?: mutableSetOf()).apply {
            forEach { value += it.y }
            return (value / size).toFloat()
        }
    }

    fun getMinHeight(uuid: UUID): Float {
        return (playerPositions[uuid] ?: mutableSetOf()).minOf { it.y }.toFloat()
    }

    fun getMaxHeight(uuid: UUID): Float {
        return (playerPositions[uuid] ?: mutableSetOf()).minOf { it.y }.toFloat()
    }
}