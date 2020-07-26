package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_entrenador.*
import kotlinx.android.synthetic.main.activity_entrenador.btn_crear_entrenador

class EntrenadorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_entrenador)
        var nombre= " "
        var color =""
        var nivel= 0
        var activo = true
        var d_camina = 0.0

        val listaEntrenadores: ArrayList<Entrenador> = arrayListOf<Entrenador>()
        val listaPokemones: ArrayList<Int> = arrayListOf(1,2,3,4,4)

        listaEntrenadores.add(Entrenador("Adrian", "Rojo", 10, true, 1.23))


        val adaptador: ArrayAdapter<Entrenador> = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_expandable_list_item_1, // Nombre Layout
            listaEntrenadores // Lista
        )
        lv_Entrenadores.adapter = adaptador

        lv_Entrenadores.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("list-view", "Nivel ${position}")

        }

        btn_crear_entrenador.setOnClickListener {
            anadirEntrenador(
                adaptador,
                listaEntrenadores
            )
            var actividad: Int = rd_activo.checkedRadioButtonId
            print(actividad)
            Log.i( "Atividad","ACTIVO:  ${actividad}");
        }

        }

        fun irCrearEntrenador(){
            val intentExplicito = Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
                this,                       //mandamos el this
                CrearEntrenadorActivity::class.java     //mandamos la actividad a donde queremos que se vaya
            )
            // this.startActivity(intentExplicito)
            startActivity(intentExplicito) //para ejecutar necesitamos este metodo que esta dentro de la clase

        }

    fun anadirEntrenador(
        adaptador: ArrayAdapter<Entrenador>,
        listaEntrenadores: ArrayList<Entrenador>,
        nombre: String = etv_nombre.text.toString(),
        color: String = etv_color.text.toString()
        ,
        nivelTomado: String = etv_nivel.text.toString(),
        nivel: Int = nivelTomado.toInt(),
        actividad: Int = rd_activo.checkedRadioButtonId,

        activo: Boolean = if (actividad != 2131230896) false else true

        ,d_caminada: Double = (etv_distancia_cami.text.toString()).toDouble()





    ){
        listaEntrenadores.add(

            Entrenador("${nombre}", "${color}", nivel, activo,d_caminada)
        )

        adaptador.notifyDataSetChanged()
    }



}