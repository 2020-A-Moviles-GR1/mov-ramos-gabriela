package com.example.afinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_http.*

class HttpActivity : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.14:1337"


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        btn_obtener
            .setOnClickListener {
                obtenerEntrenador()
            }
        btn_crear.setOnClickListener {
            crearEntrenador()
        }

    }


    fun crearEntrenador() {
        val url = urlPrincipal + "/entrenador"

        val parametrosEntrenador = listOf(

        "nombre" to "tefa PRUEBACREAR",
        "color" to "MORADO",
        "nivel" to "8",
        "activo" to "false"
        )

        url.httpPost(parametrosEntrenador).responseString{
            req, res, result ->
        when(result){
            is Result.Failure->{
                val error = result.getException()
                Log.i("http-klaxon", "error: ${error}")

            }
            is Result.Success->{
                val usuarioString = result.get()
                Log.i("http-klaon", "AQUI ${usuarioString}")

            }

        }

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
                val intentExplicito= Intent(this, RecyclerViewActivity::class.java)

                val entrenadores = Klaxon().parseArray<EntrenadorHttp>(data)

                if(entrenadores!=null){

                    intentExplicito.putExtra("tamano",entrenadores.size)


                    entrenadores.forEach{
                        Log.i("http-klaxon", "ENTRENADOR nombre : ${it.nombre} "+ "COLOR: ${it.color}" )
                        var listaNombre : ArrayList<EntrenadorHttp>

                        intentExplicito.putExtra("nombre",it.nombre)
                        intentExplicito.putExtra("color",it.color)
                        intentExplicito.putExtra("id",it.id)
                        this.startActivity(intentExplicito)
                      //  Log.i("Taza", "HTTP ENVIA  : ${entrenadores} " )

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