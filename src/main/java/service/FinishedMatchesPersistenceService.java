package service;

import dao.MatchDAO;
import entity.Match;

public class FinishedMatchesPersistenceService {
    MatchDAO matchDAO;

    public FinishedMatchesPersistenceService(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }
    public void save(Match match) {
        matchDAO.save(match);
    }
}
