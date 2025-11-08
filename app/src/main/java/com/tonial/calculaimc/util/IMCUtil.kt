package com.tonial.calculaimc.util

fun calcularIMC(pesoVal: Double, alturaVal: Double, locale: String): Double {

    if(locale.equals("pt"))
    {
        return pesoVal / (alturaVal * alturaVal)
    }
    else
    {
        return 703 * (pesoVal / (alturaVal * alturaVal))
    }
}