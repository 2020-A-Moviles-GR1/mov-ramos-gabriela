package com.example.afinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val listaEntrenadores = arrayListOf<EntrenadorHttp>()
        listaEntrenadores.add(

            EntrenadorHttp(
                1,
                123123123,
                123123123,
                "adrian",
                "verde",
                1,
                true,
                arrayListOf<PokemonHttp>()
            )
        )
        listaEntrenadores.add(
            EntrenadorHttp(
                1,
                123123123,
                123123123,
                "adrian",
                "verde",
                1,
                true,
                arrayListOf<PokemonHttp>()
            )
        )
        listaEntrenadores.add(
            EntrenadorHttp(
                1,
                123123123,
                123123123,
                "adrian",
                "verde",
                1,
                true,
                arrayListOf<PokemonHttp>()
            )
        )
        iniciarRecyclerView(listaEntrenadores
        , this,
        rv_entrenadores)
    }

    fun iniciarRecyclerView(
        Lista: List<EntrenadorHttp>,
        actividad: RecyclerViewActivity,
        recycler_view: androidx.recyclerview.widget.RecyclerView
    ){
        val adaptadorEntrenador = RecyclerAdapterEntrenadores(
            Lista,
            actividad,
            recycler_view
        )
        rv_entrenadores.adapter = adaptadorEntrenador
        rv_entrenadores.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_entrenadores.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptadorEntrenador.notifyDataSetChanged()

    }

}