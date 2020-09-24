package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_lista_entrenadores.*

class ListaEntrenadoresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_entrenadores)


        val listaEntrenadores= BddService.listaEntrenadores


        val adaptador= ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaEntrenadores)

        lv_entrenadores.adapter=adaptador

        lv_entrenadores.onItemClickListener= AdapterView.OnItemClickListener{
                parent,view,position,id ->
            Log.i("list-view","Posicion ${listaEntrenadores[position]}")
            irEntrenador(position);
        }
        btn_lista_a_main.setOnClickListener {
            this.startActivity(Intent(this,MainActivity::class.java))
        }

        btn_crear_entrenador.setOnClickListener {
            this.startActivity(Intent(this,AgregarEntrenadorActivity::class.java))
        }





    }
    fun irEntrenador(posicion:Int){
        val intentExplicito= Intent(this, EntrenadorActivity::class.java)
        intentExplicito.putExtra("index",posicion)
        this.startActivity(intentExplicito)
    }




}