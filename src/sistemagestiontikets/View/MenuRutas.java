/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

/**
 *
 * @author alexr
 */
public class MenuRutas {
    
    public MenuRutas() {
        // TODO: inicializar servicio
    }
 
    public void mostrar() {
        int opcion;
        do {
            Consolautil.mostrarTitulo("Gestión de Rutas");
            System.out.println("  1. Registrar ruta");
            System.out.println("  2. Listar rutas");
            System.out.println("  0. Volver al menú principal");
            Consolautil.mostrarLinea();
 
            opcion = Consolautil.leerEntero("Seleccione una opción");
 
            switch (opcion) {
                case 1 -> registrarRuta();
                case 2 -> listarRutas();
                case 0 -> Consolautil.mostrarInfo("Volviendo al menú principal...");
                default -> Consolautil.mostrarError("Opción no válida.");
            }
 
            if (opcion != 0) Consolautil.pausar();
 
        } while (opcion != 0);
    }
 
    private void registrarRuta() {
        Consolautil.mostrarSubtitulo("Registrar nueva ruta");
        String codigo      = Consolautil.leerTexto("Código de ruta");
        String origen      = Consolautil.leerTexto("Ciudad de origen");
        String destino     = Consolautil.leerTexto("Ciudad de destino");
        double distancia   = Consolautil.leerDouble("Distancia (km)");
        int    tiempoEst   = Consolautil.leerEntero("Tiempo estimado (minutos)");
        // TODO (Desarrollador 1): llamar rutaService.registrarRuta(...)
        Consolautil.mostrarExito("Ruta registrada. [pendiente implementación]");
    }
 
    private void listarRutas() {
        Consolautil.mostrarSubtitulo("Rutas registradas");
        // TODO: obtener rutaService.listarTodas()
        // y llamar ruta.imprimirDetalle() en cada una
        Consolautil.mostrarInfo("Sin rutas registradas aún. [pendiente implementación]");
    }
}
