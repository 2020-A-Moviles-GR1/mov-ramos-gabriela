package com.example.afinal

class Entrenador(
    var nombre:String,
    var color:String,
    var nivel:Int,
    var activo: Boolean,
    var listaPokemones:String) {
    override fun toString(): String {
        return "Nombre Entrenador: ${nombre} ,Color: ${color}  "
    }

}