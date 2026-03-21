/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

/**
 *
 * @author Camilo
 */
public class MicroBus extends Vehiculo{
    public MicroBus(String placa, Ruta ruta) {
        super(placa, ruta);
        this.capacidadMaxima = 25;
        this.tarifaBase      = 10000.0;
    }
 
    @Override
    public String getTipoVehiculo() { return "MicroBus"; }
}
