package com.example.deber_pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crear_entrenador.*

class activity_crear_entrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_entrenador)

        btn_crear_fin_entrendor
            .setOnClickListener {
            crear_entrenador()
        }
    }

    fun crear_entrenador(){
        var id = et_id_nuevo_entrenador.text.toString()
        var nombre = et_nuevo_nombre_entrenador.text.toString()

        print(id)

    }
}