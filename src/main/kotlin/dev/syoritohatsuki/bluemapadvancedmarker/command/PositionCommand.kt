package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.CommandDispatcher
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.positionManager
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import java.awt.KeyEventDispatcher

object PositionCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(CommandManager.literal("marker")
            .then(CommandManager.literal("position").then(CommandManager.literal("add").executes {
                if (it.source.player != null) positionManager.addPosition(it.source.player!!.pos) else it.source.sendError(
                    Text.literal("Player position unavailable")
                )
                1
            }).then(CommandManager.literal("remove").executes {
                if (it.source.player != null) positionManager.removePosition(it.source.player!!.pos) else it.source.sendError(
                    Text.literal("Player position unavailable")
                )
                1
            }).then(CommandManager.literal("clear").executes {
                positionManager.clearPositions()
                1
            }).then(CommandManager.literal("list").executes { context ->
                if (context.source.player != null) context.source.player!!.sendMessage(
                    Text.of(positionManager.getBlueMapPositions()
                        .joinToString("") { "X: ${it.x} | Y: ${it.y} | Z: ${it.z}\n" })
                )
                else context.source.sendError(Text.literal("Player position unavailable"))
                1
            })
            )
        )
    }
}