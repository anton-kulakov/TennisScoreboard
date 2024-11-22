package model.point;

import model.BaseScore;
import model.EnumPlayer;

public class RegularPoint extends BaseScore {
    private boolean isDeuce;
    private static final int WINNING_POINT = 4;
    private static final int WINNING_POINT_DIFFERENCE = 2;
    private static final int DEUCE_POINT = 3;
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
    }

    private boolean isPointsEqualDeucePoint() {
        return DEUCE_POINT == firstPlayerPoints && DEUCE_POINT == secondPlayerPoints;
    }

    private boolean isPointsLessThanWinningPoint() {
        return firstPlayerPoints < WINNING_POINT && secondPlayerPoints < WINNING_POINT;
    }

    private boolean isThereWinningPointDifference() {
        return Math.abs(firstPlayerPoints - secondPlayerPoints) >= WINNING_POINT_DIFFERENCE;
    }
}