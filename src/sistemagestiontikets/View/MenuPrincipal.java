/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

import sistemagestiontikets.service.VehiculoService;
import sistemagestiontikets.service.PersonaService;
import sistemagestiontikets.service.TicketService;
import sistemagestiontikets.service.ReservaService;

/**
 *
 * @author alexr
 */
public class MenuPrincipal {

    private final VehiculoService vehiculoService;
    private final PersonaService  personaService;
    private final TicketService   ticketService;
    private final ReservaService  reservaService;
 
    // Submenús
    private final MenuVehiculos menuVehiculos;
    private final MenuPersonas  menuPersonas;
    private final MenuTickets   menuTickets;
    private final MenuRutas     menuRutas;
    private final MenuReportes  menuReportes;
    private final MenuReservas  menuReservas;
 
    public MenuPrincipal() {
 
        // Crear submenús inyectando el service que cada uno necesita
        this.menuVehiculos = new MenuVehiculos(vehiculoService);
        this.menuPersonas  = new MenuPersonas(personaService);
        this.menuTickets   = new MenuTickets(ticketService);
        this.menuRutas     = new MenuRutas(vehiculoService);
        this.menuReportes  = new MenuReportes(ticketService);
        this.menuReservas  = new MenuReservas(reservaService);
    }
 
    public void iniciar() {
        mostrarBienvenida();
 
        // Verificar reservas vencidas automáticamente al arrancar
        int vencidas = reservaService.verificarReservasVencidas();
        if (vencidas > 0) {
            Consolautil.mostrarInfo("Se cancelaron " + vencidas + " reserva(s) vencidas al iniciar.");
        }
 
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
        System.out.println("  6. Reservas");
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
            case 6 -> menuReservas.mostrar();
            case 0 -> { }
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
