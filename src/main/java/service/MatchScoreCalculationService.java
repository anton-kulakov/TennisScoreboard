package service;

import entity.Match;
import model.EnumPlayer;

public class MatchScoreCalculationService {
    public void updateMatchScore(Match match, EnumPlayer pointWinner) {
        match.getMatchScore().update(pointWinner);
    }

    public boolean isMatchFinished(Match match) {
        return match.getMatchScore().getSetScore().getOptionalWinner().isPresent();
    }

    public Match getFinishedMatch(Match match, EnumPlayer pointWinner) {
        if (EnumPlayer.FIRST_PLAYER.equals(pointWinner)) {
            match.setWinner(match.getPlayer1());
        } else {
            match.setWinner(match.getPlayer2());
        }

        return match;
    }
}
