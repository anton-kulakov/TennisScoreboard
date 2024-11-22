package model.point;

import model.BaseScore;
import model.EnumPlayer;

public class RegularPoint extends BaseScore {
    private static final int WINNING_POINT = 4;
    private static final int WINNING_POINT_DIFFERENCE = 2;
    private static final int DEUCE_POINT = 3;
    void update(EnumPlayer pointWinner) {
        addPoint(pointWinner);

        if (isPointsEqualDeucePoint()) {
            return;
        }

        if (isPointsLessThanWinningPoint(WINNING_POINT)) {
            return;
        }

        if (!isThereWinningPointDifference(WINNING_POINT_DIFFERENCE)) {
            return;
        }

        setWinner();
    }

    private boolean isPointsEqualDeucePoint() {
        return DEUCE_POINT == firstPlayerPoints && DEUCE_POINT == secondPlayerPoints;
    }
}