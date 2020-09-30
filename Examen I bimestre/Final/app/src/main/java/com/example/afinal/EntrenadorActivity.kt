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
            var cancion: Entrenador? = BddService.obtenerCancion(posicion)
            Log.i("ID-http","${posicion}")
            if (cancion != null) {
                tv_nombre.text=cancion.nombre
                tv_color.text=cancion.color;
                tv_nivel.text= cancion.nivel.toString();
                tv_activo_entre.text=cancion.activo.toString();
                tv_pokemones.text=cancion.pokemones
            };

            btn_eliminar.setOnClickListener {
            //    BddService.eliminar_entrendor(entrenador)
                Toast.makeText(applicationContext,"Entrenador Eliminado", Toast.LENGTH_SHORT).show()
                ir_lista_entrenadores()
            }
            btn_modificar.setOnClickListener {
                var pokemones: Entrenador? = BddService.obtenerCancion(posicion)

                ir_agregar_entrenador(posicion);
            }
            btn_a_acordeslist.setOnClickListener {
                ir_pokemon_entrenador(posicion)
            }

        }else{
            ir_lista_entrenadores()
        }
       // btn_cancion_a_lista.setOnClickListener {
           // ir_lista_entrenadores()
       // }
        btn_cancion_a_main.setOnClickListener {
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