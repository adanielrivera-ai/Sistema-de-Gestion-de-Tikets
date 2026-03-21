/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String cedula, String nombre) {
        super(cedula, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.0;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== Pasajero Regular ===");
        System.out.println("Cedula   : " + cedula);
        System.out.println("Nombre   : " + nombre);
        System.out.println("Descuento: 0%");
    }
}