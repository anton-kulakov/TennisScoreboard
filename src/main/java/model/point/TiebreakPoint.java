package model.point;

import model.BaseScore;
import model.EnumPlayer;

public class TiebreakPoint extends BaseScore {
    private static final int WINNING_POINT = 7;
    private static final int WINNING_POINT_DIFFERENCE = 2;

    void update(EnumPlayer pointWinner) {
        addPoint(pointWinner);

        if (isPointsLessThanWinningPoint(WINNING_POINT)) {
            return;
        }

        if (!isThereWinningPointDifference()) {
            return;
        }

        setWinner();
    }

    private boolean isThereWinningPointDifference() {
        return Math.abs(firstPlayerPoints - secondPlayerPoints) >= WINNING_POINT_DIFFERENCE;
    }
}
