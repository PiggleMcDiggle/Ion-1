package net.horizonsend.ion.server

import kotlin.math.round

// Use .asInt() instead of .toInt(), unless you actually need to round down.
// SL's ubiquitous usage of .toInt() went on for tool long.
val Double.asInt get() = round(this).toInt()
val Float.asInt get() = round(this).toInt()
