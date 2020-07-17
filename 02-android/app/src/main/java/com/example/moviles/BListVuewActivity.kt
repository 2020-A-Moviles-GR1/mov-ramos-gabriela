package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_b_list_vuew.*

class BListVuewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_list_vuew)
        var listaEntrenadores = arrayListOf<Entrenador>()
        listaEntrenadores.add(Entrenador("Adrian", "Eguez"))
        listaEntrenadores.add(Entrenador("Gaby", "Ramos"))
        listaEntrenadores.add(Entrenador("Tefa", "Ramos"))
        listaEntrenadores.add(Entrenador("cornelio", "del rancho"))
        listaEntrenadores.add(Entrenador("Pacho", "Villa"))

       val adaptador = ArrayAdapter(
           this,
           android.R.layout.simple_list_item_1,
           listaEntrenadores
       )
        lv_numeros.adapter = adaptador

        lv_numeros.onItemClickListener=AdapterView.OnItemClickListener(
                pa
        )

        lo
        )



    }
}