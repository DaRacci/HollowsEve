package me.racci.hollowseve

import co.aikar.commands.BaseCommand
import me.racci.hollowseve.enums.HollowsEve2021
import me.racci.hollowseve.factories.ItemFactory
import me.racci.raccicore.RacciPlugin
import org.bukkit.inventory.ItemStack
import java.util.*

internal lateinit var plugin        : HollowsEve; private set
internal lateinit var itemFactory   : ItemFactory; private set

class HollowsEve : RacciPlugin() {


    override fun handleEnable() {
        plugin = this
        itemFactory = ItemFactory()
    }

    override fun handleDisable() {

        commandManager.unregisterCommands()

    }

    override fun registerCommands(): List<BaseCommand> {
        commandManager.commandContexts.registerContext(ItemStack::class.java) {
            itemFactory.items.getValue(HollowsEve2021.valueOf(it.popFirstArg().uppercase().replace(" ", "_")))}
        commandManager.commandCompletions.registerAsyncCompletion("hollowseve2021items") { _ ->
            itemFactory.items.entries.map{it.key.name.lowercase().replace("_", " ")}}
        commandManager.locales.defaultLocale = Locale.ENGLISH
        return listOf(
            HollowsEveCommands()
        )
    }









}