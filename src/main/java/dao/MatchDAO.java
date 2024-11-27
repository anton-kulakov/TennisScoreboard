package dao;

import entity.Match;
import org.hibernate.Session;
import util.HibernateUtil;

public class MatchDAO {
    public void save(Match match) {
        try (Session session = HibernateUtil.buildSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction();
        }
    }
}
