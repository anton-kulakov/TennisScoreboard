package model.point;

import lombok.Getter;
import model.EnumPlayer;

import java.util.Optional;

public class TiebreakPoint {
    @Getter
    private int firstPlayerPoints;
    @Getter
    private int secondPlayerPoints;
    private EnumPlayer winner;
    private final int WINNING_POINT = 7;
    private final int WINNING_POINT_DIFFERENCE = 2;

    void update(EnumPlayer pointWinner) {
        addPoint(pointWinner);

        if (isPointsLessThanWinningPoint()) {
            return;
        }

        if (!isThereWinningPointDifference()) {
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
    }
}
