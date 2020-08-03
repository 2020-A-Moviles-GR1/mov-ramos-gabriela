package com.example.pokemon

import java.util.*

class Pokemon(
    var nombre: String,
    var tipo: String,
    var nivel:Int,
    var activo: Boolean

) {
    override fun toString(): String {
        return "${this.nombre} ${this.tipo}"
    }
}