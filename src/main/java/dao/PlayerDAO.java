package dao;

import entity.Player;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Optional;

public class PlayerDAO {
    private final static PlayerDAO INSTANCE = new PlayerDAO();

    public Optional<Player> getByName(String name) throws DBException {
        String hqlQuery = "FROM Player p WHERE p.name = :name";

        try (Session session = HibernateUtil.buildSessionFactory().openSession()) {
            return session.createQuery(hqlQuery, Player.class).setParameter("name", name).uniqueResultOptional();
        } catch (HibernateException e) {
            throw new DBException();
        }
    }

    public void save(Player player) throws DBException {
        try (Session session = HibernateUtil.buildSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new DBException();
        }
    }
    public static PlayerDAO getInstance() {
        return INSTANCE;
    }
    private PlayerDAO() {

    }
}
