package com.example.afinal

 class Entrenador (
    var id: Int,
    var createdAt: Long,
    var updatedAt: Long,
    var nombre:String,
    var color:String,
    var nivel:String,
    var activo:String,

    var pokemones:String) {

     override fun toString(): String {
         return "Nombre: ${nombre}, color:${color}"
     }
 }