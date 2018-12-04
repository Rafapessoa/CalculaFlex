package com.example.rafael.calculaflex.extensions

fun Double.format(digits: Int) = String.format("%.${digits}f", this)



