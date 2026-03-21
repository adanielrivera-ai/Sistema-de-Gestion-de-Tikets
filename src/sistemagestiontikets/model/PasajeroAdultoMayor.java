/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

import java.time.LocalDate;

public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor(String cedula, String nombre, LocalDate fechaNacimiento) {
        super(cedula, nombre, fechaNacimiento);
    }

    @Override
    public double calcularDescuento() {
        return 0.30;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== Pasajero Adulto Mayor ===");
        System.out.println("Cedula   : " + getCedula());
        System.out.println("Nombre   : " + getNombre());
        System.out.println("Edad     : " + getEdad());
        System.out.println("Descuento: 30%");
    }
}
