package base.hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Tienda {
    private int id;
    private String nombreTienda;
    private String direccion;
    private int telefono;
    private String correoElectronico;
    private List<Vende> vende;

    @Override
    public String toString() {
        return "nombre: " + nombreTienda + " , " + "direccion: " + direccion + " , " + "telefono: " + telefono + " , " + "correo: " + correoElectronico ;
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
                telefono == tienda.telefono &&
                Objects.equals(nombreTienda, tienda.nombreTienda) &&
                Objects.equals(direccion, tienda.direccion) &&
                Objects.equals(correoElectronico, tienda.correoElectronico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreTienda, direccion, telefono, correoElectronico);
    }

    @OneToMany(mappedBy = "tienda")
    public List<Vende> getVende() {
        return vende;
    }

    public void setVende(List<Vende> vende) {
        this.vende = vende;
    }


}
