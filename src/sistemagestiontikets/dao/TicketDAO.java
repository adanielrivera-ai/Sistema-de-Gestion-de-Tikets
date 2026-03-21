/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    private static final String ARCHIVO = "tickets.txt";

    public void guardarTicket(String lineaTicket) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(lineaTicket);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar ticket: " + e.getMessage());
        }
    }

    public List<String> listarTickets() {
        List<String> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar tickets: " + e.getMessage());
        }
        return lista;
    }
}
