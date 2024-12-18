package service;

import dao.PlayerDAO;
import entity.Match;
import entity.Player;
import model.score.MatchScore;
import org.hibernate.HibernateException;

import java.util.Optional;

public class NewMatchService {
    private final PlayerDAO playerDAO;

    public NewMatchService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public Match createNewMatch(String firstPlayerName, String secondPlayerName) throws HibernateException {
        Player firstPlayer = getPlayer(firstPlayerName);
        Player secondPlayer = getPlayer(secondPlayerName);

        return Match.builder()
                .player1(firstPlayer)
                .player2(secondPlayer)
                .matchScore(new MatchScore())
                .build();
    }
    private Player getPlayer(String playerName) throws HibernateException {
        Optional<Player> optionalPlayer = playerDAO.getByName(playerName);
        return optionalPlayer.orElseGet(() -> new Player(playerName));
    }
}
