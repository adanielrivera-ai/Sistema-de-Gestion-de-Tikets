/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.service;

import sistemagestiontikets.dao.RutaDAO;
import sistemagestiontikets.dao.VehiculoDAO;
import sistemagestiontikets.model.*;
import java.util.*;

/**
 *
 * @author Camilo
 */
public class VehiculoService {
    private List<Vehiculo> vehiculos;
    private List<Ruta> rutas;
    private VehiculoDAO vehiculoDAO;
    private RutaDAO rutaDAO;
    public VehiculoService() {
        this.vehiculoDAO = new VehiculoDAO();
        this.rutaDAO     = new RutaDAO();
        this.rutas       = rutaDAO.cargarRutas();
        this.vehiculos   = vehiculoDAO.cargarVehiculos(rutas);
    }
    public String registrarRuta(String codigo, String origen, String destino,
                                double distancia, int tiempo) {
        for (Ruta r : rutas) {
            if (r.getCodigo().equals(codigo))
                return "ERROR: Ya existe una ruta con codigo " + codigo;
        }
        Ruta nueva = new Ruta(codigo, origen, destino, distancia, tiempo);
        rutas.add(nueva);
        rutaDAO.guardarRuta(nueva);
        return "OK: Ruta " + codigo + " registrada.";
    }
    public String registrarVehiculo(String tipo, String placa, String codigoRuta) {
        // Validacion: placa duplicada
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa))
                return "ERROR: Ya existe un vehiculo con placa " + placa;
        }
        // Buscar ruta
        Ruta ruta = null;
        for (Ruta r : rutas) {
            if (r.getCodigo().equals(codigoRuta)) { ruta = r; break; }
        }
        if (ruta == null) return "ERROR: No existe la ruta " + codigoRuta;
        // Crear vehiculo segun tipo
        Vehiculo nuevo;
        switch (tipo) {
            case "Buseta":   nuevo = new Buseta(placa, ruta);   break;
            case "MicroBus": nuevo = new MicroBus(placa, ruta); break;
            case "Bus":      nuevo = new Bus(placa, ruta);      break;
            default: return "ERROR: Tipo invalido. Use Buseta, MicroBus o Bus.";
        }
        vehiculos.add(nuevo);
        vehiculoDAO.guardarVehiculo(nuevo);
        return "OK: " + tipo + " [" + placa + "] registrado correctamente.";
    }

}
