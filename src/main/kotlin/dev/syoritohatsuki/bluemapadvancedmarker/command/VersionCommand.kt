package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry.commandLiteral
import dev.syoritohatsuki.bluemapadvancedmarker.util.getVersion
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object VersionCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal(commandLiteral).executes { executeVersion(it) }
                .then(CommandManager.literal("version").executes { executeVersion(it) })
        )
    }

    private fun executeVersion(context: CommandContext<ServerCommandSource>): Int {
        context.source.sendFeedback(Text.of("BAM Version: ${getVersion()}"), false)
        return Command.SINGLE_SUCCESS
    }
}