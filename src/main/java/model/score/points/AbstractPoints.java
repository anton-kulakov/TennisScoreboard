package model.score.points;

import model.score.BaseScore;
import model.score.EnumPlayer;

public abstract class AbstractPoints extends BaseScore {
    protected abstract int getWinningPoint();
    protected abstract int getWinningPointDifference();

    void update(EnumPlayer pointWinner) {
        addPoint(pointWinner);

        if (isWinningPointGreaterThanPlayersPoints(getWinningPoint())) {
            return;
        }

        if (!isThereWinningPointDifference(getWinningPointDifference())) {
            return;
        }

        setWinner();
    }
}
