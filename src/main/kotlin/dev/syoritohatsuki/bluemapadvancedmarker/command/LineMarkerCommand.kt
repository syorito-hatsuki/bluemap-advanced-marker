package dev.syoritohatsuki.bluemapadvancedmarker.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import dev.syoritohatsuki.bluemapadvancedmarker.BlueMapAdvancedMarkerAddon
import dev.syoritohatsuki.bluemapadvancedmarker.util.BlueMapHelper
import dev.syoritohatsuki.bluemapadvancedmarker.util.toVector3d
import net.minecraft.command.argument.ColorArgumentType
import net.minecraft.command.argument.Vec3ArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

object LineMarkerCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal("marker").then(
                CommandManager.literal("line").then(
                    CommandManager.argument("name", StringArgumentType.string()).then(
                        CommandManager.argument("color", ColorArgumentType.color()).then(
                            CommandManager.argument("pos1", Vec3ArgumentType.vec3()).then(
                                CommandManager.argument("pos2", Vec3ArgumentType.vec3()).executes {
                                    BlueMapAdvancedMarkerAddon.logger.info("Execute start")
                                    val player = it.source.player
                                    if (player != null) {
                                        BlueMapHelper.createLine(
                                            StringArgumentType.getString(it, "name"),
                                            ColorArgumentType.getColor(it, "color").colorValue!!,
                                            it.source.world,
                                            player,
                                            Vec3ArgumentType.getVec3(it, "pos1").toVector3d(),
                                            Vec3ArgumentType.getVec3(it, "pos2").toVector3d()
                                        )
                                    } else BlueMapAdvancedMarkerAddon.logger.info("Player not found")
                                    BlueMapAdvancedMarkerAddon.logger.info("Execute end")
                                    return@executes 1
                                }
                            )
                        )
                    )
                )
            )
        )
    }
}