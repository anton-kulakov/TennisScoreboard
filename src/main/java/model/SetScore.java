package model;

import lombok.Getter;

import java.util.Optional;

public class SetScore {
    private final GameScore gameScore;
    @Getter
    private int firstPlayerPoints;
    @Getter
    private int secondPlayerPoints;
    private EnumPlayer winner;
    private final int WINNING_POINT = 2;
    public SetScore(GameScore gameScore) {
        this.gameScore = gameScore;
    }
    void update(EnumPlayer pointWinner) {
        gameScore.update(pointWinner);

        if (gameScore.getOptionalWinner().isPresent()) {
            addPoint(gameScore.getOptionalWinner().get());
            gameScore.resetWinningPointDifference();
            gameScore.reset();
        }

        if (isPointsLessThanWinningPoint()) {
            return;
        }

        setWinner();
    }

    private void addPoint(EnumPlayer pointWinner) {
        if (pointWinner.equals(EnumPlayer.FIRST_PLAYER)) {
            firstPlayerPoints++;
        } else {
            secondPlayerPoints++;
        }
    }
    private boolean isPointsLessThanWinningPoint() {
        return firstPlayerPoints < WINNING_POINT && secondPlayerPoints < WINNING_POINT;
    }

    private void setWinner() {
        winner = (firstPlayerPoints > secondPlayerPoints) ? EnumPlayer.FIRST_PLAYER : EnumPlayer.SECOND_PLAYER;
    }

    public Optional<EnumPlayer> getOptionalWinner() {
        return Optional.ofNullable(this.winner);
    }
}
