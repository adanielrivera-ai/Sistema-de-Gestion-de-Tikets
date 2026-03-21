/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

/**
 *
 * @author alexr
 */
public class MenuVehiculos {
    
    public MenuVehiculos() {
        // TODO: inicializar servicios
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
        String placa = Consolautil.leerTexto("Placa");
        // TODO (Desarrollador 1): leer lista de rutas disponibles y pedir selección
        // TODO: llamar vehiculoService.registrarVehiculo(tipo, placa, ruta)
        Consolautil.mostrarExito("Vehículo registrado correctamente. [pendiente implementación]");
    }
 
    private void listarVehiculos() {
        Consolautil.mostrarSubtitulo("Lista de vehículos registrados");
        // TODO (Desarrollador 1): obtener lista de vehiculoService.listarTodos()
        // y llamar vehiculo.imprimirDetalle() en cada uno
        Consolautil.mostrarInfo("Sin vehículos registrados aún. [pendiente implementación]");
    }
 
    private void asignarConductor() {
        Consolautil.mostrarSubtitulo("Asignar conductor a vehículo");
        String placa      = Consolautil.leerTexto("Placa del vehículo");
        String cedula     = Consolautil.leerTexto("Cédula del conductor");
        // TODO (Desarrollador 1): llamar vehiculoService.asignarConductor(placa, cedula)
        // y manejar excepción si conductor no tiene licencia
        Consolautil.mostrarExito("Conductor asignado. [pendiente implementación]");
    }
}
