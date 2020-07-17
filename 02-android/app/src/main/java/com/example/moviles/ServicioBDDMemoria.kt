package com.example.moviles

class ServicioBDDMemoria{
    companion object{  //solo existe uno por clase
        var numero = 0
        fun anadirNumero(){
            this.numero = this.numero +1

        }
    }

}