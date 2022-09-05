package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon
import dev.syoritohatsuki.bluemapadvancedmarker.util.MarkerHelper
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

class PointMarkerCommand(dispatcher: CommandDispatcher<ServerCommandSource>) {
    init {
        dispatcher.register(
            CommandManager.literal("marker").then(
                CommandManager.literal("point").then(
                    CommandManager.argument("name", StringArgumentType.string()).then(
                        CommandManager.argument("iconName", StringArgumentType.word()).executes { executePoint(it) })
                )
            )
        )
    }

    private fun executePoint(context: CommandContext<ServerCommandSource>): Int {
        MarkerHelper.createPoint(
            StringArgumentType.getString(context, "name"),
            StringArgumentType.getString(context, "iconName"),
            context.source.world,
            context.source.playerOrThrow,
            BlueMapAdvancedMarkerAddon.positionManager.getBlueMapPositions()
        )
        return Command.SINGLE_SUCCESS
    }
}