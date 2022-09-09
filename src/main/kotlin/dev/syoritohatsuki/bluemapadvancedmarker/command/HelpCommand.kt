package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry.commandLiteral
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object HelpCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal(commandLiteral).then(CommandManager.literal("help").executes { executeHelp(it) })
        )
    }

    private fun executeHelp(context: CommandContext<ServerCommandSource>): Int {
        context.source.sendFeedback(
            Text.literal(
                StringBuilder().apply {
                    append("----------[ BAM Help ]----------\n")
                    append("/$commandLiteral icons\n")
                    append("/$commandLiteral point <name> <icon>\n")
                    append("--------------------------------")
                }.toString()
            ), false
        )
        return Command.SINGLE_SUCCESS
    }
}