import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.io.println as println1

fun main(args:Array<String>) {
    val entrenadores = ArrayList<Entrenador>()
    val pokemones = ArrayList<Pokemon>()
  leer_entrenadores(entrenadores)
    leer_pokemon(pokemones)
   // buscar_entrenador(entrenadores)
    print("pase")
   //
    while (true)
        menuPrincipal(entrenadores,pokemones)

}

fun leer_pokemon(pokemones: ArrayList<Pokemon>) {
    File("pokemones.txt").forEachLine{
        var a = it.split("|")
        pokemones.add(Pokemon(a[0],a[1].toInt(),a[2].toFloat(),a[3].toBoolean(),a[4]))
    }
}

fun leer_entrenadores(entrenadores: ArrayList<Entrenador>) {

        File("entrenadores.txt").forEachLine {
            var a = it.split("|")
            entrenadores.add(Entrenador(a[0],a[1],a[2].toInt(),a[3].toBoolean(),a[4].toFloat()))

    }
}

fun menuPrincipal(entrenadores: ArrayList<Entrenador>, pokemones: ArrayList<Pokemon>) {
    var texto: String
    texto = "--------------------------------------" + "\n"
    texto += "-          ENTRENADORES            " + "\n"
    texto += "1. Ingresar un entrenador        " + "\n"
    texto += "2. Mostrar entrenadores  " + "\n"
    texto += "3. Modificar un entreandor             " + "\n"
    texto += "4. Eliminar un entrenador              " + "\n"
    texto += "5. Menu POKEMONES        " + "\n"
    texto += "6. Salir                           " + "\n"
    texto += "--------------------------------------" + "\n"
    println1(texto)
    var opcion: Int
    while (true) {
        try {
            opcion = readLine().toString().toInt()
            if (opcion > 6 || opcion < 1) {
                print("ingrese un valor correcto")
            } else {
                break
            }
        } catch (e: NumberFormatException) {
            print("Ingrese un valor valido")
        }
    }
    when (opcion) {
        1 -> agregar_entrenador(entrenadores)
        2 -> mostrar_entrenador(entrenadores)
        3 -> actualizar_entrenador(entrenadores)
        4 -> eliminar_entrenador(entrenadores, pokemones)
        5 -> {
            while (menu_pokemones(entrenadores, pokemones)) {
            }
        }
        6 -> {
            guarda_entrenador(entrenadores)
            guarda_pokemon(pokemones)
            System.exit(0)
        }
    }

}

fun menu_pokemones(entrenadores: ArrayList<Entrenador>, pokemones: ArrayList<Pokemon>): Boolean {
    var estado = true
    var texto: String
    texto = "--------------------------------------" + "\n"
    texto += "-       POKEMONES       -" + "\n"
    texto += "- Registro Pokemones   -" + "\n"
    texto += "- 1. Ingresar un Pokemon     -" + "\n"
    texto += "- 2. Mostrar Pokemones  -" + "\n"
    texto += "- 3. Modificar un Pokemon           -" + "\n"
    texto += "- 4. Eliminar un Pokemon             -" + "\n"
    texto += "--------------------------------------" + "\n"
    println1(texto)
    var opcion: Int
    while (true) {
        try {
            opcion = readLine().toString().toInt()
            if (opcion > 5 || opcion < 1) {
                print("ingrese un valor correcto")
            } else {
                break
            }
        } catch (e: NumberFormatException) {
            print("Ingrese un valor valido")
        }
    }
    when (opcion) {
        1 -> agregar_pokemon(entrenadores, pokemones)
        2 -> mostrar_pokemon(pokemones)
        3 -> actualizar_pokemon(entrenadores, pokemones)
        4 -> eliminar_pokemon(pokemones)
        5 -> estado = false
    }
    return estado
}

fun eliminar_pokemon(pokemones: ArrayList<Pokemon>) {
    print("Ingresen el nombre del Pokemon a eliminar: ")
    val nombre_pokemon = readLine().toString()
    val pokemon_borrar = pokemones.removeIf { iteracion: Pokemon ->
        iteracion.nombre_pokemon == nombre_pokemon
    }
    if (!pokemon_borrar) println1("No existe el pokemon")
}

fun actualizar_pokemon(entrenadores: ArrayList<Entrenador>, pokemones: ArrayList<Pokemon>) {
    print("Ingresen el nombre del pokemon a actualizar: ")
    var nombre = readLine().toString()
    var modelo = pokemones.removeIf { iteracion: Pokemon ->
        iteracion.nombre_pokemon == nombre
    }
    if (modelo) {
        agregar_pokemon(entrenadores, pokemones)
    } else {
        println1("No existe el pokemon")
    }
}

fun mostrar_pokemon(pokemones: ArrayList<Pokemon>) {
    println1("Ingrese el nombre del pokemon o presione enter para mostrar todos los pokemones: ")
    val nombre_pokemon = readLine().toString()
    if (nombre_pokemon == "") {
        for (i in pokemones) {
            println1(i)
        }
    } else {
        val pokemon = pokemones.filter { iteracion: Pokemon ->
            iteracion.tipo_pokemon == nombre_pokemon
        }
        for (i in pokemon) {
            println1(i)
        }
    }

}

fun agregar_pokemon(entrenadores: ArrayList<Entrenador>, pokemones: ArrayList<Pokemon>) {

    print("Ingrese el nombre del entrenador: ")
    var nombre_entrenador = readLine().toString()
    var entrenador = entrenadores.find { it: Entrenador ->
        it.nombre_entrenador == nombre_entrenador
    }
    if (entrenador != null) {

        print("Ingresen el nombre del pokemon: ")
        val nombre = readLine().toString()
        print("Ingresen el nivel del pokemon: ")
        val nivel_pokemon = readLine().toString().toInt()
        print("Ingresen la fuerza del pokemon ")
        val fuerza = readLine().toString().toFloat()
        print("Pokemon activo? true o false ")
        val pokemon_activo = readLine().toString().toBoolean()
        pokemones.add(Pokemon(nombre, nivel_pokemon, fuerza, pokemon_activo, nombre_entrenador))
    } else {
        println1("No existe el entrenador")
    }
}

class Entrenador(
    var nombre_entrenador:String,
    var color_entrenador:String,
    var nivel_entrenador:Int,
    var entrenador_activo:Boolean,
    var distancia_caminada:Float
){
    override fun toString(): String {
        return "Entrenador(Nombre Entrenador='$nombre_entrenador', Color='$color_entrenador', Nivel Entrenador='$nivel_entrenador', Entrenador Activo=$entrenador_activo, distancia=$distancia_caminada.)"
    }
}
class Pokemon(
        var nombre_pokemon:String,
        var nivel_pokemon: Int,
        var fuerza_pokemon:Float,
        var pokemon_activo:Boolean,//estado
        var tipo_pokemon:String
){
    override fun toString(): String {
        return "Pokemon(Nombre Pokemon='$nombre_pokemon', Nivel Pokemon=$nivel_pokemon, Fuerza=$fuerza_pokemon, Pokemon Activo=$pokemon_activo, tipo='$tipo_pokemon')"
    }
}

fun agregar_entrenador(entrenador : ArrayList<Entrenador>){
    print("Ingresen el nombre del Entrenador: ")
    val nombre = readLine().toString()
    print("Ingresen el color: ")
    val color = readLine().toString()
    print("Ingresen el nivel: ")
    val nivel = readLine().toString().toInt()
    print("Entrenador activo? (true o false) ")
    val activo = readLine()!!.toBoolean()
    print("Ingresen la distancia caminada: ")
    val distancia = readLine().toString().toFloat()
    entrenador.add( Entrenador(nombre,color,nivel,activo,distancia))
}
fun eliminar_pokemon_entrenador(entrenador: String, pokemon: ArrayList<Pokemon>) {
    pokemon.removeIf { iteracion: Pokemon ->
        iteracion.tipo_pokemon == entrenador
    }

}

fun eliminar_entrenador(entrenador : ArrayList<Entrenador>, pokemon: ArrayList<Pokemon>){
    print("Ingresen el Nombre del Entrenador a Eliminar: ")
    var nombre = readLine().toString()
    var esta=entrenador.removeIf { iteracion : Entrenador ->
        iteracion.nombre_entrenador==nombre
    }
    if(esta){
        eliminar_pokemon_entrenador(nombre,pokemon)
    }else{
        print("No existe el Entrenador")
    }

}
//
fun mostrar_entrenador(entrenador : ArrayList<Entrenador>){
    for (i in entrenador) {
        println1(i)
    }
}
fun actualizar_entrenador(entrenador : ArrayList<Entrenador>){
    print("Ingresen el nombre del entrenador a actualizar: ")
    val nombre = readLine().toString()
    val esta = entrenador.removeIf { iteracion : Entrenador ->
        iteracion.nombre_entrenador==nombre
    }
    if(esta){
        print("Ingresen el color: ")
        val color = readLine().toString()
        print("Ingresen nivel del Entrenador: ")
        val nivel = readLine().toString().toInt()
        print("Entrenador Activo? true o false ")
        val activo = readLine()!!.toBoolean()
        print("Distancia caminada:  ")
        val distancia = readLine().toString().toFloat()
        entrenador.add( Entrenador(nombre,color,nivel,activo,distancia))
    }else{
        println1("No existe el entrenador")
    }
}

fun guarda_entrenador(entrenador : ArrayList<Entrenador>){
    var texto:String=""
    for (i in entrenador) {
        texto += i.nombre_entrenador+"|"+i.color_entrenador+"|"+i.nivel_entrenador+"|"+i.entrenador_activo+"|"+i.distancia_caminada+"|"+"\n"
    }
    File("entrenadores.txt").appendText(texto)
}




fun guarda_pokemon(pokemon : ArrayList<Pokemon>){
    var texto:String=""
    for (i in pokemon) {
        texto += i.nombre_pokemon+"|"+i.nivel_pokemon+"|"+i.fuerza_pokemon+"|"+i.pokemon_activo+"|"+i.tipo_pokemon+"|"+"\n"
    }
    File("pokemones.txt").writeText(texto)
}
/*
fun leer_pokemon(pokemones : ArrayList<Pokemon>) {
    File("pokemones.txt").forEachLine {
        var a = it.split("|")
        pokemones.add(Pokemon(a[0], a[1]!!.toInt(), a[2]!!.toFloat(), a[3]!!.toBoolean(), a[4]))
    }

}*/


