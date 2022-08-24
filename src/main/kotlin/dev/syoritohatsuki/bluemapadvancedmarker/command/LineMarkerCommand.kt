package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon
import dev.syoritohatsuki.bluemapadvancedmarker.util.BlueMapHelper
import net.minecraft.command.argument.Vec3ArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

object LineMarkerCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal("marker").then(
                CommandManager.literal("line").then(
                    CommandManager.argument("pos1", Vec3ArgumentType.vec3()).then(
                        CommandManager.argument("pos2", Vec3ArgumentType.vec3()).then(
                            CommandManager.argument("name", StringArgumentType.string()).executes {
                                BlueMapAdvancedMarkerAddon.logger.info("Execute start")
                                val player = it.source.player

                                if (player != null) BlueMapHelper.createLine(player)
                                else BlueMapAdvancedMarkerAddon.logger.info("Player not found")

                                BlueMapAdvancedMarkerAddon.logger.info("Execute end")
                                return@executes 1
                            }
                        )
                    )
                )
            )
        )
    }
}