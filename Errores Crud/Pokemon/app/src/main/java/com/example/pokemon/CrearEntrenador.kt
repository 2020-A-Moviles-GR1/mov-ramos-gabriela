package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_crear_entrenador.*
import java.util.*

class CrearEntrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_entrenador)

        val listaEntrenadores: ArrayList<Entrenador> = arrayListOf<Entrenador>()
        var activoE =true
        //      listaEntrenadores.add(ServicioBDD.Companion())

        listaEntrenadores.add(Entrenador("Vicente", "Verde",2,true,2.2))
        listaEntrenadores.add(Entrenador("Wendy", "Amarillo",3,true,2.2))
        listaEntrenadores.add(Entrenador("Ivan", "Parra",4,true,2.2))
        listaEntrenadores.add(Entrenador("Juan", "Duran",5,true,2.2))
        listaEntrenadores.add(Entrenador("Andrea", "Lara",6,true,2.2))
        listaEntrenadores.add(Entrenador("Lisa", "Guerrero",7,true,2.2))

        val adaptador: ArrayAdapter<Entrenador> = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout
            listaEntrenadores // Lista

        )

        lv_entrenador.adapter = adaptador

        lv_entrenador
            .onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Log.i("list-view", "Posicion $position")


        }

        btn_guardar
            .setOnClickListener {
                crearEntrenador(
                    adaptador,
                    listaEntrenadores
                )
            }



    }
    fun irAIntentCargarEntrenador(position: Int, listaEntrenadores: ArrayList<Entrenador>){


        var enviarEntrenador = Intent(this,
            EditarEntrenador::class.java
        )
        Log.i("gaby", "Nombre ${listaEntrenadores.get(position).nombre}")
        enviarEntrenador.putExtra("id", position)

        startActivity(enviarEntrenador)


    }

    fun crearEntrenador(adaptador: ArrayAdapter<Entrenador>,
                        listaEntrenadores: ArrayList<Entrenador>){



        listaEntrenadores.add(Entrenador(etv_nombre.text.toString(),etv_color.text.toString(),etv_nivel_entrenador.text.toString().toInt(),
            cb_activo_entrenador.isChecked,etv_distancia.text.toString().toDouble())

        )
        ServicioBDD.anadirEntrenador()

        adaptador.notifyDataSetChanged()

    }
}