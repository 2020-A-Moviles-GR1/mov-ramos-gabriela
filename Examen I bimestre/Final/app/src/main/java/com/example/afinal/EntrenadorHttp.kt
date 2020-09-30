package com.example.afinal

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

 @SuppressLint("ParcelCreator")
 class EntrenadorHttp@JvmOverloads constructor (
     var id: Int,
     var createdAt: Long,
     var updatedAt: Long,
     var nombre: String,
     var color: String,
     var nivel: Int,
     var releaseDate: LocalDate,

     var activo: Boolean,
     var pokemons:ArrayList<PokemonHttp>
){ override fun toString(): String {
     return "Nombre: "+ nombre + "\nID: "+id;
 }
 }
@Target(AnnotationTarget.FIELD)
annotation class KlaxonDate