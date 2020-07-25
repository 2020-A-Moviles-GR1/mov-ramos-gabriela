package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_b_list_vuew.*

class BListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_list_vuew)
        val listaEntrenadores: ArrayList<Entrenador> = arrayListOf<Entrenador>()

        listaEntrenadores.add(Entrenador("Adrian", "Eguez"))
        listaEntrenadores.add(Entrenador("Gaby", "Ramos"))
        listaEntrenadores.add(Entrenador("Tefa", "Ramos"))
        listaEntrenadores.add(Entrenador("cornelio", "del rancho"))
        listaEntrenadores.add(Entrenador("Pacho", "Villa"))

        val adaptador: ArrayAdapter<Entrenador> = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout
            listaEntrenadores // Lista
        )

        lv_numeros.adapter = adaptador

        lv_numeros
            .onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view", "Posicion $position")
        }


        btn_anadir_entrenador
            .setOnClickListener {
                anadirEntrenador(
                    adaptador,
                    listaEntrenadores
                )
            }

    }

    fun anadirEntrenador(
        adaptador: ArrayAdapter<Entrenador>,
        listaEntrenadores: ArrayList<Entrenador>
    ){
        listaEntrenadores.add(
            Entrenador("Nuevo", "Entrenador")
        )
        adaptador.notifyDataSetChanged()
    }


}