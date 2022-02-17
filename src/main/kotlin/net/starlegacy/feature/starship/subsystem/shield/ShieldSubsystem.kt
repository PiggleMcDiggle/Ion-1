package net.starlegacy.feature.starship.subsystem.shield

import kotlin.math.pow
import kotlin.math.roundToInt
import net.starlegacy.feature.multiblock.particleshield.ShieldMultiblock
import net.starlegacy.feature.starship.active.ActiveStarship
import net.starlegacy.feature.starship.subsystem.AbstractMultiblockSubsystem
import net.starlegacy.util.d
import net.starlegacy.util.stripColor
import org.bukkit.block.Block
import org.bukkit.block.Sign

abstract class ShieldSubsystem(
	starship: ActiveStarship,
	sign: Sign,
	multiblock: ShieldMultiblock
) : AbstractMultiblockSubsystem<ShieldMultiblock>(starship, sign, multiblock) {
	val name: String = sign.getLine(2).stripColor()
	val maxPower: Int = (starship.blockCount.coerceAtLeast(500).d().pow(3.0 / 5.0) * 10000.0).roundToInt()

	var power: Int = maxPower
		set(value) {
			field = value.coerceIn(0, maxPower)
		}

	var isReinforcementEnabled = multiblock.isReinforced

	fun isReinforcementActive(): Boolean {
		return isReinforcementEnabled && powerRatio > 0.8
	}

	val powerRatio: Double get() = power.toDouble() / maxPower.toDouble()

	fun getPowerUsage(power: Double): Int {
		return (power * 3000.0).toInt()
	}

	abstract fun containsBlock(block: Block): Boolean
}
