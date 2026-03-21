/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

/**
 *
 * @author Camilo
 */
public class Ruta {
    private String codigo;
    private String origen;
    private String destino;
    private double distanciaKm;
    private int    tiempoEstimadoMin;

    public Ruta(String codigo, String origen, String destino, double distanciaKm, int tiempoEstimadoMin) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.tiempoEstimadoMin = tiempoEstimadoMin;
    }
    
    
}
