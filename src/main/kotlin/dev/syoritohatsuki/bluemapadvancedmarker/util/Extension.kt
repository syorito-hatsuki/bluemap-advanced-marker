package dev.syoritohatsuki.bluemapadvancedmarker.util

import com.flowpowered.math.vector.Vector2d
import com.flowpowered.math.vector.Vector3d
import net.minecraft.util.math.Vec3d

fun Vec3d.toVector3d(): Vector3d = Vector3d(this.x, this.y, this.z)

fun Vector3d.toVector2d(): Vector2d = Vector2d(this.x, this.z)