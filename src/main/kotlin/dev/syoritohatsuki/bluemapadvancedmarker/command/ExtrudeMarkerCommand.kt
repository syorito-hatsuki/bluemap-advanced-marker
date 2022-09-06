package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.positionManager
import dev.syoritohatsuki.bluemapadvancedmarker.util.MarkerHelper
import net.minecraft.command.argument.ColorArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

object ExtrudeMarkerCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal("marker").then(
                CommandManager.literal("extrude").then(
                    CommandManager.argument("name", StringArgumentType.string()).then(
                        CommandManager.argument("color", ColorArgumentType.color()).executes { executeExtrude(it) }
                    )
                )
            )
        )
    }

    private fun executeExtrude(context: CommandContext<ServerCommandSource>): Int {
        MarkerHelper.createExtrude(
            StringArgumentType.getString(context, "name"),
            ColorArgumentType.getColor(context, "color").colorValue!!,
            context.source.world,
            context.source.playerOrThrow,
            positionManager.getBlueMapPositions().map { it.toVector2() },
            positionManager.getMinHeight(),
            positionManager.getMaxHeight()
        )
        return Command.SINGLE_SUCCESS
    }
}