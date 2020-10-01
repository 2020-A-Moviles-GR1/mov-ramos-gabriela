package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_entrenador.*

class EntrenadorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenador)
        val posicion= intent.getIntExtra("index",-1)

        if(posicion>-1){
            var entrenador: Entrenador? = BddService.obtenerEntrenador(posicion)
            Log.i("ID-http","${posicion}")
            if (entrenador != null) {
                tv_nombre.text=entrenador.nombre
                tv_color.text=entrenador.color;
                tv_nivel.text= entrenador.nivel.toString();
                tv_activo_entre.text=entrenador.activo.toString();
                tv_pokemones.text=entrenador.pokemones
            };

            btn_eliminar.setOnClickListener {
               BddService.elimarEntrenador(entrenador!!.id)
                Toast.makeText(applicationContext,"Entrenador Eliminado", Toast.LENGTH_SHORT).show()
                ir_lista_entrenadores()
            }
            btn_modificar.setOnClickListener {
                var pokemones: Entrenador? = BddService.obtenerEntrenador(posicion)

                ir_agregar_entrenador(posicion);
            }
            btn_pokemones.setOnClickListener {
                ir_pokemon_entrenador(posicion)
            }

        }else{
            ir_lista_entrenadores()
        }
       // btn_cancion_a_lista.setOnClickListener {
           // ir_lista_entrenadores()
       // }
        btn_entre_a_main.setOnClickListener {
            this.startActivity(Intent(this,MainActivity::class.java))
        }


    }
    fun ir_lista_entrenadores(){
        val intentExplicito= Intent(this, ListaEntrenadoresActivity::class.java)
        this.startActivity(intentExplicito)
    }
    fun ir_agregar_entrenador(posicion:Int){
        val intentExplicito= Intent(this, AgregarEntrenadorActivity::class.java)
        intentExplicito.putExtra("index",posicion)
        this.startActivity(intentExplicito)
    }
    fun ir_pokemon_entrenador(posicion:Int){
        val intentExplicito= Intent(this, ListaPokemones::class.java)
        intentExplicito.putExtra("index",posicion   )
        this.startActivity(intentExplicito)
    }
}