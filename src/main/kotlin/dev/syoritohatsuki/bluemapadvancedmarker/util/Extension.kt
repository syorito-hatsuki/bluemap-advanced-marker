package dev.syoritohatsuki.bluemapadvancedmarker.util

import com.flowpowered.math.vector.Vector3d
import net.minecraft.util.math.Vec3d

fun Vec3d.toVector3d() = Vector3d(this.x, this.y, this.z)

fun Vec3d.shortX() = "%.1f".format(this.x)

fun Vec3d.shortY() = "%.1f".format(this.y)

fun Vec3d.shortZ() = "%.1f".format(this.z)

fun Vector3d.shortX() = "%.1f".format(this.x)

fun Vector3d.shortY() = "%.1f".format(this.y)

fun Vector3d.shortZ() = "%.1f".format(this.z)