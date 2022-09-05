package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

class ExtrudeMarkerCommand(dispatcher: CommandDispatcher<ServerCommandSource>) {
    init {
        dispatcher.register(
            CommandManager.literal("marker").then(CommandManager.literal("extrude").executes { context ->
                TODO("Extrude command")
                return@executes Command.SINGLE_SUCCESS
            })
        )
    }
}