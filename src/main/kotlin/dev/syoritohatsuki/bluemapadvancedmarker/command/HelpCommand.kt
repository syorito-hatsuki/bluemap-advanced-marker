package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object HelpCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal("marker").then(CommandManager.literal("help").executes { executeHelp(it) })
        )
    }

    private fun executeHelp(context: CommandContext<ServerCommandSource>): Int {
        context.source.sendFeedback(
            Text.literal(
                StringBuilder().apply {
                    append("----------[ BAM Help ]----------")
                    append("/marker position add|remove|list|clear\n")
                    append("/marker point <name>\n")
                    append("/marker line <name> <color>\n")
                    append("/marker shape <name> <color>\n")
                    append("/marker extrude <name> <color>")
                    append("--------------------------------")
                }.toString()
            ), false
        )
        return Command.SINGLE_SUCCESS
    }
}