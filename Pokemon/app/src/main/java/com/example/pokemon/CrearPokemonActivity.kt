package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crear_pokemon.*

class CrearPokemonActivity : AppCompatActivity() {
    var nombreEntrenador = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_pokemon)

        this.nombreEntrenador = ServicioBDD.nombre
        if (nombreEntrenador != null) {
            tv_nombre_entrenador.text = nombreEntrenador.toString()
        }

    }
}