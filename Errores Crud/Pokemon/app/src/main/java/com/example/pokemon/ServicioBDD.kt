package com.example.pokemon

import android.os.Bundle

class ServicioBDD{



    companion object {  //solo existe uno por clase
        var nombre: String = ""
        var color: String = ""
        var nivel: Int = 0
        var activo: Boolean? = true
        var distancia: Double = 0.0
        var arreglo_entrenador = ArrayList<Entrenador>()
    //    var arreglo_pokemones = ArrayList<Pokemon>()


        fun crearaux() {
            val entrenador = Entrenador(
                nombre = "Anderson",
                color = "Muñoz",
                nivel = 1,
                activo = true,
                distancia = 0.0


            )

            arreglo_entrenador.add(entrenador)
          //  arreglo_entrenador.add(EntrenadorPrueba)
            var x = arreglo_entrenador;

            val pokemon = Pokemon(

                nombre = "Pikachu",
                tipo = "electrico",
                nivel = 1,
                activo = true

            )
        //    arreglo_pokemones.add(pokemon)


        }
        fun anadirEntrenador(){
            this.nombre = "nuevo"
            this.color ="nuevoApe"

        }



        override fun toString(): String {
            return "${this.nombre} ${this.color} ${this.nivel} ${this.activo} ${this.distancia}"
        }

    }
    }