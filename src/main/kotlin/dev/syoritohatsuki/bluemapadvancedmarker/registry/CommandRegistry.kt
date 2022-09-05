package dev.syoritohatsuki.bluemapadvancedmarker.registry

import com.mojang.brigadier.CommandDispatcher
import dev.syoritohatsuki.bluemapadvancedmarker.command.*
import net.minecraft.server.command.ServerCommandSource

object CommandRegistry {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        ExtrudeMarkerCommand(dispatcher)
        LineMarkerCommand(dispatcher)
        MarkerIconsCommand(dispatcher)
        PositionCommand(dispatcher)
        PointMarkerCommand(dispatcher)
        ShapeMarkerCommand(dispatcher)
    }
}