package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar_entrenador.*

class AgregarEntrenadorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_entrenador)
        val posicion= intent.getIntExtra("index",-1)

        if(posicion>-1){
            //Me enviaron para modificar
            tv_agregar_modificar_c.text="Modificar Entrenador"
            btn_agregar_modificar.setText("MODIFICAR")
            var entrenador: Entrenador? = BddService.obtenerCancion(posicion)
            if (entrenador != null) {

                et_nombre.setText(entrenador.nombre)
                et_color_entrenador.setText(entrenador.color)
                et_nivel.setText(entrenador.nivel.toString())
                et_entre_activo.setText(entrenador .activo.toString())
                et_pokemones.setText(entrenador.pokemones)

            }
            btn_agregar_modificar.setOnClickListener {
                BddService.modificarCancion(posicion,
                    et_nombre.text.toString(),
                    et_color_entrenador.text.toString(),
                   et_nivel.text.toString(),
                   et_entre_activo.text.toString(),
                    et_pokemones.text.toString()
                )
                Toast.makeText(applicationContext,"Entrenador Modificada con Exito",Toast.LENGTH_SHORT).show()
                ir_entrenador(posicion)
            }

        }else{
            tv_agregar_modificar_c.text="Agregar Entrenador"
            btn_agregar_modificar.setText("AGREGAR")
            //Me enviaron para agregar
            btn_agregar_modificar.setOnClickListener {
                BddService.agregarCancion(
                    et_nombre.text.toString(),
                    et_color_entrenador.text.toString(),
                    et_nivel.text.toString(),
                    et_entre_activo.text.toString(),
                    et_pokemones.text.toString()
                )
                Toast.makeText(applicationContext,"Entrenador Agregado con Exito",Toast.LENGTH_SHORT).show()
                ir_lista_entrenadores()
            }
        }
        btn_agregar_a_lista.setOnClickListener {
            this.startActivity(Intent(this,ListaEntrenadoresActivity::class.java))
        }
        btn_agregar_a_main.setOnClickListener {
            this.startActivity(Intent(this,MainActivity::class.java))
        }
    }
    fun ir_entrenador(posicion:Int){
        val intentExplicito= Intent(this, EntrenadorActivity::class.java)
        intentExplicito.putExtra("index",posicion)
        this.startActivity(intentExplicito)
    }
    fun ir_lista_entrenadores(){
        val intentExplicito= Intent(this, ListaEntrenadoresActivity::class.java)
        this.startActivity(intentExplicito)
    }

}