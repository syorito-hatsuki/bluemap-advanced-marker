package dev.syoritohatsuki.bluemapadvancedmarker.util

import com.flowpowered.math.vector.Vector3d
import net.minecraft.util.math.Vec3d
import java.nio.file.Path

fun Vec3d.toVector3d() = Vector3d(this.x, this.y, this.z)

fun Vec3d.toCordString() = "X: ${this.x.toInt()} | Y: ${this.y.toInt()} | Z: ${this.z.toInt()}"

fun Path.mkdirs() = toFile().mkdirs()