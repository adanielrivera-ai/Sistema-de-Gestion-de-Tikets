/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

import sistemagestiontikets.model.Conductor;
import sistemagestiontikets.model.Pasajero;
import sistemagestiontikets.service.PersonaService;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author alexr
 */
public class MenuPersonas {
    
        private final PersonaService personaService;
 
    public MenuPersonas(PersonaService personaService) {
        this.personaService = personaService;
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
        String cedula      = Consolautil.leerTexto("Cédula");
        String nombre      = Consolautil.leerTexto("Nombre completo");
        String licencia    = Consolautil.leerTexto("Número de licencia");

        System.out.println("  Categoría de licencia:");
        System.out.println("    1. B1   2. B2   3. C1   4. C2");
        int catOp = Consolautil.leerEntero("Seleccione categoría");
        String categoria = switch (catOp) {
            case 1 -> "B1"; case 2 -> "B2";
            case 3 -> "C1"; case 4 -> "C2";
            default -> "B1";
        };

        String resultado = personaService.registrarConductor(cedula, nombre, licencia, categoria);
        if (resultado.startsWith("OK")) Consolautil.mostrarExito(resultado);
        else Consolautil.mostrarError(resultado);
    }
 
    private void registrarPasajero() {
        Consolautil.mostrarSubtitulo("Registrar pasajero");
        String cedula      = Consolautil.leerTexto("Cédula");
        String nombre      = Consolautil.leerTexto("Nombre completo");
        LocalDate fechaNac = Consolautil.leerFecha("Fecha de nacimiento");
 

        // Calcular edad para determinar si aplica adulto mayor automáticamente
        int edad = LocalDate.now().getYear() - fechaNac.getYear();
        boolean esEstudiante = false;
        
        // El service calcula automáticamente si es ADULTO_MAYOR (>=60 años)
        // Si no, pregunta si es estudiante

        if (edad < 60) {
            String resp = Consolautil.leerTexto("¿Es estudiante? (s/n)");
            esEstudiante = resp.equalsIgnoreCase("s");
        }
 
        String resultado = personaService.registrarPasajero(cedula, nombre, fechaNac, esEstudiante);
        if (resultado.startsWith("OK")) Consolautil.mostrarExito(resultado);
        
        else Consolautil.mostrarError(resultado);
    }
 
    private void listarConductores() {
        Consolautil.mostrarSubtitulo("Conductores registrados");
        List<Conductor> lista = personaService.listarConductores();
        if (lista.isEmpty()) {
            Consolautil.mostrarInfo("No hay conductores registrados.");
            return;
        }
        for (Conductor c : lista) {
            c.imprimirDetalle();
            Consolautil.mostrarLinea();
        }
    }
 
    private void listarPasajeros() {
        Consolautil.mostrarSubtitulo("Pasajeros registrados");
        List<Pasajero> lista = personaService.listarPasajeros();

        if (lista.isEmpty()) {
            Consolautil.mostrarInfo("No hay pasajeros registrados.");
            return;
        }
        for (Pasajero p : lista) {
            p.imprimirDetalle();
            Consolautil.mostrarLinea();
        }

    }
}
