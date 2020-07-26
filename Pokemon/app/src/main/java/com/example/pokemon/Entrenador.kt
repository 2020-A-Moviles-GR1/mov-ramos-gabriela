package com.example.pokemon

class Entrenador(
    var nombre:String,

    var color:String,
    var nivel: Int,
   var activo:Boolean?,
  var d_caminada:Double
   //var arreglo_pokemones: ArrayList<Int>

) {
    override fun toString(): String {
        return "${this.nombre} ${this.color} | ${this.activo}  ${this.nivel}  ${this.d_caminada}"
    }



    }