package com.example.afinal

import java.util.*

class EntrenadorHttp (
    var id: Int,
    var createdAt: Long,
    var updatedAt: Long,
    var nombre: String,
    var color: String,
    var nivel: Int,
    var activo: Boolean
   // var pokemons: ArrayList<PokemonHttp>
){

    var fechaCreacion: Date
    var fechaActualizacion: Date
    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }

}