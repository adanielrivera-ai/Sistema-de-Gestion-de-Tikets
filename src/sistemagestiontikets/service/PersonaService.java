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
import java.util.List;

public class PersonaService {

    private ConductorDAO conductorDAO = new ConductorDAO();
    private PasajeroDAO pasajeroDAO = new PasajeroDAO();

    public void registrarConductor(String cedula, String nombre,
                                    String numLicencia, String categoria) {
        Conductor conductor = new Conductor(cedula, nombre, numLicencia, categoria);
        conductorDAO.guardarConductor(conductor);
        System.out.println("Conductor registrado exitosamente.");
    }

    public void registrarPasajero(String cedula, String nombre, int tipo) {
        Pasajero pasajero;
        switch (tipo) {
            case 1:
                pasajero = new PasajeroRegular(cedula, nombre);
                break;
            case 2:
                pasajero = new PasajeroEstudiante(cedula, nombre);
                break;
            case 3:
                pasajero = new PasajeroAdultoMayor(cedula, nombre);
                break;
            default:
                System.out.println("Tipo de pasajero invalido.");
                return;
        }
        pasajeroDAO.guardarPasajero(pasajero);
        System.out.println("Pasajero registrado exitosamente.");
    }

    public List<Conductor> listarConductores() {
        return conductorDAO.cargarConductores();
    }

    public List<Pasajero> listarPasajeros() {
        return pasajeroDAO.cargarPasajeros();
    }
}
