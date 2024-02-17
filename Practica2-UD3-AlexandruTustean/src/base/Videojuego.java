package base;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Videojuego {
    private int id;
    private int idVideojuego;
    private String nombreVideojuego;
    private String genero;
    private double precio;
    private Date fechaLanzamiento;
    private List<Tienda> tienda;
    private List<Vende> venta;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "idVideojuego")
    public int getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(int idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    @Basic
    @Column(name = "nombreVideojuego")
    public String getNombreVideojuego() {
        return nombreVideojuego;
    }

    public void setNombreVideojuego(String nombreVideojuego) {
        this.nombreVideojuego = nombreVideojuego;
    }

    @Basic
    @Column(name = "genero")
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Basic
    @Column(name = "precio")
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "fechaLanzamiento")
    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Videojuego that = (Videojuego) o;
        return id == that.id &&
                idVideojuego == that.idVideojuego &&
                Double.compare(that.precio, precio) == 0 &&
                Objects.equals(nombreVideojuego, that.nombreVideojuego) &&
                Objects.equals(genero, that.genero) &&
                Objects.equals(fechaLanzamiento, that.fechaLanzamiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idVideojuego, nombreVideojuego, genero, precio, fechaLanzamiento);
    }

    @ManyToMany
    @JoinTable(name = "vende", catalog = "", schema = "basejuego", joinColumns = @JoinColumn(name = "idVideojuego", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "idTienda", referencedColumnName = "id", nullable = false))
    public List<Tienda> getTienda() {
        return tienda;
    }

    public void setTienda(List<Tienda> tienda) {
        this.tienda = tienda;
    }

    @ManyToMany(mappedBy = "videojuego")
    public List<Vende> getVenta() {
        return venta;
    }

    public void setVenta(List<Vende> venta) {
        this.venta = venta;
    }
}
