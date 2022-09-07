package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command.SINGLE_SUCCESS
import com.mojang.brigadier.CommandDispatcher
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry.commandLiteral
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object MarkerIconsCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal(commandLiteral).then(
                CommandManager.literal("icons").executes {
                    it.source.sendFeedback(Text.literal("https://github.com/<repo>/<list_of_icons>"), false)
                    return@executes SINGLE_SUCCESS
                }
            )
        )
    }
}