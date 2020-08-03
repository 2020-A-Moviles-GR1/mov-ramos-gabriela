package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_crear_entrenador.*
import kotlinx.android.synthetic.main.activity_editar_entrenador.*
import kotlinx.android.synthetic.main.activity_editar_entrenador.etv_color
import java.util.ArrayList

class EditarEntrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_entrenador)
        val listaEntrenadores: ArrayList<Entrenador> = arrayListOf<Entrenador>()
        //listaEntrenadores = ServicioBDD.arreglo_entrenador
        var entrenador = ServicioBDD.arreglo_entrenador.get(1)

        val textoCompartido = intent.getIntExtra("nombre", 0)

        listaEntrenadores.get(textoCompartido).nombre
        tv_nombre_entrenador.setText(listaEntrenadores.get(textoCompartido).nombre)

    }

}


