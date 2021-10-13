package me.racci.hollowseve

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.*
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