package GUI;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

public class Vista {

    private JFrame frame;

    private JTabbedPane tabbedPane1;
    public JPanel panel1;
    public JPanel JPanelVideojuego;
    public JPanel JPanelTienda;
    public JPanel JPanelVenta;
    public JButton anadirVideojuegoBtn;
    public JButton modificarVideojuegoBtn;
    public JButton eliminarVideojuegoBtn;
    public JButton anadirTiendaBtn;
    public JButton modificarTiendaBtn;
    public JButton eliminarTiendaBtn;
    public JButton anadirVentaBtn;
    public JButton modificarVentaBtn;
    public JButton eliminarVentaBtn;
    public JList listVideojuego;
    public JList listVenta;
    public JTextField nombreVideojuego;
    public JTextField generoVideojuego;
    public JTextField precioVideojuego;
    public JTextField nombreTienda;
    public JTextField direccionTienda;
    public JTextField telefonoTienda;
    public JTextField correoTienda;
    public JTextField cantidadVenta;
    public JTextField precioVenta;
    public JComboBox tiendaCbx;
    public JComboBox videojuegoCbx;
    public JList listTienda;
    public DatePicker fechaLanzamiento;

    DefaultListModel dlmTiendas;
    DefaultListModel dlmVideojuegos;
    DefaultListModel dlmVentas;

    JMenuItem conexionItem;
    JMenuItem salirItem;

    public Vista(){
        frame = new JFrame("GAME");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(new Dimension(800,500));
        frame.setLocationRelativeTo(null);

        crearMenu();
        crearModelos();
    }

    private void crearModelos(){
        dlmTiendas = new DefaultListModel();
        listTienda.setModel(dlmTiendas);

        dlmVideojuegos = new DefaultListModel();
        listVideojuego.setModel(dlmVideojuegos);

        dlmVentas = new DefaultListModel();
        listVenta.setModel(dlmVentas);
    }

    private void crearMenu(){
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");

        conexionItem = new JMenuItem("Conectar");
        conexionItem.setActionCommand("Conectar");

        salirItem = new JMenuItem("Salir");
        salirItem.setActionCommand("Salir");

        menu.add(conexionItem);
        menu.add(salirItem);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }
}
