/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

/**
 *
 * @author Camilo
 */
public class Buseta extends Vehiculo{
    public Buseta(String placa, Ruta ruta) {
        super(placa, ruta);
        this.capacidadMaxima = 19;
        this.tarifaBase      = 8000.0;
    }
 
    @Override
    public String getTipoVehiculo() { return "Buseta"; }
 
    @Override
    public void imprimirDetalle() {
        System.out.println("=== Buseta ===");
        System.out.println("Placa    : " + placa);
        System.out.println("Capacidad: " + capacidadMaxima + " pasajeros");
        System.out.println("Tarifa   : $" + String.format("%,.0f", tarifaBase));
        System.out.println("Ruta     : " + (ruta != null
            ? ruta.getOrigen() + " -> " + ruta.getDestino() : "Sin asignar"));
        System.out.println("Conductor: " + (conductor != null
            ? conductor.getNombre() : "Sin asignar"));
    }

}
