package com.example.examen

class Entrenador(
    var id : Int,
    var nombre: String,
    var color: String,
    var nivel: Int,
    var activo: Boolean,
    var distancia: Double
) {
    override fun toString(): String {
        return "${this.nombre} ${this.color} ${this.nivel} ${this.activo} ${this.distancia}"
    }


    companion object {

        var entrenador_created_counter = 5
        var companion_entrenador = ArrayList<Entrenador>()
        var arra_pokemons = ArrayList<Pokemon>()




    }
}