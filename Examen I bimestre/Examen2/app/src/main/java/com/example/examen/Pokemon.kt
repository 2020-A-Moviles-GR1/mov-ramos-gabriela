package com.example.examen

class Pokemon(
    var id_Entrenador : Int,
    var id_pokemon : Int,
    var nombre: String,
    var tipo: String,
    var nivel: Int,
    var activo: Boolean
) {
    override fun toString(): String {
        return "id Entrenador: ${this.id_Entrenador} | ${this.nombre} ${this.tipo} ${this.nivel} ${this.activo}"
    }


    companion object {

        var pokemon_counter = 5
        var companion_pokemon = ArrayList<Pokemon>()

        fun generateNewId(): Int {
            return pokemon_counter + 1
        }

    }
}