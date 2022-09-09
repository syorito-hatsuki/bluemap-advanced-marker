package dev.syoritohatsuki.bluemapadvancedmarker.util

import com.flowpowered.math.vector.Vector3d
import net.minecraft.util.math.Vec3d

fun Vec3d.toVector3d() = Vector3d(this.x, this.y, this.z)