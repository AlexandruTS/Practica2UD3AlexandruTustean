package base;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Tienda {
    private int id;
    private int idTienda;
    private String nombreTienda;
    private String direccion;
    private int telefono;
    private String correoElectronico;
    private List<Vende> venta;
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
    @Column(name = "idTienda")
    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    @Basic
    @Column(name = "nombreTienda")
    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    @Basic
    @Column(name = "direccion")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "telefono")
    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "correoElectronico")
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tienda tienda = (Tienda) o;
        return id == tienda.id &&
                idTienda == tienda.idTienda &&
                telefono == tienda.telefono &&
                Objects.equals(nombreTienda, tienda.nombreTienda) &&
                Objects.equals(direccion, tienda.direccion) &&
                Objects.equals(correoElectronico, tienda.correoElectronico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idTienda, nombreTienda, direccion, telefono, correoElectronico);
    }

    @ManyToMany
    @JoinTable(name = "vende", catalog = "", schema = "basejuego", joinColumns = @JoinColumn(name = "idTienda", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "idVenta", referencedColumnName = "id", nullable = false))
    public List<Vende> getVenta() {
        return venta;
    }

    public void setVenta(List<Vende> venta) {
        this.venta = venta;
    }

    @ManyToMany(mappedBy = "tienda")
    public List<Videojuego> getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(List<Videojuego> videojuego) {
        this.videojuego = videojuego;
    }
}
