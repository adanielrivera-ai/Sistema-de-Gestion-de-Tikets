/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

/**
 *
 * @author Camilo
 */
public class Bus extends Vehiculo{
    public Bus(String placa, Ruta ruta) {
        super(placa, ruta);
        this.capacidadMaxima = 45;
        this.tarifaBase = 15000.0;
    }
 
    @Override
    public String getTipoVehiculo() { return "Bus"; }
}
