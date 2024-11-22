package model.point;

import model.BaseScore;
import model.EnumPlayer;

public class DeucePoint extends BaseScore {
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

    private boolean isPointsEqualDeucePoint() {
        return DEUCE_POINT == firstPlayerPoints && DEUCE_POINT == secondPlayerPoints;
    }

    private boolean isPointsLessThanWinningPoint() {
        return firstPlayerPoints < WINNING_POINT && secondPlayerPoints < WINNING_POINT;
    }

    public void reset() {
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
        winner = null;
    }
}
