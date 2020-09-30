package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar_entrenador.*
import kotlinx.android.synthetic.main.activity_entrenador.*
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
        val Entrenador_dueno=BddService.obtenerCancion(posicion_entrenador)
        val pokemon_encontrado=BddService.buscarAcorde(nombrellega.toString())

        if(posicion>-1){
            var pokemon = BddService.obtenerPokemon(posicion)
            Log.i("ENVIOOOOO","LLEGA: ${pokemon}")

            etv_id_pokemon.setText(pokemon?.id.toString())
            etv_nombre_poke_mod.setText(pokemon?.nombre)
            etv_tipo_poke_mod.setText(pokemon?.tipo)
            et_nivel_pokemon_ed.setText(pokemon?.nivel)
            et_activo_poke_edi.setText(pokemon?.activo)
            etv_entrenador_due.setText(pokemon?.entrenador)
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
                etv_entrenador_due.text.toString()
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


        /*
        if(posicion>-1){
            //Me enviaron para modificar
            tv_agregar_modificar_c.text="Modificar Pokemon"
            btn_modificar_pokemon.setText("MODIFICAR")
            var entrenador:Entrenador= BddService.obtener_entrenador(posicion)
            et_nombre.setText(entrenador.nombre)
            et_autor_genero.setText(entrenador.color)
            et_nivel.setText(entrenador.genero)
            et_pokemos.setText(entrenador.acordes)
            btn_modificar_pokemon.setOnClickListener {
                BddService.modificar_pokemon(posicion, Pokemon(
                    et_nombre.text.toString(),
                    et_autor_genero.text.toString()


                ))
                Toast.makeText(applicationContext,"Entrenador Modificada con Exito", Toast.LENGTH_SHORT).show()
            //    ir_entrenador(posicion)
            }

        }else {
            tv_agregar_modificar_c.text = "Agregar Entrenador"
            btn_modificar_pokemon.setText("AGREGAR")
            //Me enviaron para agregar
            btn_modificar_pokemon.setOnClickListener {
                BddService.agregar_entrenador(
                    Entrenador(
                        et_nombre.text.toString(),
                        et_autor_genero.text.toString(),
                        et_nivel.text.toString(),
                        et_pokemos.text.toString()
                    )
                )
                Toast.makeText(
                    applicationContext,
                    "Entrenador Agregado con Exito",
                    Toast.LENGTH_SHORT
                ).show()
                //     ir_lista_entrenadores()
            }

        }
        */

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
}