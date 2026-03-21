/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

/**
 *
 * @author alexr
 */
public class MenuPrincipal {
    /**
    private final MenuVehiculos menuVehiculos;
    private final MenuPersonas  menuPersonas;
    private final MenuTickets   menuTickets;
    private final MenuRutas     menuRutas;
    private final MenuReportes  menuReportes; */

    public MenuPrincipal() {
       /** this.menuVehiculos = new MenuVehiculos();
        this.menuPersonas  = new MenuPersonas();
        this.menuTickets   = new MenuTickets();
        this.menuRutas     = new MenuRutas();
        this.menuReportes  = new MenuReportes();*/
    }
    
     public void iniciar() {
        mostrarBienvenida();
        // TODO (Líder): cargar archivos al iniciar el sistema
        // vehiculoService.cargarDatos();
        // personaService.cargarDatos();
        // ticketService.cargarDatos();
        // rutaService.cargarDatos();

        int opcion;
        do {
            mostrarMenu();
            opcion = Consolautil.leerEntero("Seleccione una opción");
            procesarOpcion(opcion);
        } while (opcion != 0);

        mostrarDespedida();
    }
     
     private void mostrarMenu() {
        Consolautil.mostrarTitulo("Sistema TransCesar S.A.S.");
        System.out.println("  1. Gestión de vehículos");
        System.out.println("  2. Gestión de personas");
        System.out.println("  3. Venta de tickets");
        System.out.println("  4. Gestión de rutas");
        System.out.println("  5. Reportes");
        System.out.println("  0. Salir");
        Consolautil.mostrarLinea();
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> menuVehiculos.mostrar();
            case 2 -> menuPersonas.mostrar();
            case 3 -> menuTickets.mostrar();
            case 4 -> menuRutas.mostrar();
            case 5 -> menuReportes.mostrar();
            case 0 -> { /* salir */ }
            default -> {
                Consolautil.mostrarError("Opción no válida. Intente de nuevo.");
                Consolautil.pausar();
            }
        }
    }
    
    private void mostrarBienvenida() {
        System.out.println();
        System.out.println("  ╔═══════════════════════════════════════════════════╗");
        System.out.println("  ║         SISTEMA DE GESTIÓN DE TICKETS             ║");
        System.out.println("  ║              TransCesar S.A.S.                    ║");
        System.out.println("  ║                                                   ║");
        System.out.println("  ╚═══════════════════════════════════════════════════╝");
        System.out.println();
        Consolautil.pausar();
    }

    private void mostrarDespedida() {
        System.out.println();
        System.out.println("  ╔═══════════════════════════════════════════════════╗");
        System.out.println("  ║   Gracias por usar TransCesar S.A.S.              ║");
        System.out.println("  ║   Hasta pronto.                                   ║");
        System.out.println("  ╚═══════════════════════════════════════════════════╝");
        System.out.println();
    }
}
