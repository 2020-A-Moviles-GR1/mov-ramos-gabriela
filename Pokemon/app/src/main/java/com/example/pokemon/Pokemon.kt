package com.example.pokemon

class Pokemon(
    var nombre:String,
    var nivel: String
){
    override fun toString(): String {
        return "${this.nombre} ${this.nivel} "
    }
}