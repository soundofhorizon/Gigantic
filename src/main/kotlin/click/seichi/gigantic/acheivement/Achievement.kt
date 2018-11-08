package click.seichi.gigantic.acheivement

import click.seichi.gigantic.cache.key.Keys
import click.seichi.gigantic.cache.manipulator.catalog.CatalogPlayerCache
import click.seichi.gigantic.extension.find
import click.seichi.gigantic.extension.getOrPut
import click.seichi.gigantic.extension.manipulate
import click.seichi.gigantic.extension.transform
import click.seichi.gigantic.message.ChatMessage
import click.seichi.gigantic.message.messages.AchievementMessages
import org.bukkit.entity.Player

/**
 * @author tar0ss
 *
 *
 */
enum class Achievement(
        val id: Int,
        private val canGranting: (Player) -> Boolean,
        // 毎Login時とアンロック時に処理される
        val action: (Player) -> Unit = {},
        val grantMessage: ChatMessage? = null,
        private val priority: UpdatePriority = UpdatePriority.NORMAL
) {
    // messages
    JOIN_SERVER(0, { true }, action = { player ->
        player.manipulate(CatalogPlayerCache.BELT_SWITCHER) {
            it.unlock(click.seichi.gigantic.belt.Belt.DIG)
            it.unlock(click.seichi.gigantic.belt.Belt.MINE)
            it.unlock(click.seichi.gigantic.belt.Belt.CUT)
            it.setCanSwitch(click.seichi.gigantic.belt.Belt.DIG, true)
            it.setCanSwitch(click.seichi.gigantic.belt.Belt.MINE, true)
            it.setCanSwitch(click.seichi.gigantic.belt.Belt.CUT, true)
        }
    }, grantMessage = AchievementMessages.FIRST_JOIN),
    FIRST_LEVEL_UP(1, {
        it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 1
    }, grantMessage = AchievementMessages.FIRST_LEVEL_UP),

    // systems
    MANA_STONE(100, {
        it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 10
    }, grantMessage = AchievementMessages.UNLOCK_MANA_STONE,
            priority = UpdatePriority.HIGHEST),
    TELEPORT(101, {
        it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 15
    }, grantMessage = AchievementMessages.UNLOCK_TELEPORT),
    WILL_O_THE_WISP(102, {
        it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 14
    }, grantMessage = AchievementMessages.UNLOCK_WILL_O_THE_WISP),

    // skills
    SKILL_FLASH(200, {
        it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 5
    }, grantMessage = AchievementMessages.UNLOCK_FLASH),
    SKILL_MINE_BURST(201, {
        it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 7
    }, grantMessage = AchievementMessages.SKILL_MINE_BURST),

    // spells
    SPELL_STELLA_CLAIR(300, {
        MANA_STONE.isGranted(it) &&
                it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 10
    }, grantMessage = AchievementMessages.UNLOCK_STELLA_CLAIR),
    SPELL_TERRA_DRAIN(301, {
        MANA_STONE.isGranted(it) &&
                it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 10
    }, grantMessage = AchievementMessages.UNLOCK_TERRA_DRAIN),
    SPELL_GRAND_NATURA(302, {
        MANA_STONE.isGranted(it) &&
                it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 14
    }, grantMessage = AchievementMessages.UNLOCK_GRAND_NATURA),

    SPELL_AQUA_LINEA(303, {
        MANA_STONE.isGranted(it) &&
                it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 18
    }, grantMessage = AchievementMessages.UNLOCK_AQUA_LINEA),


    ;

    /**1から順に [update] される**/
    enum class UpdatePriority(val amount: Int) {
        HIGHEST(1), HIGH(2), NORMAL(3), LOW(4), LOWEST(5)
    }

    companion object {
        fun update(player: Player, isAction: Boolean = false) {
            values().sortedByDescending { it -> it.priority.amount }
                    .forEach {
                        it.update(player, isAction)
                    }
        }
    }


    private fun update(player: Player, isAction: Boolean) {
        if (canGrant(player)) {
            if (isGranted(player)) {
                // 現在も解除可能で既に解除済みの時
                if (isAction) {
                    action
                }
            } else {
                // 現在も解除可能で解除していない時
                grant(player)
            }
        } else {
            if (isGranted(player)) {
                // 現在解除できないがすでに解除しているとき
                revoke(player)
            }
        }
    }

    // 与える
    private fun grant(player: Player) {
        // 解除処理
        player.transform(Keys.ACHIEVEMENT_MAP[this] ?: return) { hasUnlocked ->
            if (!hasUnlocked) {
                action(player)
                grantMessage?.sendTo(player)
            }
            true
        }
    }

    // はく奪する
    private fun revoke(player: Player) {
        // ロック処理
        player.transform(Keys.ACHIEVEMENT_MAP[this] ?: return) {
            false
        }
    }

    private fun canGrant(player: Player) = canGranting(player)

    fun isGranted(player: Player) = canGrant(player) && player.getOrPut(Keys.ACHIEVEMENT_MAP[this]!!)
}