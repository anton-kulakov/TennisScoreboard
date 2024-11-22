package model.point;

import model.BaseScore;
import model.EnumPlayer;

public class DeucePoint extends BaseScore {
    private static final int WINNING_POINT = 2;
    private static final int DEUCE_POINT = 1;

    public void update(EnumPlayer pointWinner) {
        addPoint(pointWinner);

        if (isPointsLessThanWinningPoint(WINNING_POINT)) {
            if (isPointsEqualConstantPoint(DEUCE_POINT)) {
                reset();
            }

            return;
        }

        setWinner();
    }
}
