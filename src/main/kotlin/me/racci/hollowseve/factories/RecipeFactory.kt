package me.racci.hollowseve.factories

import kotlinx.coroutines.coroutineScope
import me.racci.hollowseve.capitalizeWords
import me.racci.hollowseve.enums.HollowsEve2021
import me.racci.raccicore.builders.ItemBuilder
import me.racci.raccicore.utils.catch
import me.racci.raccicore.utils.extensions.addRecipe
import me.racci.raccicore.utils.strings.colour
import me.racci.raccicore.utils.strings.colouredTextOf
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.block.CreatureSpawner
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.meta.BlockStateMeta
import org.bukkit.inventory.meta.ItemMeta

object RecipeFactory {

    private val RECIPE_ARRAY = ArrayList<NamespacedKey>()

    suspend fun init() {

        hollowsEveRecipes()
        //sylphRecipes()

    }

    fun shutdown() {

        RECIPE_ARRAY.forEach(Bukkit::removeRecipe)

    }

    data class Spawner(
        val entityType: EntityType,
        private val materials: Array<Material>,
    ) {

        data class SpecialItem(
            val fat: ItemStack,
            val thicc: ItemStack,
        )

        val items = ArrayList<SpecialItem>()

        lateinit var spawner : ItemStack

        init {
            catch<Exception> {
            materials.forEach {
                items.add(
                    SpecialItem(
                        ItemBuilder.from(it) {
                            name = colouredTextOf("&5Fat ${it.name.lowercase().capitalizeWords()}")
                            glow
                            model = 55
                        },
                        ItemBuilder.from(it) {
                            name = colouredTextOf("&5Thicc ${it.name.lowercase().capitalizeWords()}")
                            glow
                            model = 55
                        },
                    )
                )
            }

            items.forEach {
                val material = it.fat.type

                var key = NamespacedKey("sylph", "crafting_recipe_${material.name.lowercase()}_fat")
                val fat = ShapedRecipe(key, it.fat)
                fat.shape("iii", "idi", "iii")
                fat.setIngredient('i', material)
                fat.setIngredient('d', Material.DIAMOND)
                addRecipe(fat) ; RECIPE_ARRAY.add(key)

                key = NamespacedKey("sylph", "crafting_recipe_${material.name.lowercase()}_thicc")
                val thicc = ShapedRecipe(key, it.thicc)
                thicc.shape("iii", "idi", "iii")
                thicc.setIngredient('i', it.fat)
                thicc.setIngredient('d', Material.DIAMOND_BLOCK)
                addRecipe(thicc) ; RECIPE_ARRAY.add(key)

            }

            spawner = ItemStack(Material.SPAWNER)
            val itemMeta: ItemMeta = spawner.itemMeta
            try {
                val blockStateMeta = itemMeta as BlockStateMeta
                val creatureSpawner = blockStateMeta.blockState as CreatureSpawner
                creatureSpawner.spawnedType = entityType
                blockStateMeta.blockState = creatureSpawner
                spawner.itemMeta = blockStateMeta
            } catch (ignored: Throwable) {}
            spawner.setDisplayName(colour("&e${entityType.name.lowercase().capitalizeWords()} spawner"))
            val key = NamespacedKey("sylph", "crafting_recipe_${entityType.name.lowercase()}_spawner")
            val spawnerRecipe = ShapedRecipe(key, spawner)
            if(items.size == 1) {
                spawnerRecipe.shape("iii", "ini", "iii")
            } else if(items.size >= 3) {
                spawnerRecipe.shape("ioi", "ono", "ioi")
                spawnerRecipe.setIngredient('o', items[1].thicc)
            }
            spawnerRecipe.setIngredient('i', items[0].thicc)
            spawnerRecipe.setIngredient('n', Material.NETHERITE_INGOT)
            addRecipe(spawnerRecipe) ; RECIPE_ARRAY.add(key)
        }}
    }


