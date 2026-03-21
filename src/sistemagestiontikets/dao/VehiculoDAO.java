/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.dao;

import sistemagestiontikets.model.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Camilo
 */
public class VehiculoDAO {
    private static final String BUSETA   = "busetas.txt";
    private static final String MICROBUS = "microbuses.txt";
    private static final String BUS      = "buses.txt";
    public void guardarVehiculo(Vehiculo vehiculo) {
        String archivo = obtenerArchivo(vehiculo);
        try (FileWriter fw = new FileWriter(archivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            String codRuta = vehiculo.getRuta() != null
                           ? vehiculo.getRuta().getCodigo() : "null";
            bw.write(vehiculo.getTipoVehiculo() + ";"
                   + vehiculo.getPlaca() + ";"
                   + codRuta + ";"
                   + vehiculo.getContadorPasajeros());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar vehiculo: " + e.getMessage());
        }
    }
 
    private String obtenerArchivo(Vehiculo v) {
        if (v instanceof Buseta)   return BUSETA;
        if (v instanceof MicroBus) return MICROBUS;
        return BUS;
    }
 
    public List<Vehiculo> cargarVehiculos(List<Ruta> rutas) {
        List<Vehiculo> lista = new ArrayList<>();
        lista.addAll(leerArchivo(BUSETA,   "Buseta",   rutas));
        lista.addAll(leerArchivo(MICROBUS, "MicroBus", rutas));
        lista.addAll(leerArchivo(BUS,      "Bus",      rutas));
        return lista;
    }
 
    private List<Vehiculo> leerArchivo(String archivo, String tipo, List<Ruta> rutas) {
        List<Vehiculo> lista = new ArrayList<>();
        File f = new File(archivo);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");
                if (d.length < 4) continue;
                String placa = d[1];
                Ruta ruta = null;
                for (Ruta r : rutas) {
                    if (r.getCodigo().equals(d[2])) { ruta = r; break; }
                }
                Vehiculo v;
                switch (tipo) {
                    case "Buseta":   v = new Buseta(placa, ruta);   break;
                    case "MicroBus": v = new MicroBus(placa, ruta); break;
                    default:         v = new Bus(placa, ruta);       break;
                }
                v.setContadorPasajeros(Integer.parseInt(d[3]));
                lista.add(v);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar vehiculos: " + e.getMessage());
        }
        return lista;
    }
}
