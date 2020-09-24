package com.example.afinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_http.*

class HttpActivity : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.15:1337"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        btn_obtener
            .setOnClickListener {
                obtenerEntrenador()
            }

    }

    fun obtenerEntrenador(){
        val url = urlPrincipal + "/entrenador"
        url.httpGet().responseString { request, response, result ->
        when(result){
            is Result.Failure ->{
                val ex= result.getException()
                Log.i("http-klaxon", "error: ${ex.message}")
            }

            is Result.Success ->{
                val data= result.get()
                Log.i("http-klaxon", "data: ${data}")

            }
        }

        }

    }
}