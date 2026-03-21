/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

/**
 *
 * @author alexr
 */
public class MenuPersonas {
    
        public MenuPersonas() {
        // TODO: inicializar servicio
    }
 
    public void mostrar() {
        int opcion;
        do {
            Consolautil.mostrarTitulo("Gestión de Personas");
            System.out.println("  1. Registrar conductor");
            System.out.println("  2. Registrar pasajero");
            System.out.println("  3. Listar conductores");
            System.out.println("  4. Listar pasajeros");
            System.out.println("  0. Volver al menú principal");
            Consolautil.mostrarLinea();
 
            opcion = Consolautil.leerEntero("Seleccione una opción");
 
            switch (opcion) {
                case 1 -> registrarConductor();
                case 2 -> registrarPasajero();
                case 3 -> listarConductores();
                case 4 -> listarPasajeros();
                case 0 -> Consolautil.mostrarInfo("Volviendo al menú principal...");
                default -> Consolautil.mostrarError("Opción no válida.");
            }
 
            if (opcion != 0) Consolautil.pausar();
 
        } while (opcion != 0);
    }
 
    private void registrarConductor() {
        Consolautil.mostrarSubtitulo("Registrar conductor");
        String cedula    = Consolautil.leerTexto("Cédula");
        String nombre    = Consolautil.leerTexto("Nombre completo");
        // TODO (Desarrollador 2): leer fecha de nacimiento con ConsolaUtil.leerFecha()
        String licencia  = Consolautil.leerTexto("Número de licencia");
        System.out.println("  Categoría de licencia:");
        System.out.println("    1. B1   2. B2   3. C1   4. C2");
        int catOp        = Consolautil.leerEntero("Seleccione categoría");
        String categoria = switch (catOp) {
            case 1 -> "B1"; case 2 -> "B2";
            case 3 -> "C1"; case 4 -> "C2";
            default -> "B1";
        };
        // TODO: llamar personaService.registrarConductor(cedula, nombre, fechaNac, licencia, categoria)
        Consolautil.mostrarExito("Conductor registrado. [pendiente implementación]");
    }
 
    private void registrarPasajero() {
        Consolautil.mostrarSubtitulo("Registrar pasajero");
        String cedula = Consolautil.leerTexto("Cédula");
        String nombre = Consolautil.leerTexto("Nombre completo");
        // El tipo se determina automáticamente desde la fecha de nacimiento (>=60 -> ADULTO_MAYOR)
        // Si no aplica adulto mayor, se pregunta si es estudiante
        // TODO (Desarrollador 2): implementar lógica de determinación de tipo
        Consolautil.mostrarExito("Pasajero registrado. [pendiente implementación]");
    }
 
    private void listarConductores() {
        Consolautil.mostrarSubtitulo("Conductores registrados");
        // TODO: obtener lista de personaService.listarConductores()
        // y llamar conductor.imprimirDetalle()
        Consolautil.mostrarInfo("Sin conductores registrados aún. [pendiente implementación]");
    }
 
    private void listarPasajeros() {
        Consolautil.mostrarSubtitulo("Pasajeros registrados");
        // TODO: obtener lista de personaService.listarPasajeros()
        // y llamar pasajero.imprimirDetalle()
        Consolautil.mostrarInfo("Sin pasajeros registrados aún. [pendiente implementación]");
    }
}
