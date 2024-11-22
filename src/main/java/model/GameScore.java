package model;

import model.points.CurrentPoints;

public class GameScore extends BaseScore {
    private final CurrentPoints currentPoints;
    private static final int WINNING_POINT = 6;
    private int winningPointDifference = 2;
    public GameScore(CurrentPoints currentPoints) {
        this.currentPoints = currentPoints;
    }
    void update(EnumPlayer pointWinner) {
        if (arePointsEqualConstantPoint(WINNING_POINT)) {
            currentPoints.setTiebreak(true);
            winningPointDifference = 1;
        }

        currentPoints.update(pointWinner);

        if (currentPoints.getOptionalWinner().isPresent()) {
            addPoint(currentPoints.getOptionalWinner().get());
            currentPoints.reset();
        }

        if (arePointsLessThanWinningPoint(WINNING_POINT)) {
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
