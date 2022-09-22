package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.MOD_ID
import dev.syoritohatsuki.bluemapadvancedmarker.manager.MarkersManager
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry.commandLiteral
import me.lucko.fabric.api.permissions.v0.Permissions
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

object PointMarkerCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal(commandLiteral).then(
                CommandManager.literal("create").requires(Permissions.require("$MOD_ID.create", 0))
                    .then(CommandManager.argument("name", StringArgumentType.string())
                        .executes { executeAddPoint(it, false) }
                        .then(CommandManager.argument("icon", StringArgumentType.word())
                            .executes { executeAddPoint(it, true) })
                    )
            ).then(
                CommandManager.literal("remove").requires(Permissions.require("$MOD_ID.remove", 0)).then(
                    CommandManager.argument("name", StringArgumentType.string()).executes { executeRemovePoint(it) })
            )
        )
    }

    private fun executeAddPoint(context: CommandContext<ServerCommandSource>, withIcon: Boolean): Int {
        val icon: String = if (withIcon) StringArgumentType.getString(context, "icon") else ""

        MarkersManager.createPoint(
            StringArgumentType.getString(context, "name"),
            icon,
            context.source.world,
            context.source.playerOrThrow
        )
        return Command.SINGLE_SUCCESS
    }

    private fun executeRemovePoint(context: CommandContext<ServerCommandSource>): Int {
        MarkersManager.removeMarker(StringArgumentType.getString(context, "name"), context.source.playerOrThrow)
        return Command.SINGLE_SUCCESS
    }
}