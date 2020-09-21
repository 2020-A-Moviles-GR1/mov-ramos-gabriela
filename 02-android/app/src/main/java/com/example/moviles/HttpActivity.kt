package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_http.*

class HttpActivity : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.11:1337"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        btn_obtener
            .setOnClickListener {
                obtenerUsuarios()
            }

        btn_crear
            .setOnClickListener {
                Log.i("http-klaxon", "Entraste")
                crearUsuario()
            }



    }

    fun crearUsuario() {
        val url = urlPrincipal + "/Usuario"

        val parametrosUsuario: List<Pair<String, String>> = listOf(
            "cedula" to "1122330065",
            "nombre" to "Ramiro",
            "correo" to "r@r.com",
            "estadoCivil" to "Casado",
            "password" to "A123456789b"
        )

        url
            .httpPost(parametrosUsuario)
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                        Log.i("http-klaxon", "${usuarioString}")
                    }
                }
            }

//        "cedula": "1718137159",
//        "nombre": "Sol",
//        "correo": "adrian.eguez@epn.edu.ec",
//        "estadoCivil": "Soltero",
//        "password": "SuperS3gura"

    }


    fun obtenerUsuarios(){
        val pokemonString = """
    {
    "createdAt": 1597671444841,
    "updatedAt": 1597672206086,
    "id": 1,
    "nombre": "Pikachu",
    "usuario": 1
  }
""".trimIndent()

        val pokemonInstancia = Klaxon()
            .parse<PokemonHttp>(pokemonString)
        if (pokemonInstancia != null) {
            Log.i("http-klaxon", "Nombre: ${pokemonInstancia.nombre}")
            Log.i(
                "http-klaxon",
                "Nombre: ${pokemonInstancia.fechaCreacion}"
            )
        }
        val url = urlPrincipal + "/Usuario"
        url
            .httpGet()
            .responseString {
                    request, response, result ->
                when(result){
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http-klaxon","Data: ${data}")

                        val usuarios = Klaxon().parseArray<UsuarioHttp>(data)
                        if (usuarios != null) {
                            usuarios.forEach {
                                Log.i(
                                    "http-klaxon",
                                    "Nombre: ${it.nombre}"+
                                            " Pokemos: ${it.pokemons}" +
                                            " Estado civil: ${it.estadoCivil}"
                                )
                                if (it.pokemons is List<*>) {  //***************
                                    if (it.pokemons!!.size > 0) {

                                        it.pokemons!!.forEach {
                                            it as PokemonHttp //*********
                                            Log.i(
                                                "http-klaxon",
                                                "Nombre Pokemon: ${it.nombre}"
                                            )
                                        }
                                    }
                                }

                            }
                        }
                        obtenerPokemons() //****************

                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-klaxon", "Error: ${ex.message}")
                    }
                }
            }


    }

    fun obtenerPokemons() {
        val url = urlPrincipal + "/pokemon"
        url
            .httpGet()
            .responseString{
                    request, response, result ->
                when(result){
                    is Result.Success ->{
                        val data = result.get()
                        val pokemons = Klaxon()
                            .converter(PokemonHttp.myConverter)
                            .parseArray<PokemonHttp>(data)
                        if(pokemons != null){
                            pokemons.forEach{
                                Log.i("http-klaxon", "POKEMON: ${it.nombre}" + ", USUARIO: ${it.usuario}\n"
                                )
                            }
                        }
                    }
                    is Result.Failure ->{
                        val ex = result.getException()
                        Log.i("http-klaxon","Error: ${ex.message}")
                    }
                }
            }
    }
}