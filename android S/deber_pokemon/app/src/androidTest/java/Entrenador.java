import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Entrenador {
    //Gson gson = new Gson();
    public int id;
    public String name;
    public double height;
    public double weight;

    @Override
    public String toString() {
        return "[ id: " + String.valueOf(id) + ", name: " + name + ", height: " + String.valueOf(height) + ", weight: " + String.valueOf(weight) + " ]";
    }
    Gson gson = new Gson();
    String fichero = "";



    public static void readJson(){
        String fichero = "";

        try (BufferedReader br = new BufferedReader(new FileReader("entrenadores.json"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}


