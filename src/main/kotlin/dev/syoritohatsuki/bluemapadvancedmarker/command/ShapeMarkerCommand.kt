package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry.commandLiteral
import dev.syoritohatsuki.bluemapadvancedmarker.util.MarkerHelper
import net.minecraft.command.argument.ColorArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

object ShapeMarkerCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal(commandLiteral).then(
                CommandManager.literal("shape").then(
                    CommandManager.argument("name", StringArgumentType.string()).then(
                        CommandManager.argument("color", ColorArgumentType.color()).executes { executeShape(it) }
                    )
                )
            )
        )
    }

    private fun executeShape(context: CommandContext<ServerCommandSource>): Int {
        MarkerHelper.createShape(
            StringArgumentType.getString(context, "name"),
            ColorArgumentType.getColor(context, "color").colorValue!!,
            context.source.world,
            context.source.playerOrThrow
        )
        return Command.SINGLE_SUCCESS
    }
}