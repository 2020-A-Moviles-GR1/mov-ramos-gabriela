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
        val lista_pokemones= BddService.listaPokemones


        val adaptador= ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            lista_pokemones)

        lv_lista_pokemones.adapter=adaptador

        lv_lista_pokemones.onItemClickListener= AdapterView.OnItemClickListener{
                parent,view,position,id ->
            Log.i("list-view","Posicion ${lista_pokemones[position]}")
           // irEntrenador(position);
        }

        btn_ir_entrena.setOnClickListener {
            irEntrenador()
        }






    }
    fun irEntrenador(){
        val intentExplicito= Intent(this, EntrenadorActivity::class.java)
      //  intentExplicito.putExtra("index",posicion)
        this.startActivity(intentExplicito)
    }


}