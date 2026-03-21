/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

import java.time.LocalDate;
import java.time.Period;

/**
 * Clase abstracta que representa un pasajero en el sistema.
 * Hereda de Persona y agrega fecha de nacimiento y calculo de descuento.
 * @author JAVIER FERNANDEZ
 */
public abstract class Pasajero extends Persona {

    /** Fecha de nacimiento del pasajero */
    private LocalDate fechaNacimiento;

    /**
     * Constructor de Pasajero
     * @param cedula cedula de identidad
     * @param nombre nombre completo
     * @param fechaNacimiento fecha de nacimiento
     */
    public Pasajero(String cedula, String nombre, LocalDate fechaNacimiento) {
        super(cedula, nombre);
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Retorna la fecha de nacimiento del pasajero
     * @return fechaNacimiento
     */
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    /**
     * Calcula la edad actual del pasajero
     * @return edad en años
     */
    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    /**
     * Verifica si el pasajero es adulto mayor
     * @return true si tiene 60 años o mas
     */
    public boolean esAdultoMayor() {
        return getEdad() >= 60;
    }

    /**
     * Calcula el descuento aplicable al pasajero
     * @return porcentaje de descuento entre 0 y 1
     */
    public abstract double calcularDescuento();
}