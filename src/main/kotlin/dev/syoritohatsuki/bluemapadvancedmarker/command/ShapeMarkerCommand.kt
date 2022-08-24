package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object ShapeMarkerCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(CommandManager.literal("marker").then(CommandManager.literal("shape").executes { context ->
            context.source.player?.sendMessage(Text.literal("Shape command executed"))
            return@executes 1
        }))
    }
}