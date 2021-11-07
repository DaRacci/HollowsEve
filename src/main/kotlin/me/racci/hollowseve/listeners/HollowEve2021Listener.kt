package me.racci.hollowseve.listeners

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent
import com.github.shynixn.mccoroutine.asyncDispatcher
import com.github.shynixn.mccoroutine.minecraftDispatcher
import kotlinx.coroutines.withContext
import me.racci.hollowseve.enums.HollowsEve2021
import me.racci.hollowseve.factories.ItemFactory
import me.racci.hollowseve.gui
import me.racci.hollowseve.plugin
import me.racci.raccicore.utils.extensions.KotlinListener
import me.racci.raccicore.utils.now
import net.citizensnpcs.api.event.NPCRightClickEvent
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import su.nightexpress.goldencrates.api.GoldenCratesAPI
import java.time.Duration
import java.util.UUID
import kotlin.random.Random

class HollowEve2021Listener : KotlinListener {

    private val speedModifier = AttributeModifier(
        UUID.fromString("9f6ffc74-2d64-11ec-8d3d-0242ac130003"),
        "CANDYCORNARMOUR",
        0.25,
        AttributeModifier.Operation.ADD_SCALAR
    )

    private val invisibilityPotion = PotionEffect(
        PotionEffectType.INVISIBILITY,
        Int.MAX_VALUE,
        0,
        false,
        false,
        false,
        ItemFactory[HollowsEve2021.HOLLOWS_EVE_HAT, true]
    )

    private val foodPotions = arrayOf(
        arrayListOf(
            PotionEffect(
                PotionEffectType.HUNGER,
                60,
                4,
                false,
                false,
                false
            ),
            PotionEffect(
                PotionEffectType.SATURATION,
                60,
                9,
                false,
                false,
                false
            ),
            PotionEffect(
                PotionEffectType.SPEED,
                60,
                5,
                false,
                false,
                false
            ),
        ),
        arrayListOf(
            PotionEffect(PotionEffectType.HUNGER, 60, 10, false, false, false),
            PotionEffect(PotionEffectType.SATURATION, 60, 9, false, false, false),
            PotionEffect(PotionEffectType.SPEED, 60, 5, false, false, false),
        )
    )

    private val npcUUID = arrayOf(
        UUID.fromString("b4099652-20ec-4105-b649-ac1192b8dac9"), // GIGI
        UUID.fromString("6096cbed-01b4-4092-945a-687ae32d02b3"), // Bug
        UUID.fromString("11a2248a-0b0b-4bae-b56a-4d653d149be6"), // Chuck
    )

    private val trickOrTreatNamespacedkey = arrayOf(
        NamespacedKey("sylph", "trickortreat_gigi"),
        NamespacedKey("sylph", "trickortreat_bug"),
        NamespacedKey("sylph", "trickortreat_chuck"),
    )

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    suspend fun onCandyCornArmour(event: PlayerArmorChangeEvent) = withContext(plugin.asyncDispatcher) {
        val oldPDC = event.oldItem?.itemMeta?.persistentDataContainer
        val newPDC = event.newItem?.itemMeta?.persistentDataContainer

        if(event.player.isDead) return@withContext


        if(newPDC != oldPDC) {
            if(oldPDC?.has(ItemFactory[HollowsEve2021.CANDY_CORN_ARMOUR, true], PersistentDataType.BYTE) == true) {
                event.player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.apply {
                    if(this.modifiers.contains(speedModifier)) {
                        removeModifier(speedModifier)
                    }
                }
            } else if(oldPDC?.has(ItemFactory[HollowsEve2021.HOLLOWS_EVE_HAT, true], PersistentDataType.BYTE) == true
                && event.player.getPotionEffect(PotionEffectType.INVISIBILITY)?.key == ItemFactory[HollowsEve2021.HOLLOWS_EVE_HAT, true]) {
                withContext(plugin.minecraftDispatcher) {event.player.removePotionEffect(PotionEffectType.INVISIBILITY)}
            }

            if(newPDC?.has(ItemFactory[HollowsEve2021.CANDY_CORN_ARMOUR, true], PersistentDataType.BYTE) == true) {
                if(event.player.inventory.armorContents.toList().filter { it.persistentDataContainer.has(ItemFactory[HollowsEve2021.CANDY_CORN_ARMOUR, true], PersistentDataType.BYTE) }.size == 4) {
                    event.player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.apply {
                        if(!this.modifiers.contains(speedModifier)) {
                            event.player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.addModifier(speedModifier)
                        }
                    }
                }
            } else if(newPDC?.has(ItemFactory[HollowsEve2021.HOLLOWS_EVE_HAT, true], PersistentDataType.BYTE) == true) {
                withContext(plugin.minecraftDispatcher) {event.player.addPotionEffect(invisibilityPotion)}
            }
        }
    }

//    @EventHandler
//    fun onHeadHat(event: InventoryClickEvent) {
//        if(event.cursor != null && !event.isShiftClick && (event.clickedInventory?.type == InventoryType.PLAYER
//            || event.clickedInventory?.type == InventoryType.CRAFTING)
//            && event.rawSlot == 5) {
//            event.isCancelled = true
//
//            val slotI = event.currentItem
//            val slotC = event.cursor
//
//            event.currentItem = slotC
//            event.whoClicked.setItemOnCursor(slotI)
//
//        }
//    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onPlaceHatOrMask(event: BlockPlaceEvent) {
        if(event.itemInHand.hasCustomModelData() && event.itemInHand.customModelData == 55) {
            event.isCancelled = true
        }
    }

    private fun trickOrTreat() =
        when(Random.nextFloat()) {
            in 0f..0.33f -> ItemFactory[HollowsEve2021.CANDY_CORN].asQuantity(Random.nextInt(11, 17))  // 5 - 9
            in 0.33f..0.44f -> ItemFactory[HollowsEve2021.CANDIED_BERRIES].asQuantity(Random.nextInt(6, 12)) // 3 - 5
            in 0.44f..0.55f -> ItemFactory[HollowsEve2021.GUMMY_FISH].asQuantity(Random.nextInt(6, 9)) // 3-5
            in 0.55f..0.66f -> ItemFactory[HollowsEve2021.BOWL_OF_CHOCOLATES].asQuantity(Random.nextInt(2, 5))
            in 0.66f..1f -> GoldenCratesAPI.getKeyManager().getKeyById("hollowseve")?.item?.asQuantity(Random.nextInt(1, 2))
            else -> null
        }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    fun onInteractChuck(event: NPCRightClickEvent) {
        if(event.npc.uniqueId != UUID.fromString("11a2248a-0b0b-4bae-b56a-4d653d149be6")) return
        if (event.clicker.inventory.itemInMainHand.type == Material.BUCKET) return
        gui.chuckGUI.show(event.clicker)
    }

//    @EventHandler
//    fun onFixUndeadHead(event: InventoryClickEvent) {
//        if(event.clickedInventory !is CraftingInventory) return
//        if(event.slotType != InventoryType.SlotType.CRAFTING) return
//        if(event.action != InventoryAction.PLACE_ALL
//            || event.action != InventoryAction.PLACE_ONE
//            || event.action != InventoryAction.PLACE_SOME) return
//        if(event.rawSlot != 5) return
//        (event.clickedInventory as CraftingInventory).getItem(5).apply {
//            if(this == null) return
//            if(this.type != Material.PLAYER_HEAD) return
//            if(!this.persistentDataContainer.has(itemFactory[HollowsEve2021.UNDEAD_MASK, true]!!, PersistentDataType.BYTE)) return
//        }
//        (event.clickedInventory as CraftingInventory).setItem(5, itemFactory[HollowsEve2021.UNDEAD_MASK]!!)
//    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    suspend fun onInteractNPC(event: NPCRightClickEvent) = withContext(plugin.asyncDispatcher){
        if(event.npc.uniqueId !in npcUUID) return@withContext
        event.clicker.inventory.itemInMainHand.apply {
            if (this.type != Material.BUCKET) return@withContext
            if (this.persistentDataContainer != ItemFactory[HollowsEve2021.CANDY_PAIL].persistentDataContainer) return@withContext
        }

        val namespacedKey = when(event.npc.uniqueId) {
            npcUUID[0] -> {trickOrTreatNamespacedkey[0]} // Gigi
            npcUUID[1] -> {trickOrTreatNamespacedkey[1]} // Bug
            npcUUID[2] -> {trickOrTreatNamespacedkey[2]} // Chuck
            else -> return@withContext
        }

        val pdc = event.clicker.persistentDataContainer
        if(pdc[namespacedKey, PersistentDataType.LONG] == null) {
            pdc[namespacedKey, PersistentDataType.LONG] = 0L
        }
        if(now().epochSeconds - pdc[namespacedKey, PersistentDataType.LONG]!! < 0) {
            pdc[namespacedKey, PersistentDataType.LONG] = 0L
        }

        pdc[namespacedKey, PersistentDataType.LONG]!!.apply {
            println("$this")
            println("${now().epochSeconds - this}")
            if((now().epochSeconds - this) < 28800) {
                val var1 : Double = (28800.0 - (now().epochSeconds - this)) / 3600
                val var2 = var1.toString().split(".").toTypedArray()
                var2[1] = (var2[1].take(2).toInt() * 60).toString()
                var2.joinToString()
                var string = ""
                if(var2[0].toInt() > 0) {
                    var2[0].apply {
                        string = if(this.toInt() > 1) "${this.take(2)} hours and " else "${this.take(2)} hour and "
                    }
                }
                string += "${var2[1].take(2)} ${if(var2[1].take(2).toInt() > 1) "minutes" else "minute"}."

                val title = Title.title(
                    Component.text("oopsie woopsie!").color(NamedTextColor.RED),
                    Component.text("You have to wait another $string"),
                    Title.Times.times(Duration.ZERO, Duration.ofSeconds(2), Duration.ZERO),
                )
                event.clicker.showTitle(title)
                return@withContext
            }
            val item = trickOrTreat()
            event.clicker.apply {
                persistentDataContainer[namespacedKey, PersistentDataType.LONG] = now().epochSeconds
                if(item != null) inventory.addItem(item)
                playSound(Sound.sound(Key.key("block.beehive.exit"), Sound.Source.PLAYER, 0.7f, 0.7f))
            }
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun onConsumeTreat(event: PlayerItemConsumeEvent) {
        when(event.item.persistentDataContainer) {
            ItemFactory[HollowsEve2021.GUMMY_FISH].persistentDataContainer, ItemFactory[HollowsEve2021.CANDIED_BERRIES].persistentDataContainer -> event.player.addPotionEffects(foodPotions[0])
            ItemFactory[HollowsEve2021.BOWL_OF_CHOCOLATES].persistentDataContainer -> event.player.addPotionEffects(foodPotions[1])
            else -> return
        }
    }
}