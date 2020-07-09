package com.example.deber_pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_entrenador
            .setOnClickListener {

                irEntrenador()
            }

        btn_pokemon
            .setOnClickListener {
                irPokemon()
            }
    }


    fun irEntrenador() {
        val intentExplicito =
            Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
                this,                       //mandamos el this
                activity_entrenador::class.java     //mandamos la actividad a donde queremos que se vaya
            )
        startActivity(intentExplicito)
    }

   fun irPokemon() {
        val intentExplicito =
            Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
                this,                       //mandamos el this
                activity_pokemon ::class.java     //mandamos la actividad a donde queremos que se vaya
            )
        startActivity(intentExplicito)
    }


}