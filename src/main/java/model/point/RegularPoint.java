package model.point;

import lombok.Getter;
import model.EnumPlayer;

import java.util.Optional;

public class RegularPoint {
    @Getter
    private int firstPlayerPoints;
    @Getter
    private int secondPlayerPoints;
    @Getter
    private boolean isFinished;
    private boolean isDeuce;
    private EnumPlayer winner;
    private final int WINNING_POINT = 4;
    private final int WINNING_POINT_DIFFERENCE = 2;
    private final int DEUCE_POINT = 3;
    void update(EnumPlayer pointWinner) {
        addPoint(pointWinner);

        if (isPointsEqualDeucePoint()) {
            isDeuce = true;
            return;
        }

        if (isPointsLessThanWinningPoint()) {
            return;
        }

        if (!isThereWinningPointDifference()) {
            return;
        }

        setWinner();
        isFinished = true;
    }

    private boolean isPointsEqualDeucePoint() {
        return DEUCE_POINT == firstPlayerPoints && DEUCE_POINT == secondPlayerPoints;
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

    private boolean isThereWinningPointDifference() {
        return Math.abs(firstPlayerPoints - secondPlayerPoints) >= WINNING_POINT_DIFFERENCE;
    }

    private void setWinner() {
        winner = (firstPlayerPoints > secondPlayerPoints) ? EnumPlayer.FIRST_PLAYER : EnumPlayer.SECOND_PLAYER;
    }

    public Optional<EnumPlayer> getOptionalWinner() {
        return Optional.ofNullable(this.winner);
    }

    public void reset() {
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
        winner = null;
        isFinished = false;
    }
}
