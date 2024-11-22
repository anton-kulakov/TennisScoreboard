package model;

import model.point.CurrentPoint;

public class GameScore extends BaseScore {
    private final CurrentPoint currentPoint;
    private static final int WINNING_POINT = 6;
    private int winningPointDifference = 2;
    public GameScore(CurrentPoint currentPoint) {
        this.currentPoint = currentPoint;
    }
    void update(EnumPlayer pointWinner) {
        if (isPointsEqualConstantPoint(WINNING_POINT)) {
            currentPoint.setTiebreak(true);
            winningPointDifference = 1;
        }

        currentPoint.update(pointWinner);

        if (currentPoint.getOptionalWinner().isPresent()) {
            addPoint(currentPoint.getOptionalWinner().get());
            currentPoint.reset();
        }

        if (isPointsLessThanWinningPoint(WINNING_POINT)) {
            return;
        }

        if (!isThereWinningPointDifference(winningPointDifference)) {
            return;
        }

        setWinner();
    }

    public void resetWinningPointDifference() {
        winningPointDifference = 2;
    }
}
