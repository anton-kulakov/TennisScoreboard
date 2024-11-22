package model.point;

import model.BaseScore;
import model.EnumPlayer;

public class TiebreakPoint extends BaseScore {
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

    private boolean isPointsLessThanWinningPoint() {
        return firstPlayerPoints < WINNING_POINT && secondPlayerPoints < WINNING_POINT;
    }

    private boolean isThereWinningPointDifference() {
        return Math.abs(firstPlayerPoints - secondPlayerPoints) >= WINNING_POINT_DIFFERENCE;
    }

    public void reset() {
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
        winner = null;
    }
}
