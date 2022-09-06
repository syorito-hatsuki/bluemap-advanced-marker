package dev.syoritohatsuki.bluemapadvancedmarker.registry

import com.mojang.brigadier.CommandDispatcher
import dev.syoritohatsuki.bluemapadvancedmarker.command.*
import net.minecraft.server.command.ServerCommandSource

object CommandRegistry {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        HelpCommand.register(dispatcher)
        ExtrudeMarkerCommand.register(dispatcher)
        LineMarkerCommand.register(dispatcher)
        MarkerIconsCommand.register(dispatcher)
        PositionCommand.register(dispatcher)
        PointMarkerCommand.register(dispatcher)
        ShapeMarkerCommand.register(dispatcher)
    }
}