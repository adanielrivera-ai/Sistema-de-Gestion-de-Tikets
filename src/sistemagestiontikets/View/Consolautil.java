/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 *
 * @author alexr
 */
public class Consolautil {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final String LINEA = "═══════════════════════════════════════════════════════";
    private static final String LINEA_SIMPLE = "───────────────────────────────────────────────────────";
 
    
    public static void mostrarTitulo(String titulo) {
        System.out.println();
        System.out.println(LINEA);
        System.out.println("  " + titulo.toUpperCase());
        System.out.println(LINEA);
    }
 
    public static void mostrarSubtitulo(String subtitulo) {
        System.out.println(LINEA_SIMPLE);
        System.out.println("  " + subtitulo);
        System.out.println(LINEA_SIMPLE);
    }
 
    public static void mostrarLinea() {
        System.out.println(LINEA_SIMPLE);
    }
 
    public static void mostrarExito(String mensaje) {
        System.out.println("\n  [OK] " + mensaje);
    }
 
    public static void mostrarError(String mensaje) {
        System.out.println("\n  [ERROR] " + mensaje);
    }
 
    public static void mostrarInfo(String mensaje) {
        System.out.println("  >> " + mensaje);
    }
 
    public static void salto() {
        System.out.println();
    }
    
    public static String leerTexto(String mensaje) {
        System.out.print("  " + mensaje + ": ");
        return scanner.nextLine().trim();
    }
 
    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print("  " + mensaje + ": ");
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                mostrarError("Ingrese un número entero válido.");
            }
        }
    }
 
    public static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print("  " + mensaje + ": ");
                double valor = Double.parseDouble(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                mostrarError("Ingrese un número válido (use punto decimal).");
            }
        }
    }
 
    public static LocalDate leerFecha(String mensaje) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                System.out.print("  " + mensaje + " (dd/MM/yyyy): ");
                String entrada = scanner.nextLine().trim();
                return LocalDate.parse(entrada, fmt);
            } catch (DateTimeParseException e) {
                mostrarError("Formato de fecha inválido. Use dd/MM/yyyy.");
            }
        }
    }
 
    public static void pausar() {
        System.out.print("\n  Presione ENTER para continuar...");
        scanner.nextLine();
    }
 
    public static Scanner getScanner() {
        return scanner;
    }
}
