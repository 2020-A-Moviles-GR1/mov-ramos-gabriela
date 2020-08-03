package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_editar_entrenador.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btn_eliminar
import kotlinx.android.synthetic.main.activity_main.etv_color
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var posicion =0
        var idNuevo=5
        var nombreEntre : String
        Entrenador.companion_entrenador.add(Entrenador(0,"Vicente", "Verde",2,true,2.2))
        Entrenador.companion_entrenador.add(Entrenador(1,"Wendy", "Amarillo",3,false,2.2))
        Entrenador.companion_entrenador.add(Entrenador(2,"Ivan", "Parra",4,true,2.2))
        Entrenador.companion_entrenador.add(Entrenador(3,"Juan", "Duran",5,false,2.2))
        Entrenador.companion_entrenador.add(Entrenador(4,"Andrea", "Lara",6,true,2.2))
        Entrenador.companion_entrenador.add(Entrenador(5,"Lisa", "Guerrero",7,true,2.2))

        val adaptador: ArrayAdapter<Entrenador> = ArrayAdapter(
                this, // Contexto
                android.R.layout.simple_list_item_1, // Nombre Layout
                Entrenador.companion_entrenador // Lista
        )

        lv_entrenadores.adapter = adaptador

        lv_entrenadores
                .onItemClickListener = AdapterView.OnItemClickListener {
            parent, view, position, id ->
            posicion=position
            llenardatos(position)
        }


        btn_editar_entrenador.setOnClickListener {
            editarEntrenador(posicion, adaptador )

            Log.i("editarActivo","posicion enviada ${posicion}")


          //  adaptador.notifyDataSetChanged()
        //    Log.i("editarActivo","posicion ${cb_activo_entrenador.isChecked()}")



        }


        btn_nuevo_entrenador
                .setOnClickListener {
                    idNuevo=idNuevo+1
                    anadirEntrenador(
                        idNuevo,
                adaptador

            )
        }
        btn_eliminar.setOnClickListener {

            eliminarEntrenador(posicion,
            adaptador)

        }

        btn_Pokemon.setOnClickListener {
            irPokemon(Entrenador.companion_entrenador[posicion].id.toInt())


        }

    }

    fun irPokemon(posicion:Int) {
        val intentExplicito = Intent(
            this,
            CrearPokemon::class.java
        )
        intentExplicito.putExtra("idEntrenador",posicion)  //este putextra es para el primitivo entero
        Log.i("pokemonId", "Envio ${posicion}")

        // this.startActivity(intentExplicito)
        startActivity(intentExplicito)
    }


    fun anadirEntrenador(idNuevo :Int,
                           adaptador: ArrayAdapter<Entrenador>

    ){
        var activo = cb_nuevo_entrenador.isChecked()
            Log.i("activo?","Activo: ${activo}")
        Entrenador.companion_entrenador.add(
            Entrenador(idNuevo,etv_nuevo_nombre.text.toString(), etv_color.text.toString(),etv_nivel_entrenador_n.text.toString().toInt(),activo,etv_distancia_entrenador_n.text.toString().toDouble())
        )


        adaptador.notifyDataSetChanged()
    }


    fun editarEntrenador(
        idEditar :Int
        ,adaptador: ArrayAdapter<Entrenador>

    ){

        var check : Boolean
        if(cb_nuevo_entrenador.isChecked()){check = true} else{check=false}
        Log.i("editarActivo","posicion recibida ${idEditar}")

        Entrenador.companion_entrenador[idEditar].nombre=etv_nuevo_nombre.text.toString()
        Log.i("editarActivo","nombre ${etv_nuevo_nombre.text.toString()}")
        Log.i("editarActivo","nombre final ${Entrenador.companion_entrenador[idEditar].nombre}")

        Entrenador.companion_entrenador[idEditar].color=etv_color.text.toString()

        Entrenador.companion_entrenador[idEditar].nivel=etv_nivel_entrenador_n.text.toString().toInt()

        Entrenador.companion_entrenador[idEditar].activo=check
        Entrenador.companion_entrenador[idEditar].distancia=etv_distancia_entrenador_n.text.toString().toDouble()

        adaptador.notifyDataSetChanged()
    }



    fun eliminarEntrenador(id : Int,  adaptador: ArrayAdapter<Entrenador>
    ){
        Entrenador.companion_entrenador.removeAt(id)
        adaptador.notifyDataSetChanged()
    }


    fun llenardatos(position: Int){


        etv_nuevo_nombre.setText(Entrenador.companion_entrenador[position].nombre)
        etv_color.setText(Entrenador.companion_entrenador[position].color)
        etv_nivel_entrenador_n.setText(Entrenador.companion_entrenador[position].nivel.toString())
        if (Entrenador.companion_entrenador[position].activo) {
            cb_nuevo_entrenador.setChecked(true)
        }else{
            cb_nuevo_entrenador.setChecked(false)
        }
        etv_distancia_entrenador_n.setText(Entrenador.companion_entrenador[position].distancia.toString())


    }

}