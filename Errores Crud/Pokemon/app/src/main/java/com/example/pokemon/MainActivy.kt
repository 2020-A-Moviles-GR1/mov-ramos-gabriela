package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_crear_entrenador.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            irEditarEntrendor(

            )

        }

        btn_guardar
            .setOnClickListener {

            }




    }

    fun irEditarEntrendor(){


    }
    fun irCrearEntrendor(){
        val intentExplicito = Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
            this,                       //mandamos el this
            CrearEntrenador::class.java     //mandamos la actividad a donde queremos que se vaya
        )
        // this.startActivity(intentExplicito)
        startActivity(intentExplicito) //para ejecutar necesitamos este metodo que esta dentro de la clase

    }

    fun irCrearPokemon(){
        val intentExplicito = Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
            this,                       //mandamos el this
            CrearPokemon::class.java     //mandamos la actividad a donde queremos que se vaya
        )
        // this.startActivity(intentExplicito)
        startActivity(intentExplicito) //para ejecutar necesitamos este metodo que esta dentro de la clase

    }

}