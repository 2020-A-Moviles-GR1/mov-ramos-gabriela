package com.example.afinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_http.*

class HttpActivity : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.15:1337"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        btn_obtener
            .setOnClickListener {
                obtenerEntrenador()
            }

    }

    fun obtenerEntrenador(){
        val pokemonString = """
       {
            "createdAt": 1600915543439,
            "updatedAt": 1600919307678,
            "id": 1,
            "nombre": "prueba",
            "tipo": "agua",
            "nivel": 3,
            "activo": false,
            "entrenador": 4
          }
        """.trimIndent()


        val pokemonInstancia = Klaxon().parse<PokemonHttp>(pokemonString)
        if(pokemonInstancia!=null) {
            Log.i("http-klaxon", "nombre POKEMON: ${pokemonInstancia.nombre}")
            Log.i("http-klaxon", "fecha POKEMON: ${pokemonInstancia.fechaCreacion}")
            Log.i("http-klaxon", "nivel POKEMON: ${pokemonInstancia.nivel}")
            Log.i("http-klaxon", "activo POKEMON: ${pokemonInstancia.activo}")

        }
        val url = urlPrincipal + "/entrenador"
        url.httpGet().responseString { request, response, result ->
        when(result){
            is Result.Failure ->{
                val ex= result.getException()
                Log.i("http-klaxon", "error: ${ex.message}")

            }

            is Result.Success ->{
                val data= result.get()
                Log.i("http-klaxon", "data: ${data}")

                val entrenadores = Klaxon().parseArray<EntrenadorHttp>(data)
                if(entrenadores!=null){
                    entrenadores.forEach{
                        Log.i("http-klaxon", "ENTRENADOR nombre : ${it.nombre} "+ "COLOR: ${it.color}" )
                    if (it.pokemons.size>0){
                        it.pokemons.forEach {
                            Log.i("http-klaxon", "POKEMON  : ${it.nombre} " )

                        }

                    }
                    }
                }

            }
        }

        }

    }
}