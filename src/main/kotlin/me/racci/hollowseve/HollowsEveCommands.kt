package me.racci.hollowseve

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.*
import me.racci.hollowseve.enums.HollowsEve2021
import me.racci.hollowseve.factories.ItemFactory
import me.racci.raccicore.utils.extensions.pdc
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@CommandAlias("hollowseve")
class HollowsEveCommands : BaseCommand() {

    @Default
    @CommandPermission("hollowseve.debug")
    fun onEve(sender: CommandSender) {
        sender.sendMessage("this does nothing you fucking idiot")
    }

    @Subcommand("items")
    @CommandPermission("hollowseve.debug")
    @CommandCompletion("@hollowseve2021items")
    fun giveKey(sender: CommandSender, item: ItemStack) {
        (sender as Player).inventory.apply {
            setItem(firstEmpty(), item)
        }
    }
}

@CommandAlias("fixMask")
class FixMask : BaseCommand() {

    @Default
    fun onCommand(sender: CommandSender) {
        val item = (sender as Player).inventory.itemInMainHand
        if(item.type != Material.PLAYER_HEAD) {
            sender.sendMessage("Please have a mask from Hollow's Eve in your main hand to use this command!")
            return
        }
        when(item.persistentDataContainer) {
            ItemFactory[HollowsEve2021.UNDEAD_MASK].pdc -> {
                sender.inventory.setItemInMainHand(ItemFactory[HollowsEve2021.UNDEAD_MASK].asQuantity(item.amount))
                sender.sendMessage("Done!")
            }
            else -> {
                sender.sendMessage("This current is only for the Undead Mask as its the only broken mask.")
            }
        }


    }
}