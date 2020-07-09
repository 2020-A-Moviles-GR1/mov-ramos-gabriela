package com.example.deber_pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_pokemon.*

class activity_pokemon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        btn_crear_pokemon
            .setOnClickListener {

                irCrearEntrenador()
            }

        btn_buscar_pokemon
            .setOnClickListener {

                irBuscarEntrenador()
            }

        btn_editar_pokemon
            .setOnClickListener {

                irEditarEntrenador()
            }
        btn_eliminar_pokemon
            .setOnClickListener {

                irEliminarEntrenador()
            }

    }

    fun irCrearEntrenador() {

        val intentExplicito =
            Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
                this,                       //mandamos el this
                activity_crear_pokemon::class.java     //mandamos la actividad a donde queremos que se vaya
            )
        startActivity(intentExplicito)
    }


    fun irBuscarEntrenador() {
        val intentExplicito =
            Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
                this,                       //mandamos el this
                activity_buscar_pokemon::class.java     //mandamos la actividad a donde queremos que se vaya
            )
        startActivity(intentExplicito)
    }

    fun irEditarEntrenador() {
        val intentExplicito =
            Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
                this,                       //mandamos el this
                activity_editar_pokemon::class.java     //mandamos la actividad a donde queremos que se vaya
            )
        startActivity(intentExplicito)
    }


    fun irEliminarEntrenador() {
        val intentExplicito =
            Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
                this,                       //mandamos el this
                activity_eliminar_pokemon::class.java     //mandamos la actividad a donde queremos que se vaya
            )
        startActivity(intentExplicito)
    }

}