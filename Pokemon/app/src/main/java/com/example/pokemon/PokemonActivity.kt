package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pokemon.*

class PokemonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)


    }

    fun irCrearPokemon(){
        val intentExplicito = Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
            this,                       //mandamos el this
            CrearPokemonActivity::class.java     //mandamos la actividad a donde queremos que se vaya
        )
        // this.startActivity(intentExplicito)
        startActivity(intentExplicito) //para ejecutar necesitamos este metodo que esta dentro de la clase

    }
}