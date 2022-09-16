package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.CommandDispatcher
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry.commandLiteral
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

object MarkerListCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal(commandLiteral).then(
                CommandManager.literal("list").then(
                    CommandManager.argument("player", EntityArgumentType.player())
                        .executes { TODO("List all markers of specify user (User level)") }
                ).executes { TODO("List all users markers (Admin level)") }
            )
        )
    }
}