package model.point;

import model.BaseScore;
import model.EnumPlayer;

public abstract class BaseGamePoint extends BaseScore {
    protected abstract int getWinningPoint();
    protected abstract int getWinningPointDifference();

    void update(EnumPlayer pointWinner) {
        addPoint(pointWinner);

        if (isPointsLessThanWinningPoint(getWinningPoint())) {
            return;
        }

        if (!isThereWinningPointDifference(getWinningPointDifference())) {
            return;
        }

        setWinner();
    }
}
