package net.starlegacy.feature.starship

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import java.util.*
import net.starlegacy.util.*
import org.bukkit.Material
import org.bukkit.Material.*

val FLYABLE_BLOCKS: EnumSet<Material> = mutableSetOf(
	JUKEBOX, // ship computer
	NOTE_BLOCK, // used as power input/output for machines

	SPONGE, // used for lots of ship subsystems, esp. weapons

	GLASS,
	GLASS_PANE,
	IRON_BARS,

	// all 4used as thrusters
	SEA_LANTERN,
	GLOWSTONE,
	REDSTONE_LAMP,
	MAGMA_BLOCK,

	DIAMOND_BLOCK,
	REDSTONE_BLOCK,
	GOLD_BLOCK,
	LAPIS_BLOCK,
	IRON_BLOCK,
	EMERALD_BLOCK,
	BROWN_MUSHROOM_BLOCK, // custom ores

	// used for landing gears
	PISTON,
	PISTON_HEAD,
	MOVING_PISTON,
	STICKY_PISTON, // used for crate holders

	CHEST,
	ENDER_CHEST,
	TRAPPED_CHEST,
	FURNACE,
	DROPPER,
	HOPPER,
	DISPENSER,

	// misc stuff
	TORCH,
	WALL_TORCH,
	CRAFTING_TABLE,
	END_ROD,
	LEVER,
	FLOWER_POT,
	CAULDRON,
	ANVIL,
	BOOKSHELF,
	LADDER,
	DAYLIGHT_DETECTOR,
	NETHER_PORTAL,

	OBSERVER,
	REPEATER,
	COMPARATOR,
	REDSTONE_WIRE,
	REDSTONE_TORCH,
	REDSTONE_WALL_TORCH,

	LODESTONE,
	BREWING_STAND,
	LECTERN,

	END_PORTAL_FRAME,

	SHROOMLIGHT,
	BELL,
	GRINDSTONE,
	BARREL,
	TINTED_GLASS,
	SCAFFOLDING,
	CHAIN,

	POTTED_AZURE_BLUET,
	POTTED_BAMBOO,
	POTTED_BIRCH_SAPLING,
	POTTED_BLUE_ORCHID,
	POTTED_BROWN_MUSHROOM,
	POTTED_CACTUS,
	POTTED_CORNFLOWER,
	POTTED_CRIMSON_FUNGUS,
	POTTED_CRIMSON_ROOTS,
	POTTED_DANDELION,
	POTTED_DARK_OAK_SAPLING,
	POTTED_DEAD_BUSH,
	POTTED_FERN,
	POTTED_FLOWERING_AZALEA_BUSH,
	POTTED_JUNGLE_SAPLING,
	POTTED_LILY_OF_THE_VALLEY,
	POTTED_OAK_SAPLING,
	POTTED_ORANGE_TULIP,
	POTTED_OXEYE_DAISY,
	POTTED_PINK_TULIP,
	POTTED_POPPY,
	POTTED_RED_MUSHROOM,
	POTTED_RED_TULIP,
	POTTED_SPRUCE_SAPLING,
	POTTED_WARPED_FUNGUS,
	POTTED_WARPED_ROOTS,
	POTTED_WHITE_TULIP,
	POTTED_WITHER_ROSE,
	
	).also {
	it.addAll(CONCRETE_TYPES)
	it.addAll(SLAB_TYPES)
	it.addAll(STAIR_TYPES)
	it.addAll(GLAZED_TERRACOTTA_TYPES)
	it.addAll(STAINED_TERRACOTTA_TYPES)
	it.addAll(WOOL_TYPES)
	it.addAll(CARPET_TYPES)
	it.addAll(STAINED_GLASS_TYPES)
	it.addAll(STAINED_GLASS_PANE_TYPES)
	it.addAll(SHULKER_BOX_TYPES)
	it.addAll(SIGN_TYPES)
	it.addAll(BUTTON_TYPES)
	it.addAll(BANNER_TYPES)
	it.addAll(DOOR_TYPES)
	it.addAll(TRAPDOOR_TYPES)
	it.addAll(PRESSURE_PLATE_TYPES)
	it.addAll(BED_TYPES)
	it.addAll(FENCE_TYPES)
	it.addAll(WALL_TYPES)
	it.addAll(PLANKS_TYPES)
	it.addAll(CANDLE_TYPES)
	it.addAll(CAKE_TYPES)
	
}.filter { it.isBlock }.toCollection(EnumSet.noneOf(Material::class.java))

val DESTROYABLE_BLOCKS = setOf(
	SNOW,
	TALL_GRASS
)

private val FLYABLE_BLOCK_DATA_CACHE = CacheBuilder.newBuilder()
	.build<NMSBlockState, Boolean>(CacheLoader.from { blockData ->
		return@from blockData != null && FLYABLE_BLOCKS.contains(blockData.bukkitMaterial)
	})

fun isFlyable(blockData: NMSBlockState) = FLYABLE_BLOCK_DATA_CACHE[blockData]
