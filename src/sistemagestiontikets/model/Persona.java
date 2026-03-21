/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

/**
 * Clase abstracta que representa una persona en el sistema.
 * Es la base de la jerarquia de personas.
 * @author JAVIER FERNANDEZ
 */
public abstract class Persona implements Imprimible {

    /** Cedula de identidad de la persona */
    protected String cedula;
    
    /** Nombre completo de la persona */
    protected String nombre;

    /**
     * Constructor de Persona
     * @param cedula cedula de identidad
     * @param nombre nombre completo
     */
    public Persona(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    /**
     * Retorna la cedula de la persona
     * @return cedula
     */
    public String getCedula() { return cedula; }

    /**
     * Retorna el nombre de la persona
     * @return nombre
     */
    public String getNombre() { return nombre; }

    @Override
    public abstract void imprimirDetalle();
}