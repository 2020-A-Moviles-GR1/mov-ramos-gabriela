package com.example.afinal

import android.util.Log

class BddService {
    companion object{

        var listaPokemones= arrayListOf<Pokemon>(
            Pokemon("a","A", "23"),
            Pokemon("b","B","12"),
            Pokemon("c","C","10")
        )
        var listaEntrenadores= arrayListOf<Entrenador>(Entrenador("Andres","Rojo",25,true,
            "a,b,c"
        ))
        fun buscar_pokemon(chord:String): Pokemon? {


            var pokemon_encontrado=listaPokemones.find{
                    pokemon -> pokemon.nombre
                .equals(chord.toLowerCase())||pokemon.tipo.equals(chord.toLowerCase()) }
            return pokemon_encontrado
        }
        fun obtener_pokemon(posicion: Int):Pokemon{
            return listaPokemones.get(posicion);
        }

        fun buscar_entrenador(chord:String): Entrenador? {
            var entrenador_encontrado= listaEntrenadores.find{
                    entrenador -> entrenador.nombre
                .equals(chord.toLowerCase())||entrenador.color.equals(chord.toLowerCase()) }
            return entrenador_encontrado
        }
        fun agregar_entrenador(entrenador:Entrenador){
            listaEntrenadores.add(entrenador)
        }
        fun eliminar_entrendor(entrenador: Entrenador){
            listaEntrenadores.remove(entrenador)

        }
        fun modificar_entrenador(posicion:Int, entrenador: Entrenador){
            listaEntrenadores.set(posicion,entrenador);
        }
        fun obtener_entrenador(posicion: Int):Entrenador{
            return listaEntrenadores.get(posicion);
        }
        fun modificar_pokemon(posicion:Int, pokemon: Pokemon){
            listaPokemones.set(posicion,pokemon);
        }


    }
}