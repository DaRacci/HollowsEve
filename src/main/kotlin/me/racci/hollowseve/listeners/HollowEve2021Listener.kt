package me.racci.hollowseve.listeners

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent
import me.racci.hollowseve.enums.HollowsEve2021
import me.racci.hollowseve.itemFactory
import me.racci.raccicore.utils.extensions.KotlinListener
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class HollowEve2021Listener : KotlinListener {

    private val speedModifier = AttributeModifier(UUID.fromString("9f6ffc74-2d64-11ec-8d3d-0242ac130003"), "CANDYCORNARMOUR", 0.25, AttributeModifier.Operation.MULTIPLY_SCALAR_1)
    private val foodPotions = arrayOf(
        arrayListOf(
            PotionEffect(PotionEffectType.HUNGER, 60, 4, false, false, false),
            PotionEffect(PotionEffectType.SATURATION, 60, 9, false, false, false),
            PotionEffect(PotionEffectType.SPEED, 60, 5, false, false, false),
        ),
        arrayListOf(
            PotionEffect(PotionEffectType.HUNGER, 60, 10, false, false, false),
            PotionEffect(PotionEffectType.SATURATION, 60, 9, false, false, false),
            PotionEffect(PotionEffectType.SPEED, 60, 5, false, false, false),
        )
    )

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun onCandyCornArmour(event: PlayerArmorChangeEvent) {
        val oldPDC = event.oldItem?.itemMeta?.persistentDataContainer
        val newPDC = event.newItem?.itemMeta?.persistentDataContainer
        if(newPDC != oldPDC) {
            println("Changed Armour")
            if(oldPDC?.has(itemFactory[HollowsEve2021.CANDY_CORN_ARMOUR, true]!!, PersistentDataType.BYTE) == true) {
                event.player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)!!.removeModifier(speedModifier)
            } else if(newPDC?.has(itemFactory[HollowsEve2021.CANDY_CORN_ARMOUR, true]!!, PersistentDataType.BYTE) == true) {
                for(armour in event.player.inventory.armorContents) {
                    if(armour == null || !armour.persistentDataContainer?.has(itemFactory[HollowsEve2021.CANDY_CORN_ARMOUR, true]!!, PersistentDataType.BYTE) == true) {
                        return
                    }
                }
                event.player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)!!.apply {
                    if(!this.modifiers.contains(speedModifier)) {
                        event.player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)!!.addModifier(speedModifier)
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun onConsumeTreat(event: PlayerItemConsumeEvent) {
        when(event.item.persistentDataContainer) {
            itemFactory[HollowsEve2021.GUMMY_FISH]!!.persistentDataContainer, itemFactory[HollowsEve2021.CANDIED_BERRIES]!!.persistentDataContainer -> event.player.addPotionEffects(foodPotions[0])
            itemFactory[HollowsEve2021.BOWL_OF_CHOCOLATES]!!.persistentDataContainer -> event.player.addPotionEffects(foodPotions[1])
            else -> return
        }
    }
}