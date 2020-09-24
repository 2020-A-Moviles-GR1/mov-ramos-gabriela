package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_pokemon.setOnClickListener {
            this.startActivity(Intent(this, AgregarEntrenadorActivity::class.java))
        }
        btn_entrenador.setOnClickListener {
            this.startActivity(Intent(this,ListaEntrenadoresActivity::class.java))
        }
        btn_http.setOnClickListener {
            abrirActividadHttp()
        }

    }

    fun abrirActividadHttp() {
        val intentExplicito = Intent(
            this,
            HttpActivity::class.java
        )
        startActivity(intentExplicito)
    }

}