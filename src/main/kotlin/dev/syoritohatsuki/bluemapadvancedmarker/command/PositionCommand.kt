package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.positionManager
import dev.syoritohatsuki.bluemapadvancedmarker.util.shortX
import dev.syoritohatsuki.bluemapadvancedmarker.util.shortY
import dev.syoritohatsuki.bluemapadvancedmarker.util.shortZ
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

class PositionCommand(dispatcher: CommandDispatcher<ServerCommandSource>) {
    init {
        dispatcher.register(
            CommandManager.literal("marker")
                .then(
                    CommandManager.literal("position")
                        .then(CommandManager.literal("add").executes { executeAdd(it) })
                        .then(CommandManager.literal("remove").executes { executeRemove(it) })
                        .then(CommandManager.literal("clear").executes { executeClear(it) })
                        .then(CommandManager.literal("list").executes { executeList(it) })
                )
        )
    }

    private fun executeAdd(context: CommandContext<ServerCommandSource>): Int {
        context.source.playerOrThrow.apply {
            positionManager.addPosition(pos)
            context.source.sendFeedback(
                Text.of("Position added to list [ X: ${pos.shortX()} | Y: ${pos.shortY()} | Z: ${pos.shortZ()} ]"),
                false
            )
        }
        return Command.SINGLE_SUCCESS
    }

    private fun executeRemove(context: CommandContext<ServerCommandSource>): Int {
        context.source.playerOrThrow.apply {
            positionManager.removePosition(pos)
            context.source.sendFeedback(
                Text.of("Position [ X: ${pos.shortX()} | Y: ${pos.shortY()} | Z: ${pos.shortZ()} ] removed from list"),
                false
            )
        }
        return Command.SINGLE_SUCCESS
    }

    private fun executeClear(context: CommandContext<ServerCommandSource>): Int {
        positionManager.clearPositions()
        context.source.sendFeedback(Text.of("Position list cleared"), false)
        return Command.SINGLE_SUCCESS
    }

    private fun executeList(context: CommandContext<ServerCommandSource>): Int {
        context.source.sendFeedback(Text.of(positionManager.getBlueMapPositions().joinToString("") { vector3d ->
            "X: ${vector3d.shortX()} | Y: ${vector3d.shortY()} | Z: ${vector3d.shortZ()}\n"
        }), false)
        return Command.SINGLE_SUCCESS
    }

}