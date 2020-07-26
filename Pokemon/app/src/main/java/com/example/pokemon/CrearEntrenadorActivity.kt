package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_crear_entrenador.*

class CrearEntrenadorActivity : AppCompatActivity() {
    var nombre = ""
    val listaEntrenadores: ArrayList<Entrenador> = arrayListOf<Entrenador>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_entrenador)


        val listaEntrenadores: ArrayList<Entrenador> = arrayListOf<Entrenador>()
/*
        listaEntrenadores.add(Entrenador("Adrian", "Eguez"))
        listaEntrenadores.add(Entrenador("Gaby", "Ramos"))
        listaEntrenadores.add(Entrenador("Tefa", "Ramos"))
        listaEntrenadores.add(Entrenador("cornelio", "del rancho"))
        listaEntrenadores.add(Entrenador("Pacho", "Villa"))
*/
        val adaptador: ArrayAdapter<Entrenador> = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout
            listaEntrenadores // Lista
        )


        nombre = etv_nombre_entrenador.toString()

        btn_guardar.setOnClickListener {
            /*
            anadirEntrenador(
                adaptador,
                listaEntrenadores
            )*/
        }
        }

/*
    override fun onSaveInstanceState(
        outState: Bundle
    ) {
        Log.i("Activity", "onSaveInstanceState")
        outState?.run {
            putString("numeroActualGuardado",nombre)

        }
        super.onSaveInstanceState(outState)

    }
*/

    /*
    fun anadirEntrenador(
        adaptador: ArrayAdapter<Entrenador>,
        listaEntrenadores: ArrayList<Entrenador>
    ){
        listaEntrenadores.add(
            Entrenador("Nuevo", "Entrenador")
        )
        adaptador.notifyDataSetChanged()
    }
*/


}