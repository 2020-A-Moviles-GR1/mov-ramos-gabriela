package com.example.afinal

import java.util.*

class PokemonHttp(
    val createdAt: Long,
    val updatedAt: Long,
    var id: Int,
    var nombre: String,
    var tipo: String,
    var nivel: Int,
    var activo: Boolean,
    var entrenador: Int
) {
    var fechaCreacion: Date
    var fechaActualizacion: Date
    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }
}