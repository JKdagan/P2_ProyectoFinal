package Logica;

import Buses.*;

import java.util.ArrayList;

public class SistemaAsientos {

    private Bus bus;
    private ArrayList<Asiento> asientos;
    private ArrayList<Asiento> asientos_pedidos;
    private int precio;

    public SistemaAsientos(Bus bus) {
        this.bus = bus;
        this.asientos = bus.getAsientosArray();
        this.asientos_pedidos = new ArrayList<>();
    }
    public void elegirAsiento(int index) {
        Asiento aux = asientos.get(index);
        //Si el asiento a elegir esta ocupado lanza excepcion
        if (aux.isOcupado()) {
            //Lanzar excepcion esta ocupado
        }
        else {
            //Agrega a la lista de asientos pedidos el seleccionado y le suma al precio
            asientos_pedidos.add(aux);
            aux.setOcupado(true);
            precio += aux.getPrecio() + bus.getTarifa();
        }
    }

    public ArrayList<Asiento> getAsientosTotalesArray() {
        return asientos;
    }
    public ArrayList<Asiento> getAsientosPedidosArray() {
        return asientos_pedidos;
    }
    public int getAsientosTotalInt() {
        return bus.getAsientosTotalInt();
    }

    public int getPrecio() {
        return precio;
    }
}
