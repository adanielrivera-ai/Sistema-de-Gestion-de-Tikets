/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.dao;

import sistemagestiontikets.model.Conductor;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConductorDAO {

    private static final String ARCHIVO = "conductores.txt";

    public void guardarConductor(Conductor conductor) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(conductor.getCedula() + ";" +
                     conductor.getNombre() + ";" +
                     conductor.getNumLicencia() + ";" +
                     conductor.getCategoria());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar conductor: " + e.getMessage());
        }
    }

    public List<Conductor> cargarConductores() {
        List<Conductor> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                lista.add(new Conductor(datos[0], datos[1], datos[2], datos[3]));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar conductores: " + e.getMessage());
        }
        return lista;
    }
}
