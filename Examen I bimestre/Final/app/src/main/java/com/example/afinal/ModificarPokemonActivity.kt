package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.afinal.BddService.Companion.pokemon
import kotlinx.android.synthetic.main.activity_modificar_pokemon.*

class ModificarPokemonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_pokemon)
        val posicion_entrenador= intent.getIntExtra("posicon_entrenador", -1)
       var posicion= intent.getIntExtra("posicion_pokemon", -1)
        val nombrellega : String? = intent.getStringExtra("nombre_pokemon")
        val tipollega : String? = intent.getStringExtra("tipo_pokemon")
        val nivelllega : String? = intent.getStringExtra("nivel_pokemon")
        Log.i("Http-klaxonPOKEMON","posicion recibido ${posicion}")
        val Entrenador_dueno=BddService.obtenerEntrenador(posicion_entrenador)
        val pokemon_encontrado=BddService.buscarPokemon(nombrellega.toString())

        if(posicion>-1){
            var pokemon = BddService.obtenerPokemon(posicion)
            Log.i("ENVIOOOOO","LLEGA: ${pokemon}")

            etv_id_pokemon.setText(pokemon?.id.toString())
            etv_nombre_poke_mod.setText(pokemon?.nombre)
            etv_tipo_poke_mod.setText(pokemon?.tipo)
            et_nivel_pokemon_ed.setText(pokemon?.nivel)
            et_activo_poke_edi.setText(pokemon?.activo)
            etv_entrenador_due.setText(pokemon?.entrenador)
            etv_url_pokemon.setText(pokemon?.url)
            etv_imagen_pokemon.setText(pokemon?.imagen)
            etv_latitud.setText(pokemon?.latitud)
            etv_longitud.setText(pokemon?.longitud)
         //   etv_entrenador_due.setText(pokemon?.)

        }


     else{
            Toast.makeText(applicationContext,"No se encuentra pokemon", Toast.LENGTH_SHORT).show()
        }
        btn_modificar_pokemon.setOnClickListener {
            BddService.modificarPokemon(posicion,
                etv_nombre_poke_mod.text.toString(),
                etv_tipo_poke_mod.text.toString(),
                et_nivel_pokemon_ed.text.toString(),
                et_activo_poke_edi.text.toString(),

              //tv_nombre.text.toString()
                etv_entrenador_due.text.toString(),
                etv_latitud.text.toString(),
                etv_longitud.text.toString(),
                etv_url_pokemon.text.toString(),
                etv_imagen_pokemon.text.toString()


            )
            Toast.makeText(applicationContext,"POKEMON Modificada con Exito",Toast.LENGTH_SHORT).show()
            ir_activity_pokemones()
        }

        btn_crear_pokemon.setOnClickListener {
            BddService.crearPokemon(posicion,
                etv_nombre_poke_mod.text.toString(),
                etv_tipo_poke_mod.text.toString(),
                et_nivel_pokemon_ed.text.toString(),
                et_activo_poke_edi.text.toString(),

                //tv_nombre.text.toString()
                etv_entrenador_due.text.toString(),
                etv_latitud.text.toString(),
                etv_longitud.text.toString(),
                etv_url_pokemon.text.toString(),
                etv_nombre_poke_mod.text.toString()
            )
            Toast.makeText(applicationContext,"POKEMON Modificada con Exito",Toast.LENGTH_SHORT).show()
            ir_activity_pokemones()
        }


        btn_eliminar_pokemon.setOnClickListener {
          BddService.deletePokemon(posicion)
            val intentExplicito= Intent(this, ListaPokemones::class.java)
            this.startActivity(intentExplicito)

            Toast.makeText(applicationContext,"POKEMON eliminado con Exito",Toast.LENGTH_SHORT).show()
        }




        btn_mapa.setOnClickListener {
          //  irSoloMapa()
            irMapa(pokemon!!.nombre,"${pokemon!!.tipo}")


         //   irSoloMapa(pokemon!!.latitud, etv_longitud.text.toString())
            Log.i("MAPA", "ENVIO: ${pokemon!!.latitud}, ${ etv_longitud.text.toString()}")
        }
    }
    fun ir_list_pokemones(){
        val intentExplicito= Intent(this, ListaPokemones::class.java)
    //    intentExplicito.putExtra("index",posicion)
        this.startActivity(intentExplicito)
    }
    fun ir_activity_pokemones(){
        val intentExplicito= Intent(this, PokemonActivity::class.java)
        //    intentExplicito.putExtra("index",posicion)
        this.startActivity(intentExplicito)
    }

    fun irSoloMapa(
        latitud:String, longitud:String
    ){
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("latitud",latitud)
        intent.putExtra("longitud",longitud)
        this.startActivity(intent)

        this.startActivity(intent)
    }
    fun irMapa(nombre:String,tipo:String){
        val intent = Intent(this, MapsActivity::class.java)

        this.startActivity(intent)
    }
}