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

class ShapeMarkerCommand(dispatcher: CommandDispatcher<ServerCommandSource>) {
    init {
        dispatcher.register(
            CommandManager.literal("marker").then(
                CommandManager.literal("shape").then(
                    CommandManager.argument("name", StringArgumentType.string()).then(
                        CommandManager.argument("color", ColorArgumentType.color())
                    ).executes { executeShape(it) }
                )
            )
        )
    }

    private fun executeShape(context: CommandContext<ServerCommandSource>): Int {
        MarkerHelper.createShape(
            StringArgumentType.getString(context, "name"),
            ColorArgumentType.getColor(context, "color").colorValue!!,
            context.source.world,
            context.source.playerOrThrow,
            positionManager.getBlueMapPositions().map { it.toVector2() },
            positionManager.getAverageHeight()
        )
        return Command.SINGLE_SUCCESS
    }
}