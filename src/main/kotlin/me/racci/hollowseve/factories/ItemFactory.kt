package me.racci.hollowseve.factories

import com.willfp.ecoenchants.enchantments.EcoEnchants
import me.racci.hollowseve.enums.HollowsEve2021
import me.racci.hollowseve.enums.HollowsEve2021.*
import me.racci.raccicore.utils.items.builders.ItemBuilder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*
import java.util.function.Function

class ItemFactory {

    operator fun get(enum: HollowsEve2021) = items[enum]

    operator fun get(enum: HollowsEve2021, namespacedKey: Boolean) = namespaces[enum]

    val items = EnumMap<HollowsEve2021, ItemStack>(HollowsEve2021::class.java)

    val namespaces = EnumMap<HollowsEve2021, NamespacedKey>(HollowsEve2021::class.java).apply {
        HollowsEve2021.values().forEach {this[it] = NamespacedKey("sylph", "hollows_eve_2021_${it.name.lowercase()}")}
    }

    init {

        fun gradient(name: String, bold: Boolean = true) =
            when(bold) {
                true -> MiniMessage.get().parse("<gradient:#6fe461:#3f473f><bold>$name</gradient>")
                false -> MiniMessage.get().parse("<gradient:#6fe461:#3f473f>$name</gradient>")
            }

        val resolver: Function<String, ComponentLike> = Function<String, ComponentLike> { name ->
            when(name.lowercase()) {
                "hollowseve" -> MiniMessage.get().parse("<gradient:#6fe461:#3f473f>Hollow's Eve</gradient>")
                "chuck" -> MiniMessage.get().parse("<white><bold>- <gold>Chuck")
                "gigi" -> MiniMessage.get().parse("<white><bold>- <gold>Gigi")
                "unknown" -> MiniMessage.get().parse("<white><bold>- <grey>Unknown")
                else -> null
            }
        }


        var universalLore = listOf(
            Component.empty(),
            MiniMessage.get().parse("<yellow>Used to make Candy Corn Armour").decoration(TextDecoration.ITALIC, false),
            Component.empty(),
            MiniMessage.builder().placeholderResolver(resolver).build().parse("<yellow>Part of the <hollowseve> event.").decoration(TextDecoration.ITALIC, false),
        )

        items[CANDY_CORN] = ItemBuilder.from(Material.GOLD_NUGGET)
            .name(gradient("Candy Corn"))
            .lore(listOf(
                MiniMessage.get().parse("<yellow>\"People swear that it's edible").decoration(TextDecoration.ITALIC, false),
                MiniMessage.get().parse("<yellow>but sometimes it's hard as a rock.").decoration(TextDecoration.ITALIC, false),
                MiniMessage.builder().placeholderResolver(resolver).build().parse("<yellow>...wonder if it has another use...\" <Chuck>").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .pdc{it.set(namespaces[CANDY_CORN]!!, PersistentDataType.BYTE, 1.toByte())}
            .build()

        items[CANDY_CORN_INGOT] = ItemBuilder.from(Material.GOLD_INGOT)
            .name(gradient("Candy Corn Ingot"))
            .lore(listOf(
                MiniMessage.get().parse("<yellow>\"I don't think I can buy that off you.").decoration(TextDecoration.ITALIC, false),
                MiniMessage.builder().placeholderResolver(resolver).build().parse("<yellow>Maybe you could make something?\" <Gigi>").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .pdc{it.set(namespaces[CANDY_CORN_INGOT]!!, PersistentDataType.BYTE, 1.toByte())}
            .build()

        universalLore = listOf(
            Component.empty(),
            MiniMessage.get().parse(" <white><bold> » <yellow>Wear the full set for a massive speed boost").decoration(TextDecoration.ITALIC, false),
            Component.empty(),
            MiniMessage.builder().placeholderResolver(resolver).build().parse("<yellow>Part of the <hollowseve> event.").decoration(TextDecoration.ITALIC, false),
            )

        items[CANDY_CORN_HELMET] = ItemBuilder.from(Material.GOLDEN_HELMET)
            .name(gradient("Candy Corn Helmet"))
            .enchant(Enchantment.DURABILITY, 4)
            .lore(listOf(
                MiniMessage.get().parse("<yellow>A Helmet made from candy corn").decoration(TextDecoration.ITALIC, false),
                MiniMessage.get().parse("<yellow>You can feel the uncontrolled sugar.").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .pdc {it.set(namespaces[CANDY_CORN_HELMET]!!, PersistentDataType.BYTE, 1.toByte())}
            .build()

        items[CANDY_CORN_CHESTPLATE] = ItemBuilder.from(Material.GOLDEN_CHESTPLATE)
            .name(gradient("Candy Corn Chestplate"))
            .enchant(Enchantment.DURABILITY, 4)
            .lore(listOf(
                MiniMessage.get().parse("<yellow>A Chestplate made from candy corn").decoration(TextDecoration.ITALIC, false),
                MiniMessage.get().parse("<yellow>You can feel the uncontrolled sugar.").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .pdc {it.set(namespaces[CANDY_CORN_CHESTPLATE]!!, PersistentDataType.BYTE, 1.toByte())}
            .build()

        items[CANDY_CORN_PANTS] = ItemBuilder.from(Material.GOLDEN_LEGGINGS)
            .name(gradient("Candy Corn Pants"))
            .enchant(Enchantment.DURABILITY, 4)
            .lore(listOf(
                MiniMessage.get().parse("<yellow>Pants made from candy corn").decoration(TextDecoration.ITALIC, false),
                MiniMessage.get().parse("<yellow>You can feel the uncontrolled sugar.").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .pdc {it.set(namespaces[CANDY_CORN_PANTS]!!, PersistentDataType.BYTE, 1.toByte())}
            .build()

        items[CANDY_CORN_BOOTS] = ItemBuilder.from(Material.GOLDEN_BOOTS)
            .name(gradient("Candy Corn Boots"))
            .enchant(Enchantment.DURABILITY, 4)
            .lore(listOf(
                MiniMessage.get().parse("<yellow>Boots made from candy corn").decoration(TextDecoration.ITALIC, false),
                MiniMessage.get().parse("<yellow>You can feel the uncontrolled sugar.").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .pdc {it.set(namespaces[CANDY_CORN_BOOTS]!!, PersistentDataType.BYTE, 1.toByte())}
            .build()

        universalLore = listOf(
            Component.empty(),
            MiniMessage.builder().placeholderResolver(resolver).build().parse("<yellow>Part of the <hollowseve> event.").decoration(TextDecoration.ITALIC, false),
        )

        items[ONCE_PREY_BOOTS] = ItemBuilder.from(Material.NETHERITE_BOOTS)
            .name(gradient("Once-Prey Boots"))
            .enchant(Enchantment.PROTECTION_FALL, 7)
            .enchant(Enchantment.DURABILITY, 5)
            .enchant(Enchantment.SOUL_SPEED, 4)
            .enchant(Enchantment.MENDING)
            .lore(listOf(
                MiniMessage.get().parse("<yellow>\"The undead think we have no choice").decoration(TextDecoration.ITALIC, false),
                MiniMessage.get().parse("<yellow>but to fear them. Let's show them just").decoration(TextDecoration.ITALIC, false),
                MiniMessage.builder().placeholderResolver(resolver).build().parse("how wrong they are.\" <Chuck>").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .build()

        items[UMBERWOOD_SHARDTHROWER] = ItemBuilder.from(Material.CROSSBOW)
            .name(gradient("Umberwood Shardthrower"))
            .enchant(EcoEnchants.BUCKSHOT, 3)
            .enchant(Enchantment.ARROW_KNOCKBACK, 3)
            .enchant(Enchantment.PIERCING, 3)
            .enchant(Enchantment.DURABILITY, 5)
            .enchant(EcoEnchants.REVENANT, 6)
            .lore(listOf(
                MiniMessage.get().parse("<yellow>\"Ah, one of my old crossbows.").decoration(TextDecoration.ITALIC, false),
                MiniMessage.get().parse("<yellow>Turns arrows into shards of pure silver.").decoration(TextDecoration.ITALIC, false),
                MiniMessage.builder().placeholderResolver(resolver).build().parse("Let's see you put it to use\" <Chuck>").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .build()

        items[GNELL] = ItemBuilder.from(Material.NETHERITE_HOE)
            .name(gradient("Gnell"))
            .enchant(EcoEnchants.CLEAVE, 3)
            .enchant(EcoEnchants.LEECHING, 7)
            .enchant(EcoEnchants.NECROTIC, 7)
            .enchant(EcoEnchants.CRITICALS, 3)
            .enchant(Enchantment.DAMAGE_ALL, 10)
            .enchant(Enchantment.DURABILITY, 5)
            .enchant(Enchantment.MENDING)
            .lore(listOf(
                MiniMessage.get().parse("<yellow>\"When the soul sands dry, and the innocent sing.").decoration(TextDecoration.ITALIC, false),
                MiniMessage.builder().placeholderResolver(resolver).build().parse("<yellow>The undead shall hear me. And hear my ring\" <Unknown>").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .build()

        items[HEADSMANS_HATCHET] = ItemBuilder.from(Material.NETHERITE_AXE)
            .name(gradient("Headsman's Hatchet"))
            .enchant(EcoEnchants.BEHEADING, 5)
            .enchant(EcoEnchants.LEECHING, 6)
            .enchant(EcoEnchants.BLEED, 7)
            .enchant(Enchantment.DAMAGE_ALL, 8)
            .enchant(Enchantment.DURABILITY, 5)
            .enchant(Enchantment.MENDING)
            .lore(listOf(
                MiniMessage.get().parse("<yellow>\"A grim tradition. In parts of the mountains,").decoration(TextDecoration.ITALIC, false),
                MiniMessage.get().parse("<yellow>it was thought being buried headless could").decoration(TextDecoration.ITALIC, false),
                MiniMessage.get().parse("<yellow>save you. I hope this axe finds better purpose").decoration(TextDecoration.ITALIC, false),
                MiniMessage.builder().placeholderResolver(resolver).build().parse("<yellow>now.\" <Chuck>").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .build()

        items[GRAVE_DIGGER] = ItemBuilder.from(Material.NETHERITE_PICKAXE)
            .name(gradient("Grave Digger"))
            .enchant(EcoEnchants.INSTANTANEOUS, 8)
            .enchant(EcoEnchants.TELEKINESIS)
            .enchant(Enchantment.DIG_SPEED, 7)
            .enchant(Enchantment.DURABILITY, 5)
            .enchant(Enchantment.MENDING)
            .lore(listOf(
                MiniMessage.get().parse("<yellow>\"Gets cold enough, the hardest soil can").decoration(TextDecoration.ITALIC, false),
                MiniMessage.builder().placeholderResolver(resolver).build().parse("<yellow>feel like the average slate.\" - Chuck").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .build()

        items[CANDY_PAIL] = ItemBuilder.from(Material.BUCKET)
            .name(gradient("Candy Pail"))
            .lore(listOf(
                MiniMessage.get().parse("<yellow>\"It's an old tradition to go around in costume,").decoration(TextDecoration.ITALIC, false),
                MiniMessage.builder().placeholderResolver(resolver).build().parse("<yellow>asking for candy. Go on, give it a try\" <Chuck>").decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                MiniMessage.get().parse("<yellow>Right-click on <gold>Chuck<yellow>, <gold>Gigi<yellow>, or <gold>Bug to get candy!").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .build()

        items[GUMMY_FISH] = ItemBuilder.from(Material.TROPICAL_FISH).build()

        items[CANDIED_BERRIES] = ItemBuilder.from(Material.SWEET_BERRIES).build()

        items[BOWL_OF_CHOCOLATES] = ItemBuilder.from(Material.SUSPICIOUS_STEW).build()

        items[HOLY_SABER] = ItemBuilder.from(Material.NETHERITE_SWORD)
            .name(gradient("Holy Saber"))
            .enchant(Enchantment.DAMAGE_UNDEAD, 7)
            .enchant(EcoEnchants.MISSILE, 4)
            .enchant(Enchantment.FIRE_ASPECT, 5)
            .enchant(Enchantment.DURABILITY, 5)
            .enchant(Enchantment.MENDING, 1)
            .lore(listOf(
                MiniMessage.get().parse("<yellow>The sword of a once great hero.").decoration(TextDecoration.ITALIC, false),
                MiniMessage.get().parse("<yellow>Even still, it wards off the dark").decoration(TextDecoration.ITALIC, false),
                MiniMessage.builder().placeholderResolver(resolver).build().parse("<yellow>that has come to taint it.").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .build()

        items[HOLLOWS_EVE_HAT] = ItemBuilder.from(Material.JACK_O_LANTERN)
            .name(gradient("Hollow's Eve Hat 2021"))
            .lore(listOf(
                MiniMessage.get().parse("<yellow>\"What do you think?").decoration(TextDecoration.ITALIC, false),
                MiniMessage.get().parse("<yellow>I think it’s my scariest work yet!").decoration(TextDecoration.ITALIC, false),
                MiniMessage.builder().placeholderResolver(resolver).build().parse("<yellow>Just don’t go down out there.\" <Gigi>").decoration(TextDecoration.ITALIC, false),
            ).plus(universalLore))
            .build()

        items[STRIDER_MAN_MASK] = ItemBuilder.skull().build()

        items[CLOWN_MASK] = ItemBuilder.skull().build()

        items[SCARECROW_MASK] = ItemBuilder.skull().build()

        items[UNDEAD_MASK] = ItemBuilder.skull().build()


        items.entries.forEach {
            it.value.persistentDataContainer.set(namespaces[it.key]!!, PersistentDataType.BYTE, 1.toByte())
        }


    }

}