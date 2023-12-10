package GUI;

import Logica.*;
import Buses.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelConfirmacion extends JPanel {
    private Recorrido recorrido;
    private SistemaAsientos sistema_asientos;
    private VentanasMediator mediator;

    private JButton boton_pagar;
    private JButton boton_cancelar;
    private JPanel panel_info;

    public PanelConfirmacion(Recorrido recorrido, SistemaAsientos sistema_asientos, VentanasMediator mediator) {
        this.setLayout(new BorderLayout());
        this.recorrido = recorrido;
        this.sistema_asientos = sistema_asientos;
        this.mediator = mediator;
        mediator.setPanel(this);

        this.panel_info = new PanelInfo();
        this.boton_pagar = new BotonPagar("Pagar          ");
        this.boton_cancelar = new BotonCancelar("Cancelar Compra");

        this.add(panel_info, BorderLayout.CENTER);
        this.add(boton_pagar, BorderLayout.EAST);
        this.add(boton_cancelar, BorderLayout.WEST);
    }
    private int showPrecio() {
        return sistema_asientos.getPrecioInt();
    }
    private String showAsientosSeleccionados() {
        String s = new String();
        ArrayList<Asiento> aux_array = sistema_asientos.getAsientosSeleccionadosArray();
        Asiento aux_asiento;
        for (int i = 0; i < aux_array.size() - 1; i++) {
            aux_asiento = aux_array.get(i);
            s += aux_asiento.getNumero()+ "(" +aux_asiento.getTipo() + ")" + ", ";
        }
        aux_asiento = aux_array.get(aux_array.size() - 1);
        s += aux_asiento.getNumero() + "(" +aux_asiento.getTipo() + ")";
        return s;
    }
    private class PanelInfo extends JPanel {
        private JLabel label_recorrido;
        private JLabel label_precio;
        private JLabel label_info;
        public PanelInfo() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.label_recorrido = new JLabel(recorrido.toString());
            this.label_precio = new JLabel("Precio total: $" + showPrecio());
            this.label_info = new JLabel("Asientos comprados: " + showAsientosSeleccionados());

            this.add(label_recorrido);
            this.add(label_precio);
            this.add(label_info);
        }
    }
    private class BotonPagar extends JButton {
        public BotonPagar(String s) {
            super(s);
            this.addActionListener(new BotonPagarListener());
        }

        class BotonPagarListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema_asientos.confirmarAsientos();
                mediator.cerrarVentana();
            }
        }
    }
    private class BotonCancelar extends JButton {
        public BotonCancelar(String s) {
            super(s);
            this.addActionListener(new BotonCancelarListener());
        }
        class BotonCancelarListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema_asientos.cancelarCompra();
                mediator.cerrarVentana();
            }
        }
    }
}
