package com.example.pokemon

class ServicioBDD{
    companion object{  //solo existe uno por clase
        var nombre:String =""
        var apellido: String=""
        var color:String=""
        var nivel:Int=0
        var activo:Boolean?=true
        var d_caminada:Double=0.0
        var arreglo_pokemones: ArrayList<Int> = arrayListOf()

        fun anadirEntrenador(){
            this.nombre = "nuevo"
            this.apellido ="nuevoApe"

        }
    }
    }