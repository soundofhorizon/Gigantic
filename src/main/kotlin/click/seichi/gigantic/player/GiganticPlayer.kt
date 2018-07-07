package click.seichi.gigantic.player

import click.seichi.gigantic.database.dao.User
import click.seichi.gigantic.database.dao.UserWill
import click.seichi.gigantic.player.components.PlayerSetting
import click.seichi.gigantic.player.components.PlayerStatus
import click.seichi.gigantic.will.Will
import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * @author tar0ss
 */
class GiganticPlayer(
        user: User,
        willMap: Map<Will, UserWill>,
        val isFirstJoin: Boolean = false) {

    val player: Player
        get() = Bukkit.getServer().getPlayer(uniqueId)

    val uniqueId = user.id.value

    val setting = PlayerSetting(user)

    val status = PlayerStatus(user, willMap)

    fun save(user: User, willMap: Map<Will, UserWill>) {
        user.locale = setting.locale.toString()
        user.mana = status.mana.current
        user.mineBlock = status.mineBlock.current
        willMap.forEach { will, userWill ->
            userWill.memory = status.memory.memoryMap[will] ?: 0
            userWill.hasAptitude = status.aptitude.willAptitude.contains(will)
        }
    }

}