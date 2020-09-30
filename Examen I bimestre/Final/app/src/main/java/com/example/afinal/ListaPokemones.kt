package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_lista_entrenadores.*
import kotlinx.android.synthetic.main.activity_lista_pokemones.*

class ListaPokemones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pokemones)
        val lista_pokemones= BddService.listaAcordes



        val adaptador= ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            lista_pokemones)

        lv_lista_pokemones.adapter=adaptador

        lv_lista_pokemones.onItemClickListener= AdapterView.OnItemClickListener{
                parent,view,position,id ->
            Log.i("ENVIOOOOO","Posicion: ${lista_pokemones[position].nombre} y nombre: ${lista_pokemones[position].nombre}")
         //   irEntrenador(lista_pokemones[position].id);
            irModificar(lista_pokemones[position].id)
        }

        btn_ir_entrena.setOnClickListener {
            this.startActivity(Intent(this,MainActivity::class.java))
        }


    }
    init {
        BddService.getAcordes()
    }


    fun irEntrenador( posicion:Int){
        val intentExplicito= Intent(this, ModificarPokemonActivity::class.java)
        intentExplicito.putExtra("posicion_pokemon",posicion)
        this.startActivity(intentExplicito)
    }
    fun irModificar( posicion: Int){
        val intentExplicito= Intent(this, ModificarPokemonActivity::class.java)
       intentExplicito.putExtra("posicion_pokemon",posicion)
       // intentExplicito.putExtra("nombre_pokemon",nombre)

        this.startActivity(intentExplicito)
    }

}