package me.racci.hollowseve

import co.aikar.commands.BaseCommand
import me.racci.hollowseve.enums.HollowsEve2021
import me.racci.hollowseve.factories.ItemFactory
import me.racci.hollowseve.listeners.HollowEve2021Listener
import me.racci.raccicore.RacciPlugin
import me.racci.raccicore.utils.extensions.KotlinListener
import org.bukkit.inventory.ItemStack
import java.util.*

internal lateinit var plugin        : HollowsEve; private set
lateinit var itemFactory   : ItemFactory; private set

class HollowsEve : RacciPlugin() {


    override fun handleEnable() {
        plugin = this
        itemFactory = ItemFactory()
    }

    override fun handleDisable() {

        commandManager.unregisterCommands()

    }

    private fun String.capitalizeWords() =
        split(
            " ",
            "_"
        ).joinToString("") { it -> it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }

    private fun String.splitCapsThing() : String {
        var newString = ""
        forEachIndexed { i, c ->
            newString += ("${if(c.isUpperCase() && i > 0) "_" else ""}${c.uppercase()}")
        }
        return newString
    }

    override fun registerListeners(): List<KotlinListener> {
        return listOf(
            HollowEve2021Listener()
        )
    }


    override fun registerCommands(): List<BaseCommand> {
        commandManager.commandContexts.registerContext(ItemStack::class.java) {
            itemFactory.items.getValue(HollowsEve2021.valueOf(
                it.popFirstArg().splitCapsThing()
            ))}
            commandManager.commandCompletions.registerAsyncCompletion("hollowseve2021items") { _ ->
            itemFactory.items.entries.map{it.key.name.lowercase().capitalizeWords()}}
        commandManager.locales.defaultLocale = Locale.ENGLISH
        return listOf(
            HollowsEveCommands()
        )
    }









}