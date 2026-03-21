/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

/**
 * Clase que representa un conductor en el sistema.
 * Hereda de Persona y agrega numero de licencia y categoria.
 * @author JAVIER FERNANDEZ
 */
public class Conductor extends Persona {

    /** Numero de licencia de conduccion */
    private String numLicencia;
    
    /** Categoria de la licencia: B1, B2, C1, C2 */
    private String categoria;

    /**
     * Constructor de Conductor
     * @param cedula cedula de identidad
     * @param nombre nombre completo
     * @param numLicencia numero de licencia
     * @param categoria categoria de la licencia
     */
    public Conductor(String cedula, String nombre,
                     String numLicencia, String categoria) {
        super(cedula, nombre);
        this.numLicencia = numLicencia;
        this.categoria = categoria;
    }

    /**
     * Retorna el numero de licencia
     * @return numLicencia
     */
    public String getNumLicencia() { return numLicencia; }

    /**
     * Retorna la categoria de la licencia
     * @return categoria
     */
    public String getCategoria() { return categoria; }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== Conductor ===");
        System.out.println("Cedula    : " + cedula);
        System.out.println("Nombre    : " + nombre);
        System.out.println("Licencia  : " + numLicencia);
        System.out.println("Categoria : " + categoria);
    }
}