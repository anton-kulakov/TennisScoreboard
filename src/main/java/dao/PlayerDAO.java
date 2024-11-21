package dao;

import entity.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Optional;

public class PlayerDAO {

    public Optional<Player> getByName(String name) throws HibernateException {
        String hqlQuery = "FROM Player p WHERE p.name = :name";

        try (Session session = HibernateUtil.buildSessionFactory().openSession()) {
            return session.createQuery(hqlQuery, Player.class).setParameter("name", name).uniqueResultOptional();
        }
    }

    public void save(Player player) throws HibernateException {
        try (Session session = HibernateUtil.buildSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        }
    }
}
