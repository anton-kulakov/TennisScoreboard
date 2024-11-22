package model.point;

import lombok.Getter;
import model.EnumPlayer;

import java.util.Optional;

public class DeucePoint {
    @Getter
    private int firstPlayerPoints;
    @Getter
    private int secondPlayerPoints;
    private EnumPlayer winner;
    private final int WINNING_POINT = 2;
    private final int DEUCE_POINT = 1;

    public void update(EnumPlayer pointWinner) {
        addPoint(pointWinner);

        if (isPointsLessThanWinningPoint()) {
            if (isPointsEqualDeucePoint()) {
                firstPlayerPoints = 0;
                secondPlayerPoints = 0;
            }

            return;
        }

        setWinner();
    }

    private void setWinner() {
        winner = (firstPlayerPoints > secondPlayerPoints) ? EnumPlayer.FIRST_PLAYER : EnumPlayer.SECOND_PLAYER;
    }

    private boolean isPointsEqualDeucePoint() {
        return DEUCE_POINT == firstPlayerPoints && DEUCE_POINT == secondPlayerPoints;
    }

    private boolean isPointsLessThanWinningPoint() {
        return firstPlayerPoints < WINNING_POINT && secondPlayerPoints < WINNING_POINT;
    }

    private void addPoint(EnumPlayer pointWinner) {
        if (pointWinner.equals(EnumPlayer.FIRST_PLAYER)) {
            firstPlayerPoints++;
        } else {
            secondPlayerPoints++;
        }
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
