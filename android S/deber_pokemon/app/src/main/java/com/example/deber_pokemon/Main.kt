package com.example.deber_pokemon

import java.io.File
import kotlin.collections.ArrayList

fun main(args:Array<String>) {
    val entrenador = ArrayList<Entrenador>()
    val pokemones = ArrayList<Pokemon>()
    print("aqui")
    leerEntrenadores(entrenador)
    leerPokemones(pokemones)
    while (true)
        menuPrincipal(entrenador,pokemones)

}
class Entrenador(
    var nombre:String,
    var pais_origen:String,
    var anio_creacion:Int,
    var sucursal_local:Boolean,
    var valor:Float
){
    override fun toString(): String {
        return "Entrenador(Nombre='$nombre', Pais Origen='$pais_origen', Año Creacion='$anio_creacion', Sucursal Local=$sucursal_local, Valor=$valor.)"
    }
}
class Pokemon(
    var nombre:String,
    var anio_lanzamiento:Int,
    var precio:Float,
    var disponible:Boolean,//estado
    var marca:String
){
    override fun toString(): String {
        return "Modelo(Nombre='$nombre', Año Lanzamiento=$anio_lanzamiento, Precio=$precio, Disponible=$disponible, Marca='$marca')"
    }
}

fun agregarEntrenador(entrenadores : ArrayList<Entrenador>){
    print("Ingresen el nombre del entrenador: ")
    val nombre = readLine().toString()
    print("Ingresen el pais de origen de la Marca: ")
    val pais = readLine().toString()
    print("Ingresen el Año de creacion de la Marca(2000): ")
    val anio = readLine().toString().toInt()
    print("Ingresen true o false si existe sucurla en su Pais: ")
    val sucursal_local = readLine()!!.toBoolean()
    print("Ingresen el valor de la empresa(100000): ")
    val valor = readLine().toString().toFloat()
    entrenadores.add( Entrenador(nombre,pais,anio,sucursal_local,valor))
}

fun eliminarEntrenador(entrenadores : ArrayList<Entrenador>, pokemones: ArrayList<Pokemon>){
    print("Ingresen el Nombre de la Marca a Eliminar: ")
    var nombre = readLine().toString()
    var esta=entrenadores.removeIf { iteracion : Entrenador ->
        iteracion.nombre==nombre
    }
    if(esta){
        eliminarModeloMarca(nombre,pokemones)
    }else{
        print("No existe la Marca")
    }

}

fun mostrarEntrenadores(entrenadores : ArrayList<Entrenador>){
    for (i in entrenadores) {
        println(i)
    }
}
fun actualizarEntrenadores(entrenadores : ArrayList<Entrenador>){
    print("Ingresen el nombre del entrenador a actualizar: ")
    val nombre = readLine().toString()
    val esta = entrenadores.removeIf { iteracion : Entrenador ->
        iteracion.nombre==nombre
    }
    if(esta){
        print("Ingresen el pais de origen de la Marca: ")
        val pais = readLine().toString()
        print("Ingresen el Año de creacion de la Marca(2000): ")
        val anio = readLine().toString().toInt()
        print("Ingresen true o false si existe sucurla en su Pais: ")
        val sucursal_local = readLine()!!.toBoolean()
        print("Ingresen el valor de la empresa(100000): ")
        val valor = readLine().toString().toFloat()
        entrenadores.add( Entrenador(nombre,pais,anio,sucursal_local,valor))
    }else{
        println("No existe el entrenador")
    }
}

fun guardaEntrenadores(entrenadores : ArrayList<Entrenador>){
    var texto:String=""
    for (i in entrenadores) {
        texto += i.nombre+"|"+i.pais_origen+"|"+i.anio_creacion+"|"+i.sucursal_local+"|"+i.valor+"|"+"\n"
    }
    File("marcas.txt").writeText(texto)
}
fun leerEntrenadores(entrenador : ArrayList<Entrenador>){
    File("marcas.txt").forEachLine {
        var a = it.split("|")
        entrenador.add(Entrenador(a[0],a[1],a[2].toInt(),a[3]!!.toBoolean(),a[4]!!.toFloat()))
    }
}

fun guardaPokemones(pokemones : ArrayList<Pokemon>){
    var texto:String=""
    for (i in pokemones) {
        texto += i.nombre+"|"+i.anio_lanzamiento+"|"+i.precio+"|"+i.disponible+"|"+i.marca+"|"+"\n"
    }
    File("modelos.txt").writeText(texto)
}
fun leerPokemones(pokemones : ArrayList<Pokemon>){
    File("modelos.txt").forEachLine {
        var a = it.split("|")
        pokemones.add(Pokemon(a[0],a[1]!!.toInt(),a[2]!!.toFloat(),a[3]!!.toBoolean(),a[4]))
    }
}

fun agregarPokemones(entrenadores: ArrayList<Entrenador>, pokemones : ArrayList<Pokemon>){
    print("Ingrese el nombre del entrenador: ")
    val entrenador = readLine().toString()
    val esta = entrenadores.find { it : Entrenador ->
        it.nombre==entrenador
    }
    if(esta != null){
        print("Ingresen el nombre del Modelo: ")
        val nombre = readLine().toString()
        print("Ingresen el año de lanzamiento del Modelo: ")
        val anio = readLine().toString().toInt()
        print("Ingresen el Precio del Modelo: ")
        val precio = readLine().toString().toFloat()
        print("Ingresen true o false si hay disponibilidad: ")
        val disponible = readLine().toString().toBoolean()
        pokemones.add( Pokemon(nombre,anio,precio,disponible,entrenador))
    }else{
        println("No existe el entrenador")
    }
}

fun mostrarPokemones(pokemones : ArrayList<Pokemon>){
    println("Ingrese el nombre de la Marca o enter para mostrar todos lo Modelos: ")
    val entrenador = readLine().toString()
    if(entrenador==""){
        for (i in pokemones) {
            println(i)
        }
    }else{
            val modelo = pokemones.filter { iteracion : Pokemon ->
            iteracion.marca==entrenador
        }
        for (i in modelo) {
            println(i)
        }
    }
}
fun actualizarPokemones(entrenadores : ArrayList<Entrenador>,pokemones : ArrayList<Pokemon>){
    print("Ingresen el nombre del Modelo a actualizar: ")
    var nombre = readLine().toString()
    var modelo=pokemones.removeIf { iteracion : Pokemon ->
        iteracion.nombre==nombre
    }
    if(modelo){
        agregarPokemones(entrenadores,pokemones)
    }else{
        println("No existe el Modelo")
    }
}
fun eliminarPokemones(pokemones : ArrayList<Pokemon>){
    print("Ingresen el nombre del Modelo a eliminar: ")
    val nombre = readLine().toString()
    val resultado=pokemones.removeIf { iteracion : Pokemon ->
        iteracion.nombre==nombre
    }
    if (!resultado) println("No existe el Modelo")
}

fun eliminarModeloMarca(entrenador: String,pokemones: ArrayList<Pokemon>){
    pokemones.removeIf { iteracion : Pokemon ->
        iteracion.marca==entrenador }
}

fun menuPrincipal(entrenador : ArrayList<Entrenador>, pokemones : ArrayList<Pokemon>){
    var texto:String
    texto ="--------------------------------------"+"\n"
    texto+="-          Menu Principal            -"+"\n"
    texto+="- Registro de Marcas de Celulare     -"+"\n"
    texto+="- 1. Ingresar una nueva Marca        -"+"\n"
    texto+="- 2. Mostrar las Marcas registradas  -"+"\n"
    texto+="- 3. Modificar una Marca             -"+"\n"
    texto+="- 4. Eliminar una Marca              -"+"\n"
    texto+="- 5. Menu pokemones de Celulare        -"+"\n"
    texto+="- 6. Salir                           -"+"\n"
    texto+="--------------------------------------"+"\n"
    println(texto)
    var opcion:Int
    while(true){
        try {
            opcion = readLine().toString()!!.toInt()
            if(opcion>6 || opcion<1){
                print("ingrese un valor correcto")
            }else{
                break
            }
        }catch (e: NumberFormatException){
            print("Ingrese un valor valido")
        }
    }
    when(opcion){
        1-> agregarEntrenador(entrenador)
        2-> mostrarEntrenadores(entrenador)
        3-> actualizarEntrenadores(entrenador)
        4-> eliminarEntrenador(entrenador,pokemones)
        5-> {
            while (menuPokemones(entrenador, pokemones)){ }
        }
        6-> {
            guardaEntrenadores(entrenador)
            guardaPokemones(pokemones)
            System.exit(0)
        }
    }
}
fun menuPokemones(entrenador : ArrayList<Entrenador>, pokemones : ArrayList<Pokemon>) : Boolean{
    var estado=true
    var texto:String
    texto ="--------------------------------------"+"\n"
    texto+="-       Menu pokemones Celulare        -"+"\n"
    texto+="- Registro de pokemones de Celulare    -"+"\n"
    texto+="- 1. Ingresar una nueva Modelo       -"+"\n"
    texto+="- 2. Mostrar las Modelo registradas  -"+"\n"
    texto+="- 3. Modificar una Modelo            -"+"\n"
    texto+="- 4. Eliminar una Modelo             -"+"\n"
    texto+="- 5. Menu Marcas de Celulare         -"+"\n"
    texto+="--------------------------------------"+"\n"
    println(texto)
    var opcion:Int
    while(true){
        try {
            opcion = readLine().toString()!!.toInt()
            if(opcion>5 || opcion<1){
                print("ingrese un valor correcto")
            }else{
                break
            }
        }catch (e: NumberFormatException){
            print("Ingrese un valor valido")
        }
    }
    when(opcion){
        1-> agregarPokemones(entrenador,pokemones)
        2-> mostrarPokemones(pokemones)
        3-> actualizarPokemones(entrenador,pokemones)
        4-> eliminarPokemones(pokemones)
        5-> estado= false
    }
    return estado
}