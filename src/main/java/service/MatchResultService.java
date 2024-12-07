package service;

import entity.Match;
import model.score.EnumPlayer;
import model.score.MatchResult;

public class MatchResultService {
    public void setMatchResult(Match match) {
        MatchResult matchResult = match.getMatchScore().getMatchResult();

        String firstPlayerName = match.getPlayer1().getName();
        String secondPlayerName = match.getPlayer2().getName();
        String winner = match.getWinner().getName();

        matchResult.setFirstPlayerName(firstPlayerName);
        matchResult.setSecondPlayerName(secondPlayerName);
        matchResult.setWinner(winner);
    }

    public void setMatchWinner(Match match) {
        if (match.getMatchScore().getSetScore().getOptionalWinner().isPresent()) {
            EnumPlayer enumWinner = match.getMatchScore().getSetScore().getOptionalWinner().get();

            if (EnumPlayer.FIRST_PLAYER.equals(enumWinner)) {
                match.setWinner(match.getPlayer1());
            } else {
                match.setWinner(match.getPlayer2());
            }
        }
    }

    public boolean isMatchFinished(Match match) {
        return match.getMatchScore().getSetScore().getOptionalWinner().isPresent();
    }
}
