package click.seichi.gigantic.item.items.menu

import click.seichi.gigantic.Gigantic
import click.seichi.gigantic.cache.RankingPlayerCacheMemory
import click.seichi.gigantic.cache.key.Keys
import click.seichi.gigantic.extension.*
import click.seichi.gigantic.head.Head
import click.seichi.gigantic.item.Button
import click.seichi.gigantic.message.messages.RankingMessages
import click.seichi.gigantic.ranking.Score
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * @author tar0ss
 */
object RankingButtons {

    val DIAMOND =
            object : Button {
                override fun toShownItemStack(player: Player): ItemStack? {
                    return itemStackOf(Material.DIAMOND_HORSE_ARMOR) {
                        setDisplayName(player, RankingMessages.DIAMOND)
                        sublime()
                    }
                }
            }


    val GOLD =
            object : Button {
                override fun toShownItemStack(player: Player): ItemStack? {
                    return itemStackOf(Material.GOLDEN_HORSE_ARMOR) {
                        setDisplayName(player, RankingMessages.GOLD)
                        sublime()
                    }
                }
            }


    val SILVER =
            object : Button {
                override fun toShownItemStack(player: Player): ItemStack? {
                    return itemStackOf(Material.IRON_HORSE_ARMOR) {
                        setDisplayName(player, RankingMessages.SILVER)
                        sublime()
                    }
                }
            }


    val RANKED_PLAYER: (Score, Int) -> Button = { score: Score, rank: Int ->
        object : Button {
            override fun toShownItemStack(player: Player): ItemStack? {
                val ranking = Gigantic.RANKING_MAP[score] ?: return null
                val uniqueId = ranking.findUUID(rank) ?: return null
                val value = ranking.findValue(uniqueId) ?: return null
                return Head.getOfflinePlayerHead(uniqueId)?.apply {
                    setDisplayName(player, RankingMessages.RANKED_PLAYER(rank, player.name))
                    setLore(*RankingMessages.RANKED_PLAYER_LORE(value)
                            .map {
                                it.asSafety(player.wrappedLocale)
                            }.toTypedArray()
                    )
                }
            }
        }
    }

    val SCORE: (Score) -> Button = { score: Score ->
        object : Button {
            override fun toShownItemStack(player: Player): ItemStack? {
                val ranking = Gigantic.RANKING_MAP[score]
                val rank = ranking?.findRank(player.uniqueId)
                val value = ranking?.findValue(player.uniqueId)
                return score.getIcon().apply {
                    setDisplayName(player, RankingMessages.SCORE(score))
                    sublime()
                    setEnchanted(true)
                    if (rank != null && value != null) {
                        setLore(*RankingMessages.SCORE_LORE(rank, value)
                                .map {
                                    it.asSafety(player.wrappedLocale)
                                }
                                .toTypedArray())
                        if (rank > 1) {
                            val nextrank = rank - 1
                            val nextRankUniqueId = ranking.findUUID(nextrank) ?: return@apply
                            val nextRankCache = RankingPlayerCacheMemory.find(nextRankUniqueId) ?: return@apply
                            val nextRankPlayerName = nextRankCache.getOrPut(Keys.RANK_PLAYER_NAME)
                            val nextRankValue = ranking.findValue(nextRankUniqueId) ?: return@apply
                            val diff = nextRankValue - value
                            // TODO implements
                        }
                    } else {
                        setLore(*RankingMessages.NO_DATA
                                .map {
                                    it.asSafety(player.wrappedLocale)
                                }.toTypedArray()
                        )
                    }
                }
            }
        }
    }

}