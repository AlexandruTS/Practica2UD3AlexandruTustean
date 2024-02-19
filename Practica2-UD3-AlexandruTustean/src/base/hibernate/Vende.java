package base.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vende {
    private int id;
    private int cantidad;
    private double precio;
    private Tienda tienda;
    private Videojuego videojuego;

    @Override
    public String toString() {
        return "cantidad: " + cantidad + " , " + "precio: " + precio + " , " + "tienda: " + tienda.toString() + " , " + "videojuego: " + videojuego.toString();
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cantidad")
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "precio")
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vende vende = (Vende) o;
        return id == vende.id &&
                cantidad == vende.cantidad &&
                Double.compare(vende.precio, precio) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cantidad, precio);
    }

    @ManyToOne
    @JoinColumn(name = "idTienda", referencedColumnName = "id", nullable = false)
    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    @ManyToOne
    @JoinColumn(name = "idVideojuego", referencedColumnName = "id", nullable = false)
    public Videojuego getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(Videojuego videojuego) {
        this.videojuego = videojuego;
    }


}
