package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_Entrenador.setOnClickListener {
            irEntrendor()

        }

        btn_Pokemon.setOnClickListener {
            irPokemon()

        }
    }
    fun irEntrendor(){
        val intentExplicito = Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
            this,                       //mandamos el this
            EntrenadorActivity::class.java     //mandamos la actividad a donde queremos que se vaya
        )
        // this.startActivity(intentExplicito)
        startActivity(intentExplicito) //para ejecutar necesitamos este metodo que esta dentro de la clase

    }

    fun irPokemon(){
        val intentExplicito = Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
            this,                       //mandamos el this
            PokemonActivity::class.java     //mandamos la actividad a donde queremos que se vaya
        )
        // this.startActivity(intentExplicito)
        startActivity(intentExplicito) //para ejecutar necesitamos este metodo que esta dentro de la clase

    }

}