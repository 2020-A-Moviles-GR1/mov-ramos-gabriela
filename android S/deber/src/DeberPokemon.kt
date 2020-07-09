import java.io.File
import kotlin.collections.ArrayList


fun main(args:Array<String>) {
    val platos = ArrayList<Platos>()
    leerPlatos(platos)
    menuPrincipal(platos)
}
public class Platos(
    var NombrePlato:String,
    var valor:Float
){
    override fun toString(): String {
        return "Platos(Nombre='$NombrePlato', Valor=$valor.)"
    }
}

fun agregarPlato(platos: ArrayList<Platos>){
    print("Agregue un plato: ")
    val platillo = readLine().toString()
    print("Ingrese el valor del platillo")
    val valorPlatillo = readLine().toString().toFloat()
    platos.add(Platos(platillo,valorPlatillo))
    guardaPlatos(platos)
    menuPrincipal(platos)

}

fun eliminarPlato(platos: ArrayList<Platos>){
    print("Ingrese el Plato que va quitar de la Carta: ")
    var platillo = readLine().toString()
    var estePlatos=platos.removeIf { iteracion : Platos ->iteracion.NombrePlato==platillo
    }
    if(estePlatos){
        eliminarPlato(platos)
    }else{
        print("El plato no Existe")
    }
    menuPrincipal(platos)
}

fun leerPlatos(platos: ArrayList<Platos>) {
    File("entrenador.txt").forEachLine { var p = it.split("|")
        platos.add(Platos(p[0].toString(),p[1].toFloat()))
    }
    menuPrincipal(platos)
}

fun mostrarPlatos(platos: ArrayList<Platos>){
    for(i in platos) {
        println(i)
    }
    menuPrincipal(platos)
}

fun actualizarPlatos(platos: ArrayList<Platos>){
    print("Ingrese el platillo que va actulizar: ")
    val platillo = readLine().toString()
    var esteplato=platos.removeIf { iteracion : Platos -> iteracion.NombrePlato==platillo
    }
    if(esteplato){
        print("Ingrese el plato del menu: ")
        val platillo = readLine().toString()

        print("Ingrese el valor del plato: ")
        val valorPlatillo = readLine().toString().toFloat()
        platos.add(Platos(platillo,valorPlatillo))
    }else{
        print("el plato no Existe")
    }
    menuPrincipal(platos)
}
fun guardaPlatos(platos: ArrayList<Platos>){
    var texto:String=""
    for (i in platos) {
        texto += i.NombrePlato + "|" + i.valor+"|"+"\n"
    }
    File("platos.txt").writeText(texto)
}


fun menuPrincipal(platos: ArrayList<Platos>){
    var menuPlatos:String
    menuPlatos="**************"+"\n"
    menuPlatos+="           COMEDOR DE DORITA         "+"\n"
    menuPlatos+="        Registro de Platos           "+"\n"
    menuPlatos+="  1. Agregar un Plato                "+"\n"
    menuPlatos+="  2. Listar Platos                   "+"\n"
    menuPlatos+="  3.Modificar Plato                  "+"\n"
    menuPlatos+="  4.Eliminar Plato del Menu          "+"\n"
    menuPlatos+="  5.Salir                            "+"\n"
    menuPlatos+="*************"+"\n"
    print(menuPlatos)
    var op:Int
    while (true){
        try {
            op = readLine().toString().toInt()
            if (op>5 || op<1){
                print("Ingrese las Opciones Mostradas en el Menu")
            }else{
                break
            }

        }catch (e:NumberFormatException){
            print("Ingrese un Numero mostrado en el Menu")
        }
    }

    when(op){
        1->agregarPlato(platos)
        2->mostrarPlatos(platos)
        3->actualizarPlatos(platos)
        4->eliminarPlato(platos)
        5->{System.exit(0)}
    }

}