package model.points;

import model.BaseScore;
import model.EnumPlayer;

public abstract class AbstractPoints extends BaseScore {
    protected abstract int getWinningPoint();
    protected abstract int getWinningPointDifference();

    void update(EnumPlayer pointWinner) {
        addPoint(pointWinner);

        if (arePointsLessThanWinningPoint(getWinningPoint())) {
            return;
        }

        if (!isThereWinningPointDifference(getWinningPointDifference())) {
            return;
        }

        setWinner();
    }
}
