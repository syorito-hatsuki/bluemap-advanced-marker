package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.CommandDispatcher
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry.commandLiteral
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

object MarkerListCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal(commandLiteral).then(
                // TODO Show user owned markers
            )
        )
    }
}