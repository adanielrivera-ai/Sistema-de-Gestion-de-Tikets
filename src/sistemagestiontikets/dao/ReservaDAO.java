/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.dao;

import sistemagestiontikets.model.Reserva;
import sistemagestiontikets.model.Pasajero;
import sistemagestiontikets.model.PasajeroRegular;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Dev 2. esto para: DAO para persistencia de reservas en archivo reservas.txt
 * @author JAVIER FERNANDEZ
 */
public class ReservaDAO {

    private static final String ARCHIVO = "reservas.txt";

    public void guardarReserva(Reserva r) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(r.getCodigo() + ";" +
                     r.getPasajero().getCedula() + ";" +
                     r.getVehiculo().getPlaca() + ";" +
                     r.getFechaCreacion() + ";" +
                     r.getFechaViaje() + ";" +
                     r.getEstado());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar reserva: " + e.getMessage());
        }
    }

    public List<Reserva> cargarReservas() {
        List<Reserva> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");
                Pasajero pasajero = new PasajeroRegular(d[1], d[1], LocalDate.now());
                Reserva r = new Reserva(pasajero, d[2], LocalDate.parse(d[4]));
                r.setEstado(d[5]);
                lista.add(r);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar reservas: " + e.getMessage());
        }
        return lista;
    }

    public void actualizarEstado(String codigo, String nuevoEstado) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");
                if (d[0].equals(codigo)) {
                    linea = d[0] + ";" + d[1] + ";" + d[2] + ";" +
                            d[3] + ";" + d[4] + ";" + nuevoEstado;
                }
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer reservas: " + e.getMessage());
        }

        try (FileWriter fw = new FileWriter(ARCHIVO, false);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String l : lineas) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar reserva: " + e.getMessage());
        }
    }
}

