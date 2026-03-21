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
}
