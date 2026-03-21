/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.dao;

import sistemagestiontikets.model.Pasajero;
import sistemagestiontikets.model.PasajeroRegular;
import sistemagestiontikets.model.PasajeroEstudiante;
import sistemagestiontikets.model.PasajeroAdultoMayor;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PasajeroDAO {

    private static final String ARCHIVO = "pasajeros.txt";

    public void guardarPasajero(Pasajero pasajero) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            String tipo = pasajero.getClass().getSimpleName();
            bw.write(tipo + ";" +
                     pasajero.getCedula() + ";" +
                     pasajero.getNombre());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar pasajero: " + e.getMessage());
        }
    }

    public List<Pasajero> cargarPasajeros() {
        List<Pasajero> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                switch (datos[0]) {
                    case "PasajeroRegular":
                        lista.add(new PasajeroRegular(datos[1], datos[2]));
                        break;
                    case "PasajeroEstudiante":
                        lista.add(new PasajeroEstudiante(datos[1], datos[2]));
                        break;
                    case "PasajeroAdultoMayor":
                        lista.add(new PasajeroAdultoMayor(datos[1], datos[2]));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar pasajeros: " + e.getMessage());
        }
        return lista;
    }
}
