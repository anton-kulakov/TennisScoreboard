package dao;

import entity.Match;
import model.Page;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class MatchDAO {
    private final static String GET_ALL_HQL = "FROM Match m";
    private final static String GET_BY_NAME_HQL = "FROM Match m WHERE LOWER(m.player1.name) LIKE LOWER(:name) OR LOWER(m.player2.name) LIKE LOWER(:name)";
    private final static String COUNT_ALL_HQL = "SELECT count (m.id) FROM Match m";
    private final static String COUNT_BY_NAME_HQL = "SELECT count (m.id) FROM Match m WHERE LOWER(m.player1.name) LIKE LOWER(:name) OR LOWER(m.player2.name) LIKE LOWER(:name)";
    public Page getByName(int pageNumber, int entitiesLimit, String name) {
        int firstResult = (pageNumber == 1) ? 0 : (pageNumber - 1) * entitiesLimit;
        String searchPattern = getPatternForPartialMatch(name);
        int totalPagesNumber;
        List<Match> matches;

        try (Session session = HibernateUtil.buildSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Long> countQuery = session.createQuery(COUNT_BY_NAME_HQL, Long.class).setParameter("name", searchPattern);
            Query<Match> matchesQuery = session.createQuery(GET_BY_NAME_HQL, Match.class).setParameter("name", searchPattern);

            matchesQuery.setFirstResult(firstResult);
            matchesQuery.setMaxResults(entitiesLimit);

            Long totalEntitiesNumber = countQuery.uniqueResult();
            matches = matchesQuery.list();

            totalPagesNumber = getTotalPagesNumber(entitiesLimit, totalEntitiesNumber);

            transaction.commit();
        }

        return new Page(matches, pageNumber, totalPagesNumber);
    }

    public Page getAll(int pageNumber, int entitiesLimit) {
        int firstResult = (pageNumber == 1) ? 0 : (pageNumber - 1) * entitiesLimit;

        int totalPagesNumber;
        List<Match> matches;

        try (Session session = HibernateUtil.buildSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Long> countQuery = session.createQuery(COUNT_ALL_HQL, Long.class);
            Query<Match> matchesQuery = session.createQuery(GET_ALL_HQL, Match.class);

            matchesQuery.setFirstResult(firstResult);
            matchesQuery.setMaxResults(entitiesLimit);

            Long totalEntitiesNumber = countQuery.uniqueResult();
            matches = matchesQuery.list();

            totalPagesNumber = getTotalPagesNumber(entitiesLimit, totalEntitiesNumber);

            transaction.commit();
        }

        return new Page(matches, pageNumber, totalPagesNumber);
    }

    public void merge(Match match) {
        try (Session session = HibernateUtil.buildSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(match);
            transaction.commit();
        }
    }

    private static int getTotalPagesNumber(int entitiesLimit, Long totalEntitiesNumber) {
        int totalPagesNumber;

        if (totalEntitiesNumber % entitiesLimit == 0) {
            totalPagesNumber = (int) (totalEntitiesNumber / entitiesLimit);
        } else {
            totalPagesNumber = (int) (totalEntitiesNumber / entitiesLimit + 1);
        }

        return totalPagesNumber;
    }

    private static String getPatternForPartialMatch(String name) {
        return "%" + name + "%";
    }
}
