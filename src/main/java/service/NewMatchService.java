package service;

import dao.PlayerDAO;
import entity.Match;
import entity.MatchScore;
import entity.Player;
import exception.DBException;

import java.util.Optional;

public class NewMatchService {
    PlayerDAO playerDAO = PlayerDAO.getInstance();

    public Match createNewMatch(String firstPlayerName, String secondPlayerName) throws DBException {
        Player firstPlayer = getPlayer(firstPlayerName);
        Player secondPlayer = getPlayer(secondPlayerName);

        return Match.builder()
                .player1(firstPlayer)
                .player2(secondPlayer)
                .matchScore(new MatchScore())
                .build();
    }

    private Player getPlayer(String playerName) throws DBException {
        Optional<Player> optionalPlayer = playerDAO.getByName(playerName);

        Player player;

        if (optionalPlayer.isEmpty()) {
            player = new Player(playerName);
            playerDAO.save(player);
        } else {
            player = optionalPlayer.get();
        }

        return player;
    }
}
