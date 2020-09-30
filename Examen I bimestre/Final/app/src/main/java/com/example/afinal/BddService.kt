package com.example.afinal

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import com.beust.klaxon.KlaxonException
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class BddService {
    companion object{
        val urlPrincipal="http://192.168.1.14:1337"

        var listaCanciones= listOf<Entrenador>()
        var cancion: Entrenador? =null
        var pokemon: Pokemons? =null

        var listaAcordes= listOf<Pokemons>()
        fun buscarAcorde(chord:String): Pokemons? {
            var ac=chord.toLowerCase()

            var acordeEncontrado=listaAcordes.find{

                    acorde -> acorde.nombre.equals(ac)

            }



            return acordeEncontrado
        }
        fun getCanciones() {

            val url= "$urlPrincipal/entrenador"
            url.httpGet().responseString{
                    request, response, result ->
                when(result){
                    is Result.Success->{
                        val data=result.get()
                        Log.i("Http data cancion","Data cancion: $data")
                        val canciones=Klaxon().parseArray<Entrenador>(data)
                        if(canciones!=null){
                            this.listaCanciones=canciones
                            canciones.forEach {
                                Log.i("Http-Klaxon-canciones","Nombre ${it.nombre} , : ${it.color}")
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
        fun getAcorde(id: Int){
            val url= "$urlPrincipal/pokemon/${id}"
            Log.i("ENVIOOOOO","consutlo: ${url}")
            url.httpGet().responseString{
                    request, response, result ->
                when(result){
                    is Result.Success->{
                        val data=result.get()
                        Log.i("Http data Acorde","Data acorde: $data")
                        val pokemon=Klaxon().parse<Pokemons>(data)
                        if(pokemon!=null){

                            this.pokemon=pokemon
                                //Log.i("Http-Klaxon-acordes","Nombre ${it.nombre} , : ${it.tipo}")


                        }

                    }
                    is Result.Failure->{
                        val ex=result.getException()
                        Log.i("Http Exception","Error obteniendo canciones: $ex.message")

                    }
                }
            }.join()
        }
        fun getAcordes(){
            val url= "$urlPrincipal/pokemon"
            url.httpGet().responseString{
                    request, response, result ->
                when(result){
                    is Result.Success->{
                        val data=result.get()
                        Log.i("Http data Acorde","Data acorde: $data")
                        val acordes=Klaxon().parseArray<Pokemons>(data)
                        if(acordes!=null){
                            this.listaAcordes=acordes
                            acordes.forEach {
                                Log.i("Http-Klaxon-acordes","Nombre ${it.nombre} , : ${it.tipo}")
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

        fun getPokemonEntrenador(idEntrenador: Int){
            val url= "$urlPrincipal/pokemon/"
            url.httpGet().responseString{
                    request, response, result ->
                when(result){
                    is Result.Success->{
                        val data=result.get()
                        Log.i("Http data Acorde","Data acorde: $data")
                        val acordes=Klaxon().parseArray<Pokemons>(data)
                        if(acordes!=null){
                            this.listaAcordes=acordes
                            acordes.forEach {
                                Log.i("Http-Klaxon-acordes","Nombre ${it.nombre} , : ${it.tipo}")
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
                        val cancion=Klaxon().parse<Pokemons>(data)
                        if(cancion!=null){
                           var id = cancion.id
                        }
                    }
                    is Result.Failure->{
                        val ex=result.getException()
                        Log.i("Http Exception","Error obteniendo cancion: $ex.message")

                    }
                }
            }.join()

        }
        fun getCancion(id:Int) {

            val url= "$urlPrincipal/entrenador/${id}"
            url.httpGet().responseString{
                    request, response, result ->
                when(result){
                    is Result.Success->{
                        val data=result.get()
                        Log.i("Http data cancion","Data cancion: $data")
                        val cancion=Klaxon().parse<Entrenador>(data)
                        if(cancion!=null){
                            this.cancion=cancion
                            Log.i("Http-Klaxon-canciones","Nombre ${cancion.nombre} , : ${cancion.color}")


                        }

                    }
                    is Result.Failure->{
                        val ex=result.getException()
                        Log.i("Http Exception","Error obteniendo cancion: $ex.message")

                    }
                }
            }.join()
        }
        fun postCancion(nombre:String,color:String,nivel:String,activo:String,pokemones:String){
            val url= "$urlPrincipal/entrenador"
            val parametrosUsuario=listOf(
                "nombre" to nombre,
                "color" to color,
                "nivel" to nivel,
                "activo" to activo,
                "pokemones" to pokemones
            )
            url.httpPost(parametrosUsuario).responseString{
                    req,res,result->
                when(result){
                    is Result.Failure->{
                        val error= result.getException()
                        Log.i("http-klaxon-post-cancion","Error:${error}")
                    }
                    is Result.Success->{
                        val cancionString=result.get()
                        Log.i("Http-klaxon","Agregado ${cancionString}")
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
                        Log.i("http-klaxon-delete-cancion","Error:${error}")
                    }
                    is Result.Success->{
                        val cancionString=result.get()
                        Log.i("Http-klaxon","eliminado ${cancionString}")
                    }
                }
            }.join()
        }
        fun deleteCancion(id:Int){
            val url= "$urlPrincipal/entrenador/${id}"

            url.httpDelete().responseString{
                    req,res,result->
                when(result){
                    is Result.Failure->{
                        val error= result.getException()
                        Log.i("http-klaxon-delete-cancion","Error:${error}")
                    }
                    is Result.Success->{
                        val cancionString=result.get()
                        Log.i("Http-klaxon","eliminado ${cancionString}")
                    }
                }
            }.join()
        }

        fun putCancion(id:Int,nombre:String,color:String,nivel:String,activo:String,pokemones:String){
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
                        Log.i("http-klaxon-put-cancion","Error:${error}")
                    }
                    is Result.Success->{
                        val cancionString=result.get()
                        Log.i("Http-klaxon","Modificado ${cancionString}")
                    }
                }
            }.join()
        }

        fun putPokemon(id:Int,nombre:String,tipo:String,nivel:String,activo:String,entrenador:String){

            val url= "$urlPrincipal/pokemon/${id}"
            Log.i("Http-klaxonPOKEMON","ENVIO ${url}")

            val parametrosUsuario=listOf(
                "id" to id,
                "nombre" to nombre,
                "nivel" to nivel,
                "activo" to activo,
                "entrenador" to entrenador


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

        fun agregarCancion(nombre:String,color:String,nivel:String,activo:String,acordes:String){
            postCancion(nombre,color,nivel,activo,acordes)
            getCanciones()
        }
        fun elimarCancion(id:Int){
            deleteCancion(id)
            getCanciones()
            //listaCanciones.remove(cancion)


        }

        fun elimarPokemon(id:Int){
            deletePokemon(id)
            getAcordes()
            //listaCanciones.remove(cancion)


        }

        fun modificarCancion(id:Int,nombre:String,color:String,nivel:String,activo:String,acordes:String){
            putCancion(id,nombre,color,nivel,activo,acordes)
            getCancion(id)
            //listaCanciones.set(posicion,cancion);
        }
        fun obtenerCancion(posicion: Int): Entrenador? {
            getCancion(posicion)
            return cancion
        }

        fun obtenerPokemon(posicion: Int): Pokemons? {
            getAcorde(posicion)
            return pokemon
        }





        fun modificarPokemon(id:Int,nombre:String, tipo:String,nivel:String,activo: String,entrenador:String){
            putPokemon(id,nombre,tipo,nivel,activo,entrenador)
           // getAcordes()
            //listaCanciones.set(posicion,cancion);
        }
        fun idPokemon(nombre:String){
            getIdPokemon(nombre)
            getAcordes()
            //listaCanciones.set(posicion,cancion);
        }
    }
}