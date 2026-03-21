/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

import sistemagestiontikets.model.Ruta;
import sistemagestiontikets.service.VehiculoService;
import java.util.List;
/**
 *
 * @author alexr
 */
public class MenuRutas {
    
    private final VehiculoService vehiculoService;
 
    public MenuRutas(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }
 
    public void mostrar() {
        int opcion;
        do {
            Consolautil.mostrarTitulo("Gestión de Rutas");
            System.out.println("  1. Registrar ruta");
            System.out.println("  2. Listar rutas");
            System.out.println("  0. Volver al menú principal");
            Consolautil.mostrarLinea();
 
            opcion = Consolautil.leerEntero("Seleccione una opción");
 
            switch (opcion) {
                case 1 -> registrarRuta();
                case 2 -> listarRutas();
                case 0 -> Consolautil.mostrarInfo("Volviendo al menú principal...");
                default -> Consolautil.mostrarError("Opción no válida.");
            }
 
            if (opcion != 0) Consolautil.pausar();
 
        } while (opcion != 0);
    }
 
    private void registrarRuta() {
        Consolautil.mostrarSubtitulo("Registrar nueva ruta");
        String codigo    = Consolautil.leerTexto("Código de ruta");
        String origen    = Consolautil.leerTexto("Ciudad de origen");
        String destino   = Consolautil.leerTexto("Ciudad de destino");
        double distancia = Consolautil.leerDouble("Distancia (km)");
        int    tiempo    = Consolautil.leerEntero("Tiempo estimado (minutos)");
 
        String resultado = vehiculoService.registrarRuta(codigo, origen, destino, distancia, tiempo);
        if (resultado.startsWith("OK")) Consolautil.mostrarExito(resultado);
        else                            Consolautil.mostrarError(resultado);
    }
 
    private void listarRutas() {
        Consolautil.mostrarSubtitulo("Rutas registradas");
        List<Ruta> lista = vehiculoService.listarRutas();
        if (lista.isEmpty()) {
            Consolautil.mostrarInfo("No hay rutas registradas.");
            return;
        }
        for (Ruta r : lista) {
            r.imprimirDetalle();
            Consolautil.mostrarLinea();
        }
    }
}
