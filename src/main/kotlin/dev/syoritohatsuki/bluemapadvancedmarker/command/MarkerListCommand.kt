package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import de.bluecolored.bluemap.api.BlueMapAPI
import dev.syoritohatsuki.bluemapadvancedmarker.manager.PlayerCacheManager
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry.commandLiteral
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import java.util.*

object MarkerListCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal(commandLiteral).then(
                CommandManager.literal("list").executes { executeOwnMarkers(it) }
            ).then(
                CommandManager.literal("listall").executes { executeAllUsersMarkers(it) }
            )
        )
    }

    private fun executeAllUsersMarkers(context: CommandContext<ServerCommandSource>): Int {
        context.source.sendFeedback(Text.of(StringBuilder().apply {
            append("\n=======[ All marker's ]=======")
            BlueMapAPI.getInstance().get().maps.forEach { map ->
                append("\n  ${map.name}")
                map.markerSets.forEach { (name, value) ->
                    value.markers.values.forEach {
                        append("\n      Map: ${map.name} Owner: ${PlayerCacheManager.getPlayer(UUID.fromString(name)).name} Name: ${it.label}")
                    }
                }
            }
        }.toString()), false)
        Command.SINGLE_SUCCESS
        TODO("List all markers of specify user (User level)")
    }

    private fun executeOwnMarkers(context: CommandContext<ServerCommandSource>): Int {
        context.source.sendFeedback(Text.of(StringBuilder().apply {
            append("\n=======[ Own marker's ]=======\n")
            BlueMapAPI.getInstance().get().maps.forEach { map ->
                append("\n  ${map.name}")
                map.markerSets.filter { it.key.contains(context.source.playerOrThrow.uuidAsString) }
                    .forEach { (_, value) ->
                        value.markers.values.forEach { append("\n      Map: ${map.name} Name: ${it.label}") }
                    }
            }
        }.toString()), false)
        Command.SINGLE_SUCCESS
        TODO("List all users markers (Admin level)")
    }
}