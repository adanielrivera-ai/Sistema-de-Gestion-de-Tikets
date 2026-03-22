/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.service;

import sistemagestiontikets.dao.ConductorDAO;
import sistemagestiontikets.dao.PasajeroDAO;
import sistemagestiontikets.model.Conductor;
import sistemagestiontikets.model.Pasajero;
import sistemagestiontikets.model.PasajeroRegular;
import sistemagestiontikets.model.PasajeroEstudiante;
import sistemagestiontikets.model.PasajeroAdultoMayor;
import java.time.LocalDate;
import java.util.List;

public class PersonaService {

    private ConductorDAO conductorDAO = new ConductorDAO();
    private PasajeroDAO pasajeroDAO = new PasajeroDAO();
    private List<Pasajero> pasajeros;

    public String registrarConductor(String cedula, String nombre,
                                    String numLicencia, String categoria) {
        Conductor conductor = new Conductor(cedula, nombre, numLicencia, categoria);
        conductorDAO.guardarConductor(conductor);
        System.out.println("Conductor registrado exitosamente.");
        
        return "OK "+"Conductor" + " [" + nombre + "] registrado correctamente.";
    }

    public String registrarPasajero(String cedula, String nombre,
                                   LocalDate fechaNacimiento, boolean esEstudiante) {
        Pasajero pasajero;
        int edad = LocalDate.now().getYear() - fechaNacimiento.getYear();

        if (edad >= 60) {
            pasajero = new PasajeroAdultoMayor(cedula, nombre, fechaNacimiento);
            System.out.println("Tipo detectado automaticamente: Adulto Mayor");
        } else if (esEstudiante) {
            pasajero = new PasajeroEstudiante(cedula, nombre, fechaNacimiento);
            System.out.println("Tipo detectado: Estudiante");
        } else {
            pasajero = new PasajeroRegular(cedula, nombre, fechaNacimiento);
            System.out.println("Tipo detectado: Regular");
        }

        pasajeroDAO.guardarPasajero(pasajero);
        System.out.println("Pasajero registrado exitosamente.");
        
        return "OK "+"Pasajero" + " [" + nombre + "] registrado correctamente.";
    }

    public List<Conductor> listarConductores() {
        return conductorDAO.cargarConductores();
    }

    public List<Pasajero> listarPasajeros() {
        return pasajeroDAO.cargarPasajeros();
    }

    public Pasajero BuscarPorCedula(String cedula) {
        for (Pasajero p : pasajeros) {
            if (p.getCedula().equalsIgnoreCase(cedula)) return p;
        }
        return null;
    }
}