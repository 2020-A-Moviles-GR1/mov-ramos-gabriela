package com.example.deber_pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_entrenador.*

class activity_entrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenador)


        btn_crear_entrenador
                .setOnClickListener {

            irCrearEntrenador()
        }

        btn_buscar_entrenador
            .setOnClickListener {

                irBuscarEntrenador()
            }

    btn_editar_entrenador
            .setOnClickListener {

                irEditarEntrenador()
            }
        btn_elimimar_entrenador
            .setOnClickListener {

                irEliminarEntrenador()
            }
    }


    fun irCrearEntrenador() {
        val intentExplicito =
            Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
                this,                       //mandamos el this
                activity_crear_entrenador::class.java     //mandamos la actividad a donde queremos que se vaya
            )
        startActivity(intentExplicito)
    }


    fun irBuscarEntrenador() {
        val intentExplicito =
            Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
                this,                       //mandamos el this
                activity_buscar_entrenador::class.java     //mandamos la actividad a donde queremos que se vaya
            )
        startActivity(intentExplicito)
    }

    fun irEditarEntrenador() {
        val intentExplicito =
            Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
                this,                       //mandamos el this
                activity_editar_entrenador::class.java     //mandamos la actividad a donde queremos que se vaya
            )
        startActivity(intentExplicito)
    }


    fun irEliminarEntrenador() {
        val intentExplicito =
            Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
                this,                       //mandamos el this
                activity_eliminar_entrenador::class.java     //mandamos la actividad a donde queremos que se vaya
            )
        startActivity(intentExplicito)
    }
}