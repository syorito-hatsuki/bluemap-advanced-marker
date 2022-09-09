package dev.syoritohatsuki.bluemapadvancedmarker.registry

import com.mojang.brigadier.CommandDispatcher
import dev.syoritohatsuki.bluemapadvancedmarker.command.HelpCommand
import dev.syoritohatsuki.bluemapadvancedmarker.command.MarkerIconsCommand
import dev.syoritohatsuki.bluemapadvancedmarker.command.PointMarkerCommand
import net.minecraft.server.command.ServerCommandSource

object CommandRegistry {
    const val commandLiteral = "bam"
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        HelpCommand.register(dispatcher)
        MarkerIconsCommand.register(dispatcher)
        PointMarkerCommand.register(dispatcher)
    }
}