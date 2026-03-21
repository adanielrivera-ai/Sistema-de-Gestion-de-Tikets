/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

/**
 *
 * @author alexr
 */
public class MenuTickets {
    public MenuTickets() {
        // TODO: inicializar servicio
    }
 
    public void mostrar() {
        int opcion;
        do {
            Consolautil.mostrarTitulo("Gestión de Tickets");
            System.out.println("  1. Vender ticket");
            System.out.println("  2. Listar todos los tickets");
            System.out.println("  0. Volver al menú principal");
            Consolautil.mostrarLinea();
 
            opcion = Consolautil.leerEntero("Seleccione una opción");
 
            switch (opcion) {
                case 1 -> venderTicket();
                case 2 -> listarTickets();
                case 0 -> Consolautil.mostrarInfo("Volviendo al menú principal...");
                default -> Consolautil.mostrarError("Opción no válida.");
            }
 
            if (opcion != 0) Consolautil.pausar();
 
        } while (opcion != 0);
    }
 
    private void venderTicket() {
        Consolautil.mostrarSubtitulo("Vender ticket");
        String cedulaPasajero = Consolautil.leerTexto("Cédula del pasajero");
        String placaVehiculo  = Consolautil.leerTexto("Placa del vehículo");
        String origen         = Consolautil.leerTexto("Ciudad de origen");
        String destino        = Consolautil.leerTexto("Ciudad de destino");
        // La fecha de compra se toma automáticamente con LocalDate.now()
        // TODO (Desarrollador 2): llamar ticketService.venderTicket(...)
        // Manejar caso: vehículo sin cupos
        // Manejar caso: pasajero con 3 tickets en el día (Req 2)
        // Manejar caso: día festivo -> recargo 20% (Req 2)
        Consolautil.mostrarExito("Ticket generado. [pendiente implementación]");
    }
 
    private void listarTickets() {
        Consolautil.mostrarSubtitulo("Todos los tickets vendidos");
        // TODO: obtener ticketService.listarTodos()
        // y llamar ticket.imprimirDetalle() en cada uno
        Consolautil.mostrarInfo("Sin tickets registrados aún. [pendiente implementación]");
    }
}
