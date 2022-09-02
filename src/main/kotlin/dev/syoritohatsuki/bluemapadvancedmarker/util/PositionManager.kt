package dev.syoritohatsuki.bluemapadvancedmarker.util

import net.minecraft.util.math.Vec3d

object PositionManager {
    private val positions = mutableSetOf<Vec3d>()

    fun addPosition(vec3d: Vec3d) = positions.add(vec3d)

    fun removePosition(vec3d: Vec3d) = positions.remove(vec3d)

    fun clearPositions() = positions.clear()

    fun getBlueMapPositions() = positions.map { it.toVector3d() }
}