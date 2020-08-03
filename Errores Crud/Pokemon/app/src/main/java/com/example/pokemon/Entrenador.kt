package com.example.pokemon

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Entrenador(
    var nombre: String,
    var color: String,
    var nivel: Int,
    var activo: Boolean,
    var distancia: Double
) {
    override fun toString(): String {
        return "${this.nombre} ${this.color} ${this.nivel} ${this.activo} ${this.distancia}"
    }
}