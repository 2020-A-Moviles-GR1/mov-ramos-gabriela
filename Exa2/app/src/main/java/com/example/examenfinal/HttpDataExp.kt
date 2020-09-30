package com.example.examenfinal

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
import com.llamasoftworks.examen.Carta
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class HttpDataExp{
    companion object{
        var expansionesList = arrayListOf<Expansion>()
        var listaNombresCartasOnExp = mutableListOf<String>()
        var idsCartasOnExp = arrayListOf<String>()
        var cartasOnExp = arrayListOf<Carta>()
    }
    var urlPrincipal = "http://192.168.1.3:1337"
    val dateConverter = object: Converter {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun canConvert(cls: Class<*>)
                = cls == LocalDate::class.java

        @RequiresApi(Build.VERSION_CODES.O)
        override fun fromJson(jv: JsonValue) =
            if (jv.longValue != null) {
                Instant.ofEpochMilli(jv.longValue!!).atZone(ZoneId.systemDefault()).toLocalDate();
            } else {
                throw KlaxonException("Couldn't parse date: ${jv.string}")
            }

        override fun toJson(o: Any)
                = """ { "releaseDate" : $o } """
    }

    fun readExpansionNames(){
        val (request, response, result) = "http://192.168.1.3:1337/expansion"
            .httpGet()
            .responseString()
        when(result){
            is com.github.kittinunf.result.Result.Success -> {
                val data = result.get()
                val expansiones = Klaxon()
                    .fieldConverter(KlaxonDate::class, dateConverter)
                    .parseArray<Expansion>(data)
                if (expansiones != null){
                    expansionesList.clear()
                    listaNombresCartasOnExp.clear()
                    expansiones.forEach{
                        expansionesList.add(it)
                    }
                }
            }
            is com.github.kittinunf.result.Result.Failure -> {
                val ex = result.getException()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createExpansion(expansion: Expansion){
        val url = urlPrincipal + "/expansion"
        val instant: Instant = expansion.releaseDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        val parametrosCarta = listOf(
            "nombre" to expansion.nombre,
            "id" to expansion.id,
            "releaseDate" to instant.toEpochMilli(),
            "precio" to expansion.precio,
            "tcg" to expansion.tcg
        )
        url.httpPost(parametrosCarta)
            .responseString{
                    req, res, result ->
                when(result){
                    is com.github.kittinunf.result.Result.Failure ->{
                        val error = result.getException()
                    }
                    is com.github.kittinunf.result.Result.Success -> {
                        val usuarioString = result.get()
                    }
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun readExpansion(posicion:Int):List<*>{
        val nombre = expansionesList[posicion].nombre
        var listaDeDatosExpansion = mutableListOf("","",0,LocalDate.now(),0.01,true, listOf<String>())
        val url = urlPrincipal + "/expansion?nombre="+nombre
        val (request, response, result) = url
            .httpGet()
            .responseString()
        when(result){
            is com.github.kittinunf.result.Result.Success -> {
                val data = result.get()
                val expansion = Klaxon()
                    .fieldConverter(KlaxonDate::class, dateConverter)
                    .parseArray<Expansion>(data)
                if (expansion != null){
                    Log.i("http-klaxon","Datos-----------: ${expansion[0].cartas}")
                    listaDeDatosExpansion = mutableListOf(expansion[0].nombre,expansion[0].id,
                        expansion[0].releaseDate,expansion[0].precio,expansion[0].tcg,expansion[0].cartas)
                }
            }
            is com.github.kittinunf.result.Result.Failure -> {
                val ex = result.getException()
                //Log.i("http-klaxon","Error: ${ex.cause}")
            }
        }
        return listaDeDatosExpansion
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateExpansion(newName: String, id: String,
                        releaseDate: LocalDate, tcg: Boolean, precio: Double,
                        listCartas: ArrayList<String>){

        val url = urlPrincipal + "/expansion/"+id
        val instant: Instant = releaseDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        val parametrosCarta = listOf(
            "nombre" to newName,
            "id" to id,
            "releaseDate" to instant.toEpochMilli(),
            "precio" to precio,//,
            "tcg" to tcg,
            "cartas" to listCartas
        )
        Log.i("http-klaxon","Parametros: ${parametrosCarta} Cartas lenght ${listCartas.size}")
        url.httpPut(parametrosCarta)
            .responseString{
                    req, res, result ->
                when(result){
                    is com.github.kittinunf.result.Result.Failure ->{
                        val error = result.getException()
                        Log.i("http-klaxon","Error put: ${error}")

                    }
                    is com.github.kittinunf.result.Result.Success -> {
                        val usuarioString = result.get()
                    }
                }
            }
    }

    fun deleteExpansion (idExpansion:String){
        val url = urlPrincipal + "/expansion/"+idExpansion
        url.httpDelete()
            .responseString{
                    req, res, result ->
                when(result){
                    is com.github.kittinunf.result.Result.Failure ->{
                        val error = result.getException()
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                    }
                }
            }
    }
}