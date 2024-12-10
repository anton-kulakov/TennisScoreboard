package service;

import dao.MatchDAO;
import dao.PlayerDAO;
import entity.Match;
import entity.Player;

public class FinishedMatchesPersistenceService {
    PlayerDAO playerDAO;
    MatchDAO matchDAO;

    public FinishedMatchesPersistenceService(PlayerDAO playerDAO, MatchDAO matchDAO) {
        this.playerDAO = playerDAO;
        this.matchDAO = matchDAO;
    }
    public void save(Match match) {
        Player firstPlayer = match.getPlayer1();
        Player secondPlayer = match.getPlayer2();

        if (0 == firstPlayer.getId()) {
            int savedID = playerDAO.save(firstPlayer);
            firstPlayer.setId(savedID);
        }

        if (0 == secondPlayer.getId()) {
            int savedID = playerDAO.save(secondPlayer);
            secondPlayer.setId(savedID);
        }

        matchDAO.save(match);
    }
}
