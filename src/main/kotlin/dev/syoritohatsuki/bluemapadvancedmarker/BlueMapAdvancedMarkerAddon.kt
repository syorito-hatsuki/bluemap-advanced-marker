package dev.syoritohatsuki.bluemapadvancedmarker

import com.mojang.logging.LogUtils
import de.bluecolored.bluemap.api.BlueMapAPI
import dev.syoritohatsuki.bluemapadvancedmarker.command.ExtrudeMarkerCommand
import dev.syoritohatsuki.bluemapadvancedmarker.command.LineMarkerCommand
import dev.syoritohatsuki.bluemapadvancedmarker.command.PositionCommand
import dev.syoritohatsuki.bluemapadvancedmarker.command.ShapeMarkerCommand
import dev.syoritohatsuki.bluemapadvancedmarker.util.PositionManager
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.player.AttackBlockCallback
import net.minecraft.util.ActionResult
import org.slf4j.Logger

object BlueMapAdvancedMarkerAddon : ModInitializer {
    val logger: Logger = LogUtils.getLogger()
    val positionManager = PositionManager

    override fun onInitialize() {
        logger.info("${javaClass.simpleName} initialized")

        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, registryAccess, environment ->
            ShapeMarkerCommand.register(dispatcher)
            LineMarkerCommand.register(dispatcher)
            ExtrudeMarkerCommand.register(dispatcher)
            PositionCommand.register(dispatcher)
        })

        AttackBlockCallback.EVENT.register(AttackBlockCallback { player, world, hand, pos, direction ->

            BlueMapAPI.getInstance().ifPresent { blueMapAPI ->
                blueMapAPI.getWorld(player.world).ifPresent { blueMapWorld ->
                    blueMapWorld.maps.forEach {
                        logger.info(it.id)
                        logger.info(it.name)
                        logger.info(it.world.toString())
                    }
                }
            }

            ActionResult.PASS
        })

    }
}