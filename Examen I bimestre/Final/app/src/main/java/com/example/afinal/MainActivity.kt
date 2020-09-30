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
            this.startActivity(Intent(this, ListaPokemones::class.java))
        }
        btn_entrenador.setOnClickListener {
            AbrirListaEntrenadores()
        }
        btn_http.setOnClickListener {
            abrirActividadHttp()
        }
        btn_recycler.setOnClickListener {
            abrirRecycler()
        }

    }

    fun AbrirListaEntrenadores(){

        val intentExplicito = Intent(
            this,
            ListaEntrenadoresActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun abrirActividadHttp() {
        val intentExplicito = Intent(
            this,
            HttpActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun abrirRecycler() {
        val intentExplicito = Intent(
            this,
            RecyclerViewActivity::class.java
        )
        startActivity(intentExplicito)
    }



}