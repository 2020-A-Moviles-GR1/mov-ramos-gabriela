package com.example.afinal

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class BddService {
    companion object{
        val urlPrincipal="http://192.168.1.9:1337"

        var listaEntrenadores= listOf<Entrenador>()
        var entrenador: Entrenador? =null
        var pokemon: Pokemons? =null

        var listaPokemones= listOf<Pokemons>()
        fun buscarPokemon(chord:String): Pokemons? {
            var poke=chord.toLowerCase()

            var pokemonEncontrado=listaPokemones.find{

                    pokemons -> pokemons.nombre.equals(poke)

            }



            return pokemonEncontrado
        }
        fun getEntrenadores() {

            val url= "$urlPrincipal/entrenador"
            url.httpGet().responseString{
                    request, response, result ->
                when(result){
                    is Result.Success->{
                        val data=result.get()
                        val entrenadores=Klaxon().parseArray<Entrenador>(data)
                        if(entrenadores!=null){
                            this.listaEntrenadores=entrenadores
                            entrenadores.forEach {
                            }

                        }

                    }
                    is Result.Failure->{
                        val ex=result.getException()
                        Log.i("Http Exception","Error  entrenadores: $ex.message")

                    }
                }
            }.join()
        }
        fun getPokemon(id: Int){
            val url= "$urlPrincipal/pokemon/${id}"
            url.httpGet().responseString{
                    request, response, result ->
                when(result){
                    is Result.Success->{
                        val data=result.get()
                        Log.i("Http data Acorde","Data acorde: $data")
                        val pokemon=Klaxon().parse<Pokemons>(data)
                        if(pokemon!=null){

                            this.pokemon=pokemon


                        }

                    }
                    is Result.Failure->{
                        val ex=result.getException()
                        Log.i("Http Exception","Error  pokemones: $ex.message")

                    }
                }
            }.join()
        }
        fun getPokemones(){
            val url= "$urlPrincipal/pokemon"
            url.httpGet().responseString{
                    request, response, result ->
                when(result){
                    is Result.Success->{
                        val data=result.get()
                        Log.i("Http data Acorde","Data acorde: $data")
                        val pokemones=Klaxon().parseArray<Pokemons>(data)
                        if(pokemones!=null){
                            this.listaPokemones=pokemones
                            pokemones.forEach {
                            }

                        }

                    }
                    is Result.Failure->{
                        val ex=result.getException()
                        Log.i("Http Exception","Error  entrena: $ex.message")

                    }
                }
            }.join()
        }

        fun getPokemonEntrenador(idEntrenador: Int){
            val url= "$urlPrincipal/pokemon/"
            url.httpGet().responseString{
                    request, response, result ->
                when(result){
                    is Result.Success->{
                        val data=result.get()
                        val pokemones=Klaxon().parseArray<Pokemons>(data)
                        if(pokemones!=null){
                            this.listaPokemones=pokemones
                            pokemones.forEach {
                            }

                        }

                    }
                    is Result.Failure->{
                        val ex=result.getException()
                        Log.i("Http Exception","Error obteniendo canciones: $ex.message")

                    }
                }
            }.join()
        }
        fun getIdPokemon(nombre:String) {
            val url= "$urlPrincipal/pokemon?nombre=${nombre}"
            url.httpGet().responseString{
                    request, response, result ->
                when(result){
                    is Result.Success->{
                        val data=result.get()
                        val pokemons=Klaxon().parse<Pokemons>(data)
                        if(pokemons!=null){
                           var id = pokemons.id
                        }
                    }
                    is Result.Failure->{
                        val ex=result.getException()
                        Log.i("Http Exception","Error obteniendo cancion: $ex.message")

                    }
                }
            }.join()

        }
        fun getEntrenador(id:Int) {

            val url= "$urlPrincipal/entrenador/${id}"
            url.httpGet().responseString{
                    request, response, result ->
                when(result){
                    is Result.Success->{
                        val data=result.get()
                        Log.i("Http data cancion","Data cancion: $data")
                        val entrenador=Klaxon().parse<Entrenador>(data)
                        if(entrenador!=null){
                            this.entrenador=entrenador
                            Log.i("Http-Klaxon","Nombre ${entrenador.nombre} , color : ${entrenador.color}")



                        }

                    }
                    is Result.Failure->{
                        val ex=result.getException()
                        Log.i("Http Exception","Error  : $ex.message")

                    }
                }
            }.join()
        }


        fun postPokemon(nombre:String,tipo:String,nivel:String,activo:String,entrenadornuevo:String,latitud:String,longitud:String,url:String, imagen:String){
            val url= "$urlPrincipal/pokemon"
            val parametrosUsuario=listOf(
                "nombre" to nombre,
                "tipo" to tipo,
                "nivel" to nivel,
                "activo" to activo,
                "entrenador" to entrenadornuevo,
                "latitud" to latitud,
                 "longitud" to longitud,
                 "url" to  url,
                 "imagen" to  imagen
            )
            url.httpPost(parametrosUsuario).responseString{
                    req,res,result->
                when(result){
                    is Result.Failure->{
                        val error= result.getException()
                    }
                    is Result.Success->{
                        val cancionString=result.get()
                    }
                }
            }.join()

        }


        fun postEntrenador(nombre:String, color:String, nivel:String, activo:String, pokemones:String){
            val url= "$urlPrincipal/entrenador"
            val parametrosEntrenador=listOf(
                "nombre" to nombre,
                "color" to color,
                "nivel" to nivel,
                "activo" to activo,
                "pokemones" to pokemones
            )
            url.httpPost(parametrosEntrenador).responseString{
                    req,res,result->
                when(result){
                    is Result.Failure->{
                        val error= result.getException()
                    }
                    is Result.Success->{
                        val cancionString=result.get()
                    }
                }
            }.join()

        }
        fun deletePokemon(id:Int){
            val url= "$urlPrincipal/pokemon/${id}"

            url.httpDelete().responseString{
                    req,res,result->
                when(result){
                    is Result.Failure->{
                        val error= result.getException()
                    }
                    is Result.Success->{
                        val cancionString=result.get()
                    }
                }
            }.join()
        }
        fun deleteEntrenador(id:Int){
            val url= "$urlPrincipal/entrenador/${id}"

            url.httpDelete().responseString{
                    req,res,result->
                when(result){
                    is Result.Failure->{
                        val error= result.getException()
                    }
                    is Result.Success->{
                        val cancionString=result.get()
                    }
                }
            }.join()
        }

        fun putEntrenador(id:Int, nombre:String, color:String, nivel:String, activo:String, pokemones:String){
            val url= "$urlPrincipal/entrenador/${id}"
            val parametrosUsuario=listOf(
                "nombre" to nombre,
                "color" to color,
                "nivel" to nivel,
                "activo" to activo,
                "pokemones" to pokemones
            )
            url.httpPut(parametrosUsuario).responseString{
                    req,res,result->
                when(result){
                    is Result.Failure->{
                        val error= result.getException()
                    }
                    is Result.Success->{
                        val cancionString=result.get()
                        Log.i("Http-klaxon","Modificado ${cancionString}")
                    }
                }
            }.join()
        }

        fun putPokemon(id:Int,nombre:String,nivel:String,tipo:String,activo:String,entrenador:String,latitud: String,longitud: String,urlnueva: String,imagen: String){

            val url= "$urlPrincipal/pokemon/${id}"
            Log.i("Http-klaxonPOKEMON","ENVIO ${url}")

            val parametrosUsuario=listOf(
                "id" to id,
                "nombre" to nombre,
                "nivel" to nivel,

                "tipo" to tipo,
                "activo" to activo,
                "entrenador" to entrenador,
                "latitud" to latitud,
                "longitud" to longitud,
                "url" to urlnueva,
                "imagen" to imagen


            )
            url.httpPut(parametrosUsuario).responseString{
                    req,res,result->
                when(result){
                    is Result.Failure->{
                        val error= result.getException()
                        Log.i("Http-klaxonPOKEMON","Error:${error}")
                    }
                    is Result.Success->{
                        val cancionString=result.get()
                        Log.i("Http-klaxonPOKEMON","Modificado ${cancionString}")
                    }
                }
            }.join()
        }

        fun agregarEntrenador(nombre:String, color:String, nivel:String, activo:String, acordes:String){
            postEntrenador(nombre,color,nivel,activo,acordes)
            getEntrenadores()
        }
        fun elimarEntrenador(id:Int){
            deleteEntrenador(id)
            getEntrenadores()
            //listaCanciones.remove(cancion)


        }

        fun elimarPokemon(id:Int){
            deletePokemon(id)
            getPokemones()
            //listaCanciones.remove(cancion)


        }

        fun modificarEntrenador(id:Int, nombre:String, color:String, nivel:String, activo:String, acordes:String){
            putEntrenador(id,nombre,color,nivel,activo,acordes)
            getEntrenador(id)
            //listaCanciones.set(posicion,cancion);
        }
        fun obtenerEntrenador(posicion: Int): Entrenador? {
            getEntrenador(posicion)
            return entrenador
        }

        fun obtenerPokemon(posicion: Int): Pokemons? {
            getPokemon(posicion)
            return pokemon
        }




        fun crearPokemon(id:Int,nombre:String, tipo:String,nivel:String,activo: String,entrenador:String,latitud:String,longitud:String,url:String, imagen:String){
            postPokemon(nombre,tipo,nivel,activo,entrenador, latitud, longitud, url, imagen)
            getPokemones()
            //listaCanciones.set(posicion,cancion);
        }

        fun modificarPokemon(id:Int,nombre:String,nivel:String, tipo:String,activo: String,entrenador:String,latitud: String,longitud: String,url:String, imagen:String){
            putPokemon(id,nombre,nivel,tipo,activo,entrenador,latitud,longitud,url,imagen)
           getPokemones()
        }
        fun idPokemon(nombre:String){
            getIdPokemon(nombre)
            getPokemones()
        }
    }
}