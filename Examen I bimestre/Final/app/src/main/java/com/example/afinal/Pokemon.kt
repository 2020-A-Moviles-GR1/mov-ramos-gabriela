package com.example.afinal

class Pokemon(
    var nombre:String,
    var tipo:String,
    var nivel: String

     ) {
    override fun toString(): String {
        return " ${nombre}, ${tipo} "
    }

}