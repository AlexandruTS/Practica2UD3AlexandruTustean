package base;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Vende {
    private int id;
    private int idVenta;
    private int cantidad;
    private double precio;
    private List<Tienda> tienda;
    private List<Videojuego> videojuego;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "idVenta")
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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
                idVenta == vende.idVenta &&
                cantidad == vende.cantidad &&
                Double.compare(vende.precio, precio) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idVenta, cantidad, precio);
    }

    @ManyToMany(mappedBy = "venta")
    public List<Tienda> getTienda() {
        return tienda;
    }

    public void setTienda(List<Tienda> tienda) {
        this.tienda = tienda;
    }

    @ManyToMany
    @JoinTable(name = "vende", catalog = "", schema = "basejuego", joinColumns = @JoinColumn(name = "idVenta", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "idVideojuego", referencedColumnName = "id", nullable = false))
    public List<Videojuego> getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(List<Videojuego> videojuego) {
        this.videojuego = videojuego;
    }
}
