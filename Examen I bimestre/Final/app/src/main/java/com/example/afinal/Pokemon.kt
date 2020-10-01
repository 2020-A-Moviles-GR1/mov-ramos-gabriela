package com.example.afinal

class Pokemons(var id: Int,var nombre:String,
               var tipo:String, var nivel: String,
               var activo: String, var entrenador: String,
var latitud: String, var longitud: String,var url:String, var imagen: String) {
    override fun toString(): String {
        return "id: ${id}, nombre:${nombre}, tipo: ${tipo}, nivel:${nivel}, entrenador:${entrenador}"
    }


}