package click.seichi.gigantic.message

import click.seichi.gigantic.cache.manipulator.manipulators.Mana
import click.seichi.gigantic.player.Defaults
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import kotlin.math.roundToInt

/**
 * @author tar0ss
 */
class ManaMessage(
        val mana: Mana,
        // 棒の本数(double値)
        amount: Double
) : Message {

    private val maxNum = Defaults.MANA_BAR_NUM

    private val nextNum = when {
        amount > maxNum - 1 && maxNum > amount -> maxNum - 1
        amount > 0 && 1 > amount -> 1
        amount > maxNum -> maxNum
        else -> amount.roundToInt()
    }

    private val remainNumString = (1..nextNum).joinToString(
            prefix = "${ChatColor.AQUA}",
            separator = ""
    ) { Defaults.MANA_CHAR }

    private val lostNumString = (1..(maxNum - nextNum)).joinToString(
            prefix = "${ChatColor.GRAY}",
            separator = ""
    ) { Defaults.MANA_LOST_CHAR }

    private val spaceString = (1..(63 - maxNum)).joinToString(separator = "") { " " }

    override fun sendTo(player: Player) {
        player.sendTitle(
                "",
                "$remainNumString$lostNumString$spaceString",
                0,
                80,
                10
        )
    }
}