/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.dao;

import sistemagestiontikets.model.Ruta;
import java.io.*;
import java.util.*;

/**
 *
 * @author Camilo
 */
public class RutaDAO {
    private static final String ARCHIVO = "rutas.txt";
    
    public void guardarRuta(Ruta ruta) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(ruta.getCodigo() + ";"
                   + ruta.getOrigen() + ";"
                   + ruta.getDestino() + ";"
                   + ruta.getDistanciaKm() + ";"
                   + ruta.getTiempoEstimadoMin());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar ruta: " + e.getMessage());
        }
    }
 
    public List<Ruta> cargarRutas() {
        List<Ruta> lista = new ArrayList<>();
        File f = new File(ARCHIVO);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");
                if (d.length < 5) continue;
                lista.add(new Ruta(d[0], d[1], d[2],
                    Double.parseDouble(d[3]),
                    Integer.parseInt(d[4])));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar rutas: " + e.getMessage());
        }
        return lista;
    }
}
