/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

/**
 *
 * @author Camilo
 */
public abstract class Vehiculo implements Imprimible{
    protected String placa;
    protected Ruta   ruta;
    protected int    capacidadMaxima;
    protected int    contadorPasajeros;
    protected double tarifaBase;
    protected Conductor conductor;

    public Vehiculo(String placa, Ruta ruta) {
        this.placa = placa;
        this.ruta = ruta;
        this.contadorPasajeros = 0;
    }
    
    public abstract String getTipoVehiculo();
    
    public boolean tieneCupo() {
        return contadorPasajeros < capacidadMaxima;
    }

    public boolean agregarPasajero() {
        if (tieneCupo()) {
            contadorPasajeros++;
            return true;
        }
        return false;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getContadorPasajeros() {
        return contadorPasajeros;
    }

    public void setContadorPasajeros(int contadorPasajeros) {
        this.contadorPasajeros = contadorPasajeros;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "placa=" + placa + ", ruta=" + ruta + ", capacidadMaxima=" + capacidadMaxima + ", contadorPasajeros=" + contadorPasajeros + ", tarifaBase=" + tarifaBase + '}';
    }
}
