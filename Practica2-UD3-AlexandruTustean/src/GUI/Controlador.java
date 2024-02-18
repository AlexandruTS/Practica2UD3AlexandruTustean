package GUI;

import base.Tienda;
import base.Vende;
import base.Videojuego;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.sun.java.accessibility.util.SwingEventMonitor.addListSelectionListener;

public class Controlador implements ActionListener, ListSelectionListener {

    private Vista vista;
    private Modelo modelo;
    private boolean conectado;

    public Controlador(Modelo modelo, Vista vista){
        this.vista = vista;
        this.modelo = modelo;
        this.conectado = false;

        addActionListeners(this);
        addListSelectionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if(!conectado && !comando.equalsIgnoreCase("Conectar")){
            JOptionPane.showMessageDialog(null, "No has conectado con la BBDD", "Error de conexion", JOptionPane.ERROR_MESSAGE);
            return;
        }
        switch (comando) {
            case "Salir":
                modelo.desconectar();
                System.exit(0);
                break;
            case "Conectar":
                vista.conexionItem.setEnabled(false);
                modelo.conectar();
                conectado = true;
                break;
            case "anadirVideojuegoBtn":
                Videojuego nuevoVideojuego = new Videojuego();
                nuevoVideojuego.setNombreVideojuego(vista.nombreVideojuego.getText());
                nuevoVideojuego.setGenero(vista.generoVideojuego.getText());
                nuevoVideojuego.setPrecio(Double.valueOf(vista.precioVideojuego.getText()));
                nuevoVideojuego.setFechaLanzamiento(Date.valueOf(vista.fechaLanzamiento.getDate()));
                modelo.insertar(nuevoVideojuego);
                break;
            case "modificarVideojuegoBtn":
                Videojuego modificarVideojuego = (Videojuego) vista.listVideojuego.getSelectedValue();
                modificarVideojuego.setNombreVideojuego(vista.nombreVideojuego.getText());
                modificarVideojuego.setGenero(vista.generoVideojuego.getText());
                modificarVideojuego.setPrecio(Double.valueOf(vista.precioVideojuego.getText()));
                modificarVideojuego.setFechaLanzamiento(Date.valueOf(vista.fechaLanzamiento.getDate()));
                modelo.modificar(modificarVideojuego);
                break;
            case "eliminarVideojuegoBtn":
                Videojuego eliminarVideojuego = (Videojuego) vista.listVideojuego.getSelectedValue();
                if(!comprobarVideojuegoVenta(eliminarVideojuego.getIdVideojuego())) {
                    JOptionPane.showMessageDialog(null,"este videojuego esta ligado a una venta, debes eliminar la venta priemro",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    break;
                }
                modelo.eliminar(eliminarVideojuego);
                break;
            case "anadirTiendaBtn":
                Tienda nuevaTienda = new Tienda();
                nuevaTienda.setNombreTienda(vista.nombreTienda.getText());
                nuevaTienda.setDireccion(vista.direccionTienda.getText());
                nuevaTienda.setTelefono(Integer.parseInt(vista.telefonoTienda.getText()));
                nuevaTienda.setCorreoElectronico(vista.correoTienda.getText());
                modelo.insertar(nuevaTienda);
                break;
            case "modificarTiendaBtn":
                Tienda modificarTienda = (Tienda) vista.listTienda.getSelectedValue();
                modificarTienda.setNombreTienda(vista.nombreTienda.getText());
                modificarTienda.setDireccion(vista.direccionTienda.getText());
                modificarTienda.setTelefono(Integer.parseInt(vista.telefonoTienda.getText()));
                modificarTienda.setCorreoElectronico(vista.correoTienda.getText());
                modelo.modificar(modificarTienda);
                break;
            case "eliminarTiendaBtn":
                Tienda elimniarTienda = (Tienda) vista.listTienda.getSelectedValue();
                if(!comprobarTiendaVenta(elimniarTienda.getIdTienda())) {
                    JOptionPane.showMessageDialog(null, "esta tienda esta ligada a una venta, debes eliminar la venta primero",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                modelo.eliminar(elimniarTienda);
                break;
            case "anadirVentaBtn":
                Vende nuevaVenta = new Vende();
                nuevaVenta.setCantidad(Integer.parseInt(vista.cantidadVenta.getText()));
                nuevaVenta.setPrecio(Double.parseDouble(vista.precioVenta.getText()));
                nuevaVenta.setTienda((List<Tienda>) vista.tiendaCbx.getSelectedItem());
                nuevaVenta.setVideojuego((List<Videojuego>) vista.videojuegoCbx.getSelectedItem());
                modelo.insertar(nuevaVenta);
                break;
            case "modificarVentaBtn":
                Vende modificarVenta = (Vende) vista.listVenta.getSelectedValue();
                modificarVenta.setCantidad(Integer.parseInt(vista.cantidadVenta.getText()));
                modificarVenta.setPrecio(Double.parseDouble(vista.precioVenta.getText()));
                modificarVenta.setTienda((List<Tienda>) vista.tiendaCbx.getSelectedItem());
                modificarVenta.setVideojuego((List<Videojuego>) vista.videojuegoCbx.getSelectedItem());
                modelo.modificar(modificarVenta);
                break;
            case "eliminarVentaBtn":
                Vende eliminarVenta = (Vende) vista.listVenta.getSelectedValue();
                modelo.eliminar(eliminarVenta);
                break;
        }
        limpiarCampos();
        actualizar();
    }

    public boolean comprobarVideojuegoVenta(int id){
        for(Vende venta : modelo.getVentas()) {
            Videojuego v = (Videojuego) venta.getVideojuego();
            if(v.getIdVideojuego() == id){
                return false;
            }
        }
        return true;
    }

    public boolean comprobarTiendaVenta(int id){
        for (Vende venta : modelo.getVentas()) {
            Tienda t = (Tienda) venta.getTienda();
            if(t.getIdTienda() == id){
                return false;
            }
        }
        return true;
    }

    public void limpiarCampos(){
        limpiarCamposVideojuegos();
        limpiarCamposTiendas();
        limpiarCamposVentas();
    }

    private void limpiarCamposVideojuegos(){
        vista.nombreVideojuego.setText("");
        vista.generoVideojuego.setText("");
        vista.precioVideojuego.setText("");
        vista.fechaLanzamiento.setDate(null);
    }
    private void limpiarCamposTiendas(){
        vista.nombreTienda.setText("");
        vista.direccionTienda.setText("");
        vista.telefonoTienda.setText("");
        vista.correoTienda.setText("");
    }
    private void limpiarCamposVentas(){
        vista.cantidadVenta.setText("");
        vista.precioVenta.setText("");
        vista.tiendaCbx.setSelectedItem(-1);
        vista.videojuegoCbx.setSelectedItem(-1);
    }

    private void actualizar(){
        listarVideojuegos(modelo.getVideojuegos());
        listarTiendas(modelo.getTiendas());
    }

    private void listarVideojuegos(ArrayList<Videojuego> lista){
        vista.dlmVideojuegos.clear();
        for (Videojuego videojuego : lista){
            vista.dlmVideojuegos.addElement(videojuego);
        }
        vista.videojuegoCbx.removeAllItems();
        ArrayList<Videojuego> videoJu = modelo.getVideojuegos();

        for (Videojuego v : videoJu){
            vista.videojuegoCbx.addItem(v);
        }
        vista.videojuegoCbx.setSelectedItem(-1);
    }
    private void listarTiendas(ArrayList<Tienda> lista){
        vista.dlmTiendas.clear();
        for (Tienda tienda : lista){
            vista.dlmTiendas.addElement(tienda);
        }
        vista.tiendaCbx.removeAllItems();
        ArrayList<Tienda> tiendaLi = modelo.getTiendas();
        for(Tienda t : tiendaLi){
            vista.tiendaCbx.addItem(t);
        }
        vista.videojuegoCbx.setSelectedItem(-1);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) {
            if(e.getSource() == vista.listVideojuego){
                Videojuego videojuegoSeleccionado = (Videojuego) vista.listVideojuego.getSelectedValue();
                vista.nombreVideojuego.setText(String.valueOf(videojuegoSeleccionado.getNombreVideojuego()));
                vista.generoVideojuego.setText(String.valueOf(videojuegoSeleccionado.getGenero()));
                vista.precioVideojuego.setText(String.valueOf(videojuegoSeleccionado.getPrecio()));
                vista.fechaLanzamiento.setDate(LocalDate.parse(vista.fechaLanzamiento.getDate().toString()));
            }
            if (e.getSource() == vista.listTienda){
                Tienda tiendaSeleccionada = (Tienda) vista.listTienda.getSelectedValue();
                vista.nombreTienda.setText(String.valueOf(tiendaSeleccionada.getNombreTienda()));
                vista.direccionTienda.setText(String.valueOf(tiendaSeleccionada.getDireccion()));
                vista.telefonoTienda.setText(String.valueOf(tiendaSeleccionada.getTelefono()));
                vista.correoTienda.setText(String.valueOf(tiendaSeleccionada.getCorreoElectronico()));
            }
            if (e.getSource() == vista.listVenta){
                Vende ventaSeleccionada =(Vende) vista.listVenta.getSelectedValue();
                vista.cantidadVenta.setText(String.valueOf(ventaSeleccionada.getCantidad()));
                vista.precioVenta.setText(String.valueOf(ventaSeleccionada.getPrecio()));
                vista.tiendaCbx.setSelectedItem(ventaSeleccionada.getTienda());
                vista.videojuegoCbx.setSelectedItem(ventaSeleccionada.getVideojuego());
            }
        }
    }

    public void addActionListeners(ActionListener Listener){
        vista.conexionItem.addActionListener(Listener);
        vista.salirItem.addActionListener(Listener);
        vista.anadirVideojuegoBtn.addActionListener(Listener);
        vista.anadirTiendaBtn.addActionListener(Listener);
        vista.anadirVentaBtn.addActionListener(Listener);
        vista.modificarVideojuegoBtn.addActionListener(Listener);
        vista.modificarTiendaBtn.addActionListener(Listener);
        vista.modificarVentaBtn.addActionListener(Listener);
        vista.eliminarVideojuegoBtn.addActionListener(Listener);
        vista.eliminarTiendaBtn.addActionListener(Listener);
        vista.eliminarVentaBtn.addActionListener(Listener);
    }
    private void addListSelectionListener(ListSelectionListener listener){
        vista.listVideojuego.addListSelectionListener(listener);
        vista.listTienda.addListSelectionListener(listener);
        vista.listVenta.addListSelectionListener(listener);
    }
}
