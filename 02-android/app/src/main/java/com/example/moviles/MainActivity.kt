package com.example.moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_ciclo_vida
            .setOnClickListener { boton ->
                irCicloDeVida()
            }
        btn_list_view
            .setOnClickListener { boton ->
                irListView()
            }

       // Log.i("Activity", "OnCreate")

        btn_intent_respuesta
            .setOnClickListener {
                irAIntentConRespuesta()
            }

        btn_intent_implicito.setOnClickListener {
            enviarIntentConRespuesta()
        }


        btn_resp_propia.setOnClickListener {
            enviarIntentConRespuestaPropia()
        }

        btn_http.setOnClickListener {
            abrirActividadHttp()
        }
    }

    fun abrirActividadHttp() {
        val intentExplicito = Intent(
            this,
            HttpActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun enviarIntentConRespuestaPropia() {
        val intentExplicito = Intent(
            this,
            IntentEnviaParametros::class.java
        )
        startActivityForResult(intentExplicito, 305)
    }

    fun enviarIntentConRespuesta() {
        val intentConRespuesta = Intent(
            Intent.ACTION_PICK,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        // this.startActivityForResult(intent, codigoDeRespuesta)
        // 304 lo pusimos nosotros, no es ningun numero en especial
        startActivityForResult(intentConRespuesta, 304)

        }

    override fun onActivityResult(
        requestCode: Int, // Numero que enviamos
        resultCode: Int,
        data: Intent?
                    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            RESULT_OK -> {
                Log.i("resultado", "OK")
                when (requestCode) {
                    304 -> { // Contactos
                        val uri = data?.data
                        if (uri != null) {
                            val cursor = contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                            cursor?.moveToFirst()               //movemos el cursor
                            val indiceTelefono = cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(indiceTelefono!!)
                            cursor?.close()
                            Log.i("resultado", "Telefono: ${telefono}")
                        }
                    }

                    305 -> {
                        if (data!= null){
                            val nombre = data.getStringExtra("nombre")
                            val edad = data.getIntExtra("edad", 0)
                            Log.i("resultado", "Nombre: ${nombre} Edad: ${edad}")
                        }
                    }
                    }
                }
            RESULT_CANCELED -> {
                Log.i("resultado", "=(")
            }

        }
    }

    fun irAIntentConRespuesta(){
        val intentExplicito = Intent(
            this,
            IntentEnviaParametros::class.java
        )

        intentExplicito.putExtra("numero",2)  //este putextra es para el primitivo entero

        val adrian = Usuario(
            "Adrian",
            31,
            Date(),
            1.0
        )
        val cachetes = Mascota(
            "Cachetes",
            adrian
        )
        val arregloMascotas = arrayListOf<Mascota>(cachetes)

        intentExplicito.putExtra("cachetes", cachetes)
        intentExplicito.putExtra("arregloMascotas", arregloMascotas)


        startActivity(intentExplicito)

    }



    fun irListView() {
        val intentExplicito = Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
            this,                       //mandamos el this
            BListViewActivity::class.java     //mandamos la actividad a donde queremos que se vaya
        )
        // this.startActivity(intentExplicito)
        startActivity(intentExplicito) //para ejecutar necesitamos este metodo que esta dentro de la clase
    }

    fun irCicloDeVida() {
        val intentExplicito = Intent( //solo necesitamos escribir el nombre de la clase para instanciar un nuevo objecto
            this,                       //mandamos el this
           CicloVida::class.java     //mandamos la actividad a donde queremos que se vaya
        )
        // this.startActivity(intentExplicito)
        startActivity(intentExplicito) //para ejecutar necesitamos este metodo que esta dentro de la clase
    }



    }