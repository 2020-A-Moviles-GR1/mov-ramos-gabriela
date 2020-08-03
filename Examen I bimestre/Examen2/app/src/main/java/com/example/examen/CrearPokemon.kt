package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_crear_pokemon.*
import kotlinx.android.synthetic.main.activity_main.*

class CrearPokemon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_pokemon)
        var posicion=0
        var id_entrenador = intent.getIntExtra(
            "idEntrenador",
            -1
        )
        Log.i("pokemonId", "Recibo: ${id_entrenador}")
        var id_pokemon = 4
        Pokemon.companion_pokemon.add(Pokemon(0, 0, "Pikachu", "Electrico", 25, true))
        Pokemon.companion_pokemon.add(Pokemon(1, 1, "Charmander", "Fuego", 15, false))
        Pokemon.companion_pokemon.add(Pokemon(2, 2, "pikachu2", "Fuego", 15, false))
        Pokemon.companion_pokemon.add(Pokemon(1, 3, "Charmander2", "Fuego", 15, false))


        val adaptador: ArrayAdapter<Pokemon> = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Nombre Layout

            Pokemon.companion_pokemon

        )

        lv_pokemones.adapter = adaptador

 //Pokemon.companion_pokemon[ind(id_entrenador)]



            Log.i(
                "pokemonId",
                "Tama;o dis: ${Pokemon.companion_pokemon.size}"
            )

        lv_pokemones
            .onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
              posicion =position
            llenardatos(posicion)


        }

        btn_nuevo_pokemon.setOnClickListener {
            id_pokemon=id_pokemon+1
            anadirPokemon(
                id_entrenador,id_pokemon,
                adaptador

            )

        }

        btn_modificar_pokemon.setOnClickListener {
            editarPokemon(
                posicion
                ,adaptador

            )

        }
        btn_eliminar_pokemon.setOnClickListener {
            eliminarPokemon(
                posicion
                ,adaptador

            )

        }
    }

    fun llenardatos(position: Int) {


        etv_nombre_pokemon.setText(Pokemon.companion_pokemon[position].nombre)

        etv_tipo_pokemon.setText(Pokemon.companion_pokemon[position].tipo)
        etv_nivel.setText(Entrenador.companion_entrenador[position].nivel.toString())
        if (Pokemon.companion_pokemon[position].activo) {
            cb_pokemon_activo.setChecked(true)
        } else {
            cb_pokemon_activo.setChecked(false)
        }

    }



        fun anadirPokemon(
            id_entrenador: Int,id_pokemon: Int,
            adaptador: ArrayAdapter<Pokemon>

        ) {
            var activo = cb_pokemon_activo.isChecked()


                        Pokemon.companion_pokemon.add(Pokemon(
                            id_entrenador,
                            id_pokemon,
                            etv_nombre_pokemon.text.toString(),
                            etv_tipo_pokemon.text.toString(),
                            etv_nivel.text.toString().toInt(),
                            activo))


            Log.i("pokemonId", "pOKEMON PA CREAR: ${etv_nombre_pokemon.toString()}")

            adaptador.notifyDataSetChanged()
        }
    fun editarPokemon(
        idEditar :Int
        ,adaptador: ArrayAdapter<Pokemon>

    ){

        var check : Boolean
        if(cb_pokemon_activo.isChecked()){check = true} else{check=false}
        Log.i("editarPokemon","posicion recibida ${idEditar}")

        Pokemon.companion_pokemon[idEditar].nombre=etv_nombre_pokemon.text.toString()


        Pokemon.companion_pokemon[idEditar].tipo=etv_tipo_pokemon.text.toString()

        Pokemon.companion_pokemon[idEditar].nivel=etv_nivel.text.toString().toInt()

        Pokemon.companion_pokemon[idEditar].activo=check


        adaptador.notifyDataSetChanged()
    }

    fun eliminarPokemon(id : Int,  adaptador: ArrayAdapter<Pokemon>
    ){
        Pokemon.companion_pokemon.removeAt(id)
        adaptador.notifyDataSetChanged()
    }

    }

