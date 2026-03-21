/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

public class Conductor extends Persona {

    private String numLicencia;
    private String categoria; // B1, B2, C1, C2

    public Conductor(String cedula, String nombre,
                     String numLicencia, String categoria) {
        super(cedula, nombre);
        this.numLicencia = numLicencia;
        this.categoria = categoria;
    }

    public String getNumLicencia() { return numLicencia; }
    public String getCategoria()   { return categoria; }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== Conductor ===");
        System.out.println("Cedula    : " + cedula);
        System.out.println("Nombre    : " + nombre);
        System.out.println("Licencia  : " + numLicencia);
        System.out.println("Categoria : " + categoria);
    }
}
