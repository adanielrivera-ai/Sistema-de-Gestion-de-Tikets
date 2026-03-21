/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

import sistemagestiontikets.model.Ruta;
import sistemagestiontikets.model.Vehiculo;
import sistemagestiontikets.service.VehiculoService;
import java.util.List;
/**
 *
 * @author alexr
 */
public class MenuVehiculos {
    
    private final VehiculoService vehiculoService;
 
    public MenuVehiculos(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }
 
    public void mostrar() {
        int opcion;
        do {
            Consolautil.mostrarTitulo("Gestión de Vehículos");
            System.out.println("  1. Registrar Buseta");
            System.out.println("  2. Registrar MicroBus");
            System.out.println("  3. Registrar Bus");
            System.out.println("  4. Listar todos los vehículos");
            System.out.println("  5. Asignar conductor a vehículo");
            System.out.println("  0. Volver al menú principal");
            Consolautil.mostrarLinea();
 
            opcion = Consolautil.leerEntero("Seleccione una opción");
 
            switch (opcion) {
                case 1 -> registrarVehiculo("Buseta");
                case 2 -> registrarVehiculo("MicroBus");
                case 3 -> registrarVehiculo("Bus");
                case 4 -> listarVehiculos();
                case 5 -> asignarConductor();
                case 0 -> Consolautil.mostrarInfo("Volviendo al menú principal...");
                default -> Consolautil.mostrarError("Opción no válida.");
            }
 
            if (opcion != 0) Consolautil.pausar();
 
        } while (opcion != 0);
    }
 
    private void registrarVehiculo(String tipo) {
        Consolautil.mostrarSubtitulo("Registrar " + tipo);
 
        // Mostrar rutas disponibles
        List<Ruta> rutas = vehiculoService.listarRutas();
        if (rutas.isEmpty()) {
            Consolautil.mostrarError("No hay rutas registradas. Vaya al menú 4 y registre una ruta primero.");
            return;
        }
        Consolautil.mostrarInfo("Rutas disponibles:");
        for (Ruta r : rutas) {
            System.out.println("    [" + r.getCodigo() + "] "
                + r.getOrigen() + " -> " + r.getDestino()
                + "  (" + r.getDistanciaKm() + " km)");
        }
 
        String placa      = Consolautil.leerTexto("Placa del vehículo");
        String codigoRuta = Consolautil.leerTexto("Código de ruta");
 
        String resultado = vehiculoService.registrarVehiculo(tipo, placa, codigoRuta);
        if (resultado.startsWith("OK")) Consolautil.mostrarExito(resultado);
        else                            Consolautil.mostrarError(resultado);
    }
 
    private void listarVehiculos() {
        Consolautil.mostrarSubtitulo("Vehículos registrados");
        List<Vehiculo> lista = vehiculoService.listarVehiculos();
        if (lista.isEmpty()) {
            Consolautil.mostrarInfo("No hay vehículos registrados.");
            return;
        }
        for (Vehiculo v : lista) {
            v.imprimirDetalle();
            Consolautil.mostrarLinea();
        }
    }
 
    private void asignarConductor() {
        Consolautil.mostrarSubtitulo("Asignar conductor a vehículo");
        String placa  = Consolautil.leerTexto("Placa del vehículo");
        String cedula = Consolautil.leerTexto("Cédula del conductor");
 
        String resultado = vehiculoService.asignarConductor(placa, cedula);
        if (resultado.startsWith("OK")) Consolautil.mostrarExito(resultado);
        else                            Consolautil.mostrarError(resultado);
    }
}