    private fun sylphRecipes() {
        arrayOf(
            Spawner(EntityType.SKELETON,        arrayOf(Material.BONE)),
//            Spawner(EntityType.SHEEP,           arrayOf(Material.MUTTON, Material.WHITE_WOOL)),
//            Spawner(EntityType.COW,             arrayOf(Material.BEEF, Material.LEATHER)),
//            Spawner(EntityType.CHICKEN,         arrayOf(Material.CHICKEN, Material.FEATHER)),
            Spawner(EntityType.ENDERMAN,        arrayOf(Material.ENDER_PEARL)),
            Spawner(EntityType.CREEPER,         arrayOf(Material.GUNPOWDER)),
//            Spawner(EntityType.MUSHROOM_COW,    arrayOf(Material.BROWN_MUSHROOM, Material.RED_MUSHROOM)),
        )
    }

    private suspend fun hollowsEveRecipes() = coroutineScope {

        val candyCornIngotRecipe = ShapedRecipe(ItemFactory[HollowsEve2021.CANDY_CORN_INGOT, true], ItemFactory[HollowsEve2021.CANDY_CORN_INGOT])
        candyCornIngotRecipe.shape("ccc", "ccc", "ccc")
        candyCornIngotRecipe.setIngredient('c', ItemFactory[HollowsEve2021.CANDY_CORN])

        val ccH = ShapedRecipe(ItemFactory[HollowsEve2021.CANDY_CORN_HELMET, true], ItemFactory[HollowsEve2021.CANDY_CORN_HELMET])
        ccH.shape("ccc", "cac")
        ccH.setIngredient('c', ItemFactory[HollowsEve2021.CANDY_CORN_INGOT])
        ccH.setIngredient('a', Material.AIR)

        val ccC = ShapedRecipe(ItemFactory[HollowsEve2021.CANDY_CORN_CHESTPLATE, true], ItemFactory[HollowsEve2021.CANDY_CORN_CHESTPLATE])
        ccC.shape("cac", "ccc", "ccc")
        ccC.setIngredient('c', ItemFactory[HollowsEve2021.CANDY_CORN_INGOT])
        ccC.setIngredient('a', Material.AIR)

        val ccP = ShapedRecipe(ItemFactory[HollowsEve2021.CANDY_CORN_PANTS, true], ItemFactory[HollowsEve2021.CANDY_CORN_PANTS])
        ccP.shape("ccc", "cac", "cac")
        ccP.setIngredient('c', ItemFactory[HollowsEve2021.CANDY_CORN_INGOT])
        ccP.setIngredient('a', Material.AIR)

        val ccB = ShapedRecipe(ItemFactory[HollowsEve2021.CANDY_CORN_BOOTS, true], ItemFactory[HollowsEve2021.CANDY_CORN_BOOTS])
        ccB.shape("cac", "cac")
        ccB.setIngredient('c', ItemFactory[HollowsEve2021.CANDY_CORN_INGOT])
        ccB.setIngredient('a', Material.AIR)

        val heH = ShapedRecipe(ItemFactory[HollowsEve2021.HOLLOWS_EVE_HAT, true], ItemFactory[HollowsEve2021.HOLLOWS_EVE_HAT])
        heH.shape("nnn", "nhc", "ccc")
        heH.setIngredient('c', ItemFactory[HollowsEve2021.CANDY_CORN_INGOT])
        heH.setIngredient('n', ItemStack(Material.NETHERITE_SCRAP))
        heH.setIngredient('h', ItemFactory[HollowsEve2021.UNDEAD_MASK])

        val ciC = ShapedRecipe(ItemFactory[HollowsEve2021.CANDY_CORN, true], ItemFactory[HollowsEve2021.CANDY_CORN])
        ciC.shape("c")
        ciC.setIngredient('c', ItemFactory[HollowsEve2021.CANDY_CORN_INGOT])

        val recipes = arrayOf(candyCornIngotRecipe, ccH, ccC, ccP, ccB, heH, ciC)

        recipes.onEach(::addRecipe).map(ShapedRecipe::getKey).forEach(RECIPE_ARRAY::add)
    }
}