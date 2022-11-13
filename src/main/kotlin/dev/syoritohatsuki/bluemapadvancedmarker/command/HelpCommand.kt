package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry.commandLiteral
import me.lucko.fabric.api.permissions.v0.Permissions
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import net.minecraft.text.Text

object HelpCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal(commandLiteral).then(
                CommandManager.literal("help").requires(
                    Permissions.require("${BlueMapAdvancedMarkerAddon.MOD_ID}.help", 0)
                ).executes { executeHelp(it) }
            )
        )
    }

    private fun executeHelp(context: CommandContext<ServerCommandSource>): Int {
        context.source.sendFeedback(
            LiteralText(
                StringBuilder().apply {
                    append("\n----------[ BAM Help ]----------")
                    append("\n/$commandLiteral help")
                    append("\n/$commandLiteral icons")
                    append("\n/$commandLiteral list")
                    append("\n/$commandLiteral listall")
                    append("\n/$commandLiteral create <name> [<icon>]")
                    append("\n/$commandLiteral remove <name> [<owner>]")
                    append("\n/$commandLiteral version")
                    append("\n------------------------------")
                }.toString()
            ), false
        )
        return Command.SINGLE_SUCCESS
    }
}