package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import de.bluecolored.bluemap.api.BlueMapAPI
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.MOD_ID
import dev.syoritohatsuki.bluemapadvancedmarker.manager.PlayerCacheManager
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry.commandLiteral
import me.lucko.fabric.api.permissions.v0.Permissions
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import java.util.*

object MarkerListCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal(commandLiteral).then(
                CommandManager.literal("list").requires(
                    Permissions.require("$MOD_ID.list", 0)
                ).executes { executeOwnMarkers(it) }.then(
                    CommandManager.argument("playerName", StringArgumentType.word())
                        .requires(Permissions.require("$MOD_ID.list.other", 2))
                        .executes { executeListUserPoint(it, false) }).then(
                    CommandManager.argument("playerEntity", EntityArgumentType.player())
                        .requires(Permissions.require("$MOD_ID.list.other", 2))
                        .executes { executeListUserPoint(it, true) })
            ).then(
                CommandManager.literal("listall").requires(
                    Permissions.require("$MOD_ID.listall", 2)
                ).executes { executeAllUsersMarkers(it) }
            )
        )
    }

    private fun executeAllUsersMarkers(context: CommandContext<ServerCommandSource>): Int {
        context.source.sendFeedback(Text.of(StringBuilder().apply {
            append("\n=======[ All marker's ]=======\n")
            BlueMapAPI.getInstance().get().maps.forEach { map ->
                append("\n  ${map.name}")
                map.markerSets.forEach { (name, value) ->
                    value.markers.values.forEach {
                        append("\n      Map: ${map.name} Owner: ${PlayerCacheManager.getPlayer(UUID.fromString(name)).name} Name: ${it.label}")
                    }
                }
                append("\n")
            }
        }.toString()), false)
        return Command.SINGLE_SUCCESS
    }

    private fun executeOwnMarkers(context: CommandContext<ServerCommandSource>): Int {
        context.source.sendFeedback(Text.of(StringBuilder().apply {
            append("\n=======[ Own marker's ]=======\n")
            BlueMapAPI.getInstance().get().maps.forEach { map ->
                append("\n  ${map.name}")
                map.markerSets.filter { it.key.contains(context.source.player.uuidAsString) }
                    .forEach { (_, value) ->
                        value.markers.values.forEach { append("\n      Map: ${map.name} Name: ${it.label}") }
                    }
                append("\n")
            }
        }.toString()), false)
        return Command.SINGLE_SUCCESS
    }

    private fun executeListUserPoint(context: CommandContext<ServerCommandSource>, entity: Boolean): Int {
        val uuid = when {
            entity -> EntityArgumentType.getPlayer(context, "playerEntity").uuidAsString
            else -> PlayerCacheManager.getPlayer(StringArgumentType.getString(context, "playerName")).uuid
        }

        context.source.sendFeedback(Text.of(StringBuilder().apply {
            append("\n=======[ ${PlayerCacheManager.getPlayer(uuid).name} marker's ]=======\n")
            BlueMapAPI.getInstance().get().maps.forEach { map ->
                append("\n  ${map.name}")
                map.markerSets.filter { it.key.contains(uuid) }
                    .forEach { (_, value) ->
                        value.markers.values.forEach { append("\n      Map: ${map.name} Name: ${it.label}") }
                    }
                append("\n")
            }
        }.toString()), false)
        return Command.SINGLE_SUCCESS
    }
}