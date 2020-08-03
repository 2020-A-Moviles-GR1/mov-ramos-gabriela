package com.example.afinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_pokemon.*
import android.content.Intent
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast

class PokemonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        val posicion= intent.getIntExtra("index",-1)

        if(posicion>-1){
            var entrenador : Entrenador= BddService.obtener_entrenador(posicion)
            tv_cancion_y_autor.setText("Entrenador: ${entrenador.nombre} -Color: ${entrenador.color}")
            val chords=entrenador.listaPokemones.toString().split(",").toTypedArray()
            val adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,chords)
            lv_acordes.adapter=adaptador

            lv_acordes.onItemClickListener= AdapterView.OnItemClickListener{
                    parent,view,position,id ->
                Log.i("list-view","Posicion pokemon ${chords[position]}")
                val pokemon_encontrado=BddService.obtener_pokemon(position)
                Log.i("list-view","POKEMON ENCONTRADO ${pokemon_encontrado}")
                if(pokemon_encontrado!=null){
                    Log.i("pokemon-econtrado","$pokemon_encontrado")
                    //Modificar Pokemon

                    val intentExplicito= Intent(this, ModificarPokemonActivity::class.java)
                    intentExplicito.putExtra("posicon_entrenador", posicion)
                    intentExplicito.putExtra("posicion_pokemon", position)
                    intentExplicito.putExtra("nombre_pokemon", pokemon_encontrado.nombre)
                    intentExplicito.putExtra("tipo_pokemon", pokemon_encontrado.tipo)
                    intentExplicito.putExtra("nivel_pokemon", pokemon_encontrado.nivel)

                    Log.i("list-view","Posicion eviado ${chords[position]}")
                    Log.i("list-view","tipo_pokemon ${pokemon_encontrado.tipo}")
                  // Log.i("list-view","nivel_pokemon ${pokemon_encontrado.nivel}")
                    this.startActivity(intentExplicito)


                    // this.startActivity(Intent(this,ModificarPokemonActivity::class.java))

                    //   iv_chord.setImageResource(acorde_encontrado.imagen)

                }else{
                    Toast.makeText(applicationContext,"No se encuentra pokemon", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            this.startActivity(Intent(this,MainActivity::class.java))
        }


        btn_menu.setOnClickListener {
            this.startActivity(Intent(this,MainActivity::class.java))
        }
        btn_acordes_a_lista.setOnClickListener {
            this.startActivity(Intent(this,ListaEntrenadoresActivity::class.java))
        }



    }
}