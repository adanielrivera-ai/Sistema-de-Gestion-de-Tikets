/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private static final String ARCHIVO = "reservas.txt";

    public void guardarReserva(String lineaReserva) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(lineaReserva);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar reserva: " + e.getMessage());
        }
    }

    public List<String> listarReservas() {
        List<String> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar reservas: " + e.getMessage());
        }
        return lista;
    }
}

