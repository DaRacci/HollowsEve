package me.racci.hollowseve

import co.aikar.commands.BaseCommand
import me.racci.hollowseve.enums.HollowsEve2021
import me.racci.hollowseve.factories.GUI
import me.racci.hollowseve.factories.ItemFactory
import me.racci.hollowseve.factories.RecipeFactory
import me.racci.hollowseve.listeners.HollowEve2021Listener
import me.racci.raccicore.RacciPlugin
import me.racci.raccicore.utils.extensions.KotlinListener
import org.bukkit.inventory.ItemStack
import java.util.*

internal lateinit var plugin        : HollowsEve; private set
lateinit var gui                    : GUI; private set

class HollowsEve : RacciPlugin() {


    override suspend fun onEnableAsync() {
        println("Im Being Enabled!")
        plugin = this
        super.onEnableAsync()
    }

    override suspend fun handleEnable() {
        println("Im still being Enabled")
        ItemFactory.init()
        RecipeFactory.init()
        gui = GUI()
    }

    override suspend fun handleDisable() {
        println("Im being Disabled")
        RecipeFactory.shutdown()
        ItemFactory.shutdown()
    }

    override suspend fun registerListeners(): List<KotlinListener> {
        println("Im Registering my Listeners")
        return listOf(
            HollowEve2021Listener()
        )
    }


    override suspend fun registerCommands(): List<BaseCommand> {
        println("Im Registering my Commands")
        commandManager.commandContexts.registerContext(ItemStack::class.java) {
            ItemFactory.items.getValue(HollowsEve2021.valueOf(
                it.popFirstArg().splitCapsThing()
            ))}
            commandManager.commandCompletions.registerAsyncCompletion("hollowseve2021items") { _ ->
                ItemFactory.items.entries.map{it.key.name.lowercase().capitalizeWords(true)}}
        commandManager.locales.defaultLocale = Locale.ENGLISH
        return listOf(
            HollowsEveCommands(),
            FixMask()
        )
    }
}

internal fun String.capitalizeWords(removeSpaces: Boolean = false) =
    split(
        " ",
        "_"
    ).joinToString(if(removeSpaces) "" else " ") { it -> it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }

internal fun String.splitCapsThing() : String {
    var newString = ""
    forEachIndexed { i, c ->
        newString += ("${if(c.isUpperCase() && i > 0) "_" else ""}${c.uppercase()}")
    }
    return newString
}