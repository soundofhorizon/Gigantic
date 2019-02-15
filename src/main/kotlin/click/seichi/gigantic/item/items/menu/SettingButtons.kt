package click.seichi.gigantic.item.items.menu

import click.seichi.gigantic.extension.setDisplayName
import click.seichi.gigantic.extension.setLore
import click.seichi.gigantic.extension.wrappedLocale
import click.seichi.gigantic.item.Button
import click.seichi.gigantic.menu.menus.DisplaySettingMenu
import click.seichi.gigantic.menu.menus.ToolSwitchSettingMenu
import click.seichi.gigantic.message.messages.BagMessages
import click.seichi.gigantic.message.messages.menu.SettingMenuMessages
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * @author tar0ss
 */
object SettingButtons {

    val TOOL_SWITCH_SETTING = object : Button {

        override fun toShownItemStack(player: Player): ItemStack? {
            return ItemStack(Material.LADDER).apply {
                setDisplayName("${ChatColor.AQUA}${ChatColor.UNDERLINE}"
                        + BagMessages.SWITCH_DETAIL.asSafety(player.wrappedLocale))
                setLore(BagMessages.SWITCH_DETAIL_LORE.asSafety(player.wrappedLocale))
            }
        }

        override fun tryClick(player: Player, event: InventoryClickEvent): Boolean {
            if (event.inventory.holder === ToolSwitchSettingMenu) return false
            ToolSwitchSettingMenu.open(player)
            return true
        }

    }

    val DISPLAY_SETTING = object : Button {

        override fun toShownItemStack(player: Player): ItemStack? {
            return ItemStack(Material.REDSTONE_TORCH).apply {
                setDisplayName("${ChatColor.AQUA}${ChatColor.UNDERLINE}"
                        + SettingMenuMessages.DISPLAY.asSafety(player.wrappedLocale))
            }
        }

        override fun tryClick(player: Player, event: InventoryClickEvent): Boolean {
            if (event.inventory.holder === DisplaySettingMenu) return false
            DisplaySettingMenu.open(player)
            return true
        }

    }
}