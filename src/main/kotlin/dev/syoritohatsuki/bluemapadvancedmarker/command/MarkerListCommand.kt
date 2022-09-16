package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import de.bluecolored.bluemap.api.BlueMapAPI
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon.logger
import dev.syoritohatsuki.bluemapadvancedmarker.registry.CommandRegistry.commandLiteral
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import java.util.*

object MarkerListCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal(commandLiteral).then(
                CommandManager.literal("list").then(
                    CommandManager.argument("player", EntityArgumentType.player())
                        .executes { executeUserMarkers(it) }
                ).executes { executeAllUsersMarkers(it) }
            )
        )
    }

    private fun executeUserMarkers(context: CommandContext<ServerCommandSource>): Int {
        BlueMapAPI.getInstance().get().maps.forEach { map ->
            map.markerSets.forEach { (name, value) ->
                value.markers.values.forEach {
                    logger.info(
                        "Map: ${map.name} Owner: ${
                            context.source.server.playerManager.getPlayer(
                                UUID.fromString(
                                    name
                                )
                            )
                        } Name: ${it.label}"
                    )
                }
            }
        }
        Command.SINGLE_SUCCESS
        TODO("List all markers of specify user (User level)")
    }

    private fun executeAllUsersMarkers(context: CommandContext<ServerCommandSource>): Int {
        BlueMapAPI.getInstance().get().maps.forEach { map ->
            map.markerSets.filter {
                it.key.contains(EntityArgumentType.getPlayer(context, "player").uuid.toString())
            }.map { (_, value) ->
                value.markers.values.forEach {
                    logger.info("Filtered markers: ${it.label}")
                }
            }
        }
        Command.SINGLE_SUCCESS
        TODO("List all users markers (Admin level)")
    }
}