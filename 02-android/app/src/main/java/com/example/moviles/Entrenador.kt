package com.example.moviles

import android.os.Parcel
import android.os.Parcelable
import java.util.*
import android.R.attr.name
import android.R.id




class Entrenador(
    var nombre: String,
    var apellido: String
) {
    override fun toString(): String {
        return "${this.nombre} ${this.apellido}"
    }
}