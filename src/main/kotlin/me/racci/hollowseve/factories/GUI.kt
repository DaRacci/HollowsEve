package me.racci.hollowseve.factories

import com.destroystokyo.paper.MaterialTags
import com.github.stefvanschie.inventoryframework.gui.GuiItem
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui
import com.github.stefvanschie.inventoryframework.pane.Pane
import com.github.stefvanschie.inventoryframework.pane.PatternPane
import com.github.stefvanschie.inventoryframework.pane.util.Pattern
import me.racci.raccicore.builders.ItemBuilder
import me.racci.raccicore.utils.strings.colouredTextOf
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.entity.Player

class GUI {

    // GUI Sounds
    private val backExitSound = Sound.sound(Key.key("block.beehive.exit"), Sound.Source.MASTER, 1f, 1f)

    // GUI Buttons
    private val exitGUIButton = GuiItem(ItemBuilder.from(Material.BARRIER) {
        name = (colouredTextOf("&cClose this menu"))
        lore = colouredTextOf("&f&l» &bClick &f&l« &eclose the menu.")
    }
    ) {
        it.whoClicked.closeInventory()
        it.whoClicked.playSound(backExitSound, Sound.Emitter.self())
    }

    private val borderFiller = HashMap<Material, GuiItem>().apply {
        for(mat in MaterialTags.GLASS_PANES.values) {
            this[mat] = GuiItem(ItemBuilder.from(mat) {
                name = Component.empty()
            }).apply {setAction {it.isCancelled = true}}
        }
    }

    private val var1 = Pattern(
        "000000000",
        "012345670",
        "000080000"
    )
    private val var2 = PatternPane(0, 0, 9, 3, Pane.Priority.HIGH, var1).apply {
        bindItem('0', borderFiller[Material.GRAY_STAINED_GLASS_PANE]!!)
        bindItem('1', GuiItem(ItemBuilder.from(Material.ROTTEN_FLESH) {
            name = (Component.text("What is Hollow's Eve").color(NamedTextColor.GOLD)
                .decoration(TextDecoration.ITALIC, false))
            lore {
                listOf(
                    Component.empty(),
                    colouredTextOf("&f&l» &bClick &f&l« &eto ask this question.").decoration(TextDecoration.ITALIC,
                        false)
                )
            }
        }
        ) {
            it.whoClicked.closeInventory()
            it.whoClicked.playSound(backExitSound, Sound.Emitter.self())
            (it.whoClicked as Player).sendMessage(colouredTextOf("&a&lChuck &f&l» &aLong ago, the evil Hollow God attempted to seize control over reincarnation. In their only ever act of cooperation, Ren and Drakon together struck him down. But on his death, the Hollow God created the first Undead, and cursed the world with his zombie hordes. Lady Nika, the new goddess of death, manages to mostly hold back the tide. But every year, on the eve of his death, the Hollow God’s hordes muster once more. Thus the name; Hollow’s Eve."))
        })
        bindItem('2', GuiItem(ItemBuilder.from(Material.JACK_O_LANTERN) {
            name = Component.text("Why are people celebrating such a dark holiday?").color(NamedTextColor.GOLD)
                .decoration(TextDecoration.ITALIC, false)
            lore {
                listOf(
                    Component.empty(),
                    colouredTextOf("&f&l» &bClick &f&l« &eto ask this question.").decoration(TextDecoration.ITALIC,
                        false)
                )
            }
        }
        ) {
            it.whoClicked.closeInventory()
            it.whoClicked.playSound(backExitSound, Sound.Emitter.self())
            (it.whoClicked as Player).sendMessage(colouredTextOf("&a&lChuck &f&l» &aWeapons, magic, and even people are all stronger today. Combined with Nika’s influence, the Hordes are much easier to hold back. In fortified towns such as this? All of the old tactics for surviving the undead have become traditions for the children. Sugary food, wearing costumes. It’s how people celebrate overcoming years of struggle. When they can, anyways."))
        })
        bindItem('3', GuiItem(ItemBuilder.from(Material.NETHERITE_AXE) {
            name = Component.text("Is there any new gear to earn?").color(NamedTextColor.GOLD)
                .decoration(TextDecoration.ITALIC, false)
            lore {
                listOf(
                    Component.empty(),
                    colouredTextOf("&f&l» &bClick &f&l« &eto ask this question.").decoration(TextDecoration.ITALIC,
                        false)
                )
            }
        }
        ) {
            it.whoClicked.closeInventory()
            it.whoClicked.playSound(backExitSound, Sound.Emitter.self())
            (it.whoClicked as Player).sendMessage(colouredTextOf("&a&lChuck &f&l» &aThe undead still threaten less protected towns. So Bug has put together a crate for anyone with Hollow Keys who’d like some new weapons to take on the Horde Nights with. Hoggers over there also has his shop set up, and if you combine one of his Undead Masks with four Netherite Scrap and four Candy Corn Ingots, you can get a new hat. Some people been using the Candy Corn to make armor but...just stick to Hollow Keys for now."))
        })
        bindItem('4', GuiItem(ItemBuilder.from(Material.TRIPWIRE_HOOK) {
            model = 1
            name = Component.text("How do you get Hollows Keys").color(NamedTextColor.GOLD)
                .decoration(TextDecoration.ITALIC, false)
            lore {
                listOf(
                    Component.empty(),
                    colouredTextOf("&f&l» &bClick &f&l« &eto ask this question.").decoration(TextDecoration.ITALIC,
                        false)
                )
            }
        }
        ) {
            it.whoClicked.closeInventory()
            it.whoClicked.playSound(backExitSound, Sound.Emitter.self())
            (it.whoClicked as Player).sendMessage(colouredTextOf("&a&lChuck &f&l» &aNormally this part of the world gets Blood Nights, where extra powerful monsters come out. But this time of year, they’re replaced with Horde Nights, where the Hollow God’s forces come out. Any of the Hollow undead except Thralls will give a chance at keys. And if you can slay a Harbinger, it’s a guaranteed chance. There’s also Trick or Treating, but that’s way less fun."))
        })
        bindItem('5', GuiItem(ItemBuilder.from(Material.BUCKET) {
            name = Component.text("What's Trick or Treating").color(NamedTextColor.GOLD)
                .decoration(TextDecoration.ITALIC, false)
            lore {
                listOf(
                    Component.empty(),
                    colouredTextOf("&f&l» &bClick &f&l« &eto ask this question.").decoration(TextDecoration.ITALIC,
                        false)
                )
            }
        }
        ) {
            it.whoClicked.closeInventory()
            it.whoClicked.playSound(backExitSound, Sound.Emitter.self())
            (it.whoClicked as Player).sendMessage(colouredTextOf("&a&lChuck &f&l» &aBack in the old days, people had to wear undead disguises to scavenge for food without detection. Nowadays people go around in costumes collecting candy from their neighbors. Weird tradition. But if you right click me, Gigi, or Bug with a Candy Pail, we’ve all got something for you. Including a chance at keys. Just ask Hoggers for a pail to get started."))
        })
        bindItem('6', GuiItem(ItemBuilder.from(Material.CROSSBOW) {
            name = Component.text("Who are you?").color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false)
            lore {
                listOf(
                    Component.empty(),
                    colouredTextOf("&f&l» &bClick &f&l« &eto ask this question.").decoration(TextDecoration.ITALIC,
                        false)
                )
            }
        }
        ) {
            it.whoClicked.closeInventory()
            it.whoClicked.playSound(backExitSound, Sound.Emitter.self())
            (it.whoClicked as Player).sendMessage(colouredTextOf("&a&lChuck &f&l» &aChuck. Chuck Goldfield. I’m a Slayer from the island of Jalis. Our job is to hunt undead, and you can imagine we get good business this time of year. Jalis doesn’t need the extra protection this time of year, so I usually come here to help people out. Plenty of excess soul energy in the air, and I get to help clean up for my friend Luck. You eh...tell her I said hello."))
        })
        bindItem('7', GuiItem(ItemBuilder.from(Material.PLAYER_HEAD) {
            name = Component.text("Wait- you eat souls? Does that make you-").color(NamedTextColor.GOLD)
                .decoration(TextDecoration.ITALIC, false)
            lore {
                listOf(
                    Component.empty(),
                    colouredTextOf("&f&l» &bClick &f&l« &eto ask this question.").decoration(TextDecoration.ITALIC,
                        false)
                )
            }
        }
        ) {
            it.whoClicked.closeInventory()
            it.whoClicked.playSound(backExitSound, Sound.Emitter.self())
            (it.whoClicked as Player).sendMessage(colouredTextOf("&a&lChuck &f&l» &aNo, I’m not here to kill you. And no, I don’t eat souls. I’m a Vampire. We’re special undead made by Nika to help ward off the other stuff. Everyone’s soul leaks off excess energy and that’s what we eat- not the soul itself. Good way to die, actually consuming a whole soul. And- we don’t drink blood either, alright? It was one weirdo, one time, now everyone thinks we do it."))
        })
        bindItem('8', exitGUIButton)
    }

    lateinit var chuckGUI : ChestGui

    init {
        reload()
    }

    private fun reload() {
        chuckGUI = ChestGui(3, "Chuck").apply {
            addPane(var2)
            setOnGlobalClick { it.isCancelled = true }
        }
    }

}