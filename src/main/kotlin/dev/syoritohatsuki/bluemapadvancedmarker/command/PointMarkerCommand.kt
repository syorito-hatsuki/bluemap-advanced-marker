package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry.commandLiteral
import dev.syoritohatsuki.bluemapadvancedmarker.util.MarkerHelper
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

object PointMarkerCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal(commandLiteral).then(
                CommandManager.literal("point").then(
                    CommandManager.literal("add").then(
                        CommandManager.argument("name", StringArgumentType.string()).then(
                            CommandManager.argument("icon", StringArgumentType.word()).executes { executePoint(it) }
                        )
                    )
                ).then(
                    CommandManager.literal("remove").then(
                        CommandManager.argument("name", StringArgumentType.string()).executes {
                            TODO("Remove marker by name")
                        }
                    )
                )
            )
        )
    }

    private fun executePoint(context: CommandContext<ServerCommandSource>): Int {
        MarkerHelper.createPoint(
            StringArgumentType.getString(context, "name"),
            StringArgumentType.getString(context, "icon"),
            context.source.world,
            context.source.playerOrThrow
        )
        return Command.SINGLE_SUCCESS
    }
}