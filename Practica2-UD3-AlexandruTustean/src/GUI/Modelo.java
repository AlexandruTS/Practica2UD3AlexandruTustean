package GUI;

import base.Tienda;
import base.Vende;
import base.Videojuego;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;

public class Modelo {

    SessionFactory sessionFactory;


    public void desconectar(){
        if(sessionFactory != null && sessionFactory.isOpen())
            sessionFactory.close();
    }

    public void conectar() {
        Configuration configuracion = new Configuration();

        configuracion.configure("hibernate.cfg.xml");

        configuracion.addAnnotatedClass(Tienda.class);
        configuracion.addAnnotatedClass(Vende.class);
        configuracion.addAnnotatedClass(Videojuego.class);

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().applySettings(
                configuracion.getProperties()).build();

        sessionFactory = configuracion.buildSessionFactory(ssr);
    }

    ArrayList<Tienda> getTiendas() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Tienda");
        ArrayList<Tienda> lista = (ArrayList<Tienda>)query.getResultList();
        sesion.close();
        return lista;
    }

    ArrayList<Videojuego> getVideojuegos() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM Videojuego ");
        ArrayList<Videojuego> lista = (ArrayList<Videojuego>)query.getResultList();
        sesion.close();
        return lista;
    }

    ArrayList<Vende> getVentas() {
        Session sesion = sessionFactory.openSession();
        Query query = sesion.createQuery("FROM vende");
        ArrayList<Vende> lista = (ArrayList<Vende>)query.getResultList();
        sesion.close();
        return lista;
    }



    void insertar(Object o){
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
    }

    void modificar(Object o){
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.saveOrUpdate(o);
        session.getTransaction().commit();
        session.close();
    }

    void eliminar(Object o){
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
    }
}
