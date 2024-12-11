package service;

import dao.MatchDAO;
import dao.PlayerDAO;
import entity.Match;

public class FinishedMatchesPersistenceService {
    PlayerDAO playerDAO;
    MatchDAO matchDAO;

    public FinishedMatchesPersistenceService(PlayerDAO playerDAO, MatchDAO matchDAO) {
        this.playerDAO = playerDAO;
        this.matchDAO = matchDAO;
    }
    public void save(Match match) {
        matchDAO.merge(match);
    }
}
