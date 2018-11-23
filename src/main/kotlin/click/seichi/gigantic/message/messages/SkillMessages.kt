package click.seichi.gigantic.message.messages

import click.seichi.gigantic.message.LocalizedText
import click.seichi.gigantic.player.skill.SkillParameters
import org.bukkit.ChatColor
import java.util.*

/**
 * @author tar0ss
 */
object SkillMessages {

    val FLASH = LocalizedText(
            Locale.JAPANESE to "フラッシュ"
    )

    val HEAL = LocalizedText(
            Locale.JAPANESE to "ヒール"
    )

    val MINE_BURST = LocalizedText(
            Locale.JAPANESE to "マイン・バースト"
    )

    val KODAMA_DRAIN = LocalizedText(
            Locale.JAPANESE to "コダマ・ドレイン"
    )


    val MINE_BURST_LORE =
            listOf(
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.GRAY}" +
                                    "アクティブ効果: 少しの間だけ掘る速度が上昇"
                    ),
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.AQUA}" +
                                    "持続時間: ${SkillParameters.MINE_BURST_DURATION}秒"
                    ),
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.AQUA}" +
                                    "クールタイム: ${SkillParameters.MINE_BURST_COOLTIME}秒"
                    ),
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.LIGHT_PURPLE}" +
                                    "\"${SkillParameters.MINE_BURST_KEY}\" キー を押して発動"
                    )
            )

    val FLASH_LORE =
            listOf(
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.GRAY}" +
                                    "アクティブ効果: 視点方向にワープ"
                    ),
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.AQUA}" +
                                    "クールタイム: ${SkillParameters.FLASH_COOLTIME}秒"
                    ),
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.LIGHT_PURPLE}" +
                                    "\"${SkillParameters.FLASH_KEY}\" キー を押して発動"
                    )
            )

    val HEAL_LORE =
            listOf(
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.GRAY}" +
                                    "パッシブ効果: ブロックを破壊して体力を回復"
                    ),
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.AQUA}" +
                                    "発動確率: ${SkillParameters.HEAL_PROBABILITY_PERCENT} %"
                    ),
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.AQUA}" +
                                    "回復量: 最大体力の${SkillParameters.HEAL_AMOUNT_PERCENT} %"
                    ),
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.LIGHT_PURPLE}" +
                                    "全ての通常破壊で発動"
                    )
            )

    val KODAMA_DRAIN_LORE =
            listOf(
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.GRAY}" +
                                    "パッシブ効果: 原木を破壊して体力を回復"
                    ),
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.GRAY}" +
                                    "※間接的に破壊したブロックは回復以外の効果を持たない"
                    ),
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.AQUA}" +
                                    "回復量(原木): 最大体力の %.1f ".format(SkillParameters.KODAMA_DRAIN_LOG_HEAL_PERCENT).plus("%")
                    ),
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.AQUA}" +
                                    "回復量(葉): 最大体力の %.1f ".format(SkillParameters.KODAMA_DRAIN_LEAVES_HEAL_PERCENT).plus("%")
                    ),
                    LocalizedText(
                            Locale.JAPANESE to "${ChatColor.LIGHT_PURPLE}" +
                                    "原木を通常破壊で発動"
                    )
            )

}