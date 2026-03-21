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
    public Vehiculo vehiculoConMasTickets() {
        if (vehiculos.isEmpty()) return null;
        Vehiculo max = vehiculos.get(0);
        for (Vehiculo v : vehiculos) {
            if (v.getContadorPasajeros() > max.getContadorPasajeros())
                max = v;
        }
        return max;
    }
    public List<Vehiculo> listarVehiculos() { return vehiculos; }
    public List<Ruta>     listarRutas()     { return rutas;     }
    public Vehiculo buscarPorPlaca(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        }
        return null;
    }
    public String asignarConductor(String placa, String cedulaConductor) {
        // Buscar el vehículo
        Vehiculo vehiculo = buscarPorPlaca(placa);
        if (vehiculo == null)
            return "ERROR: No existe un vehículo con placa " + placa;

        // Buscar el conductor en los archivos
        List<Conductor> conductores = new sistemagestiontikets.dao.ConductorDAO().cargarConductores();
        Conductor conductor = null;
        for (Conductor c : conductores) {
            if (c.getCedula().equals(cedulaConductor)) {
                conductor = c;
                break;
            }
        }
        if (conductor == null)
            return "ERROR: No existe un conductor con cédula " + cedulaConductor;

        // Validar que tenga licencia registrada
        if (conductor.getNumLicencia() == null || conductor.getNumLicencia().isBlank())
            return "ERROR: El conductor no tiene licencia registrada.";

        vehiculo.setConductor(conductor);
        vehiculoDAO.guardarVehiculo(vehiculo);
        return "OK: Conductor " + conductor.getNombre() + " asignado al vehículo " + placa;
    }
}
