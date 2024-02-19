package base.hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Videojuego {
    private int id;
    private String nombreVideojuego;
    private String genero;
    private double precio;
    private Date fechaLanzamiento;
    private List<Vende> vende;

    @Override
    public String toString() {
        return "Nombre: " + nombreVideojuego +"," + "genero: "+ genero +"," + "precio: " + precio + "," + "fechaLanzamiento: " + fechaLanzamiento;
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
                Double.compare(that.precio, precio) == 0 &&
                Objects.equals(nombreVideojuego, that.nombreVideojuego) &&
                Objects.equals(genero, that.genero) &&
                Objects.equals(fechaLanzamiento, that.fechaLanzamiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreVideojuego, genero, precio, fechaLanzamiento);
    }

    @OneToMany(mappedBy = "videojuego")
    public List<Vende> getVende() {
        return vende;
    }

    public void setVende(List<Vende> vende) {
        this.vende = vende;
    }

}
