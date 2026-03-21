/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.dao;

import sistemagestiontikets.model.Ticket;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Dev 2. DAO para persistencia de tickets en archivo tickets.txt
 * @author JAVIER FERNANDEZ
 */
public class TicketDAO {

    private static final String ARCHIVO = "tickets.txt";

    public void guardarTicket(Ticket t) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(t.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Dev 2. esto para: Error al guardar ticket: " + e.getMessage());
        }
    }

    public List<Ticket> cargarTickets() {
        List<Ticket> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");
                Ticket t = new Ticket(
                    d[0],                        // Dev 2. esto para: cedulaPasajero
                    d[1],                        // Dev 2. esto para: nombrePasajero
                    d[2],                        // Dev 2. esto para: placaVehiculo
                    d[3],                        // Dev 2. esto para: origen
                    d[4],                        // Dev 2. esto para: destino
                    LocalDate.parse(d[5]),       // Dev 2. esto para: fechaCompra
                    Double.parseDouble(d[6])     // Dev 2. esto para: valorFinal
                );
                lista.add(t);
            }
        } catch (IOException e) {
            System.out.println("Dev 2. esto para: Error al cargar tickets: " + e.getMessage());
        }
        return lista;
    }
}
