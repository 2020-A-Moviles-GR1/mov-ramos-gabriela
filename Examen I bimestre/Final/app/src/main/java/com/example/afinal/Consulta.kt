package com.example.afinal

import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class Consulta: AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.14:1337"

    companion object {
        val numero: Int = 1

        /*var listaEntrenadores = arrayListOf<Entrenador>()
        fun cargarEntrenadores() {
            listaEntrenadores.add(
                Entrenador(1, "aa", nivel = numero, activo = true, listaPokemones = "a,a")

            )


        }

        */


        fun obtenerEntrenador() {
            val url = BddService.urlPrincipal + "/entrenador"
            url.httpGet().responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        val entrenadores = Klaxon().parseArray<EntrenadorHttp>(data)
                        if (entrenadores != null) {
                            entrenadores.forEach {
                                /*
                                BddService.listaEntrenadores.add(
                                    Entrenador(
                                        "${it.nombre}",
                                        "${it.color}",
                                        nivel = it.nivel,
                                        activo = it.activo,
                                        listaPokemones = "a,a"
                                    )

                                )

                                 */
                            }
                        }

                    }

                }
            }
        }
    }
}