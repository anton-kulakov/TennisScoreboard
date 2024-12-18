package model.score;

import model.score.points.CurrentPoints;

public class GameScore extends BaseScore {
    private final CurrentPoints currentPoints;
    private static final int WINNING_POINT = 6;
    private int winningPointDifference = 2;

    public GameScore(CurrentPoints currentPoints) {
        this.currentPoints = currentPoints;
    }

    public void resetWinningPointDifference() {
        winningPointDifference = 2;
    }

    void update(EnumPlayer pointWinner) {
        if (isConstantPointEqualPlayersPoints(WINNING_POINT)) {
            currentPoints.setTiebreak(true);
            winningPointDifference = 1;
        }

        currentPoints.update(pointWinner);

        if (currentPoints.getOptionalWinner().isPresent()) {
            addPoint(currentPoints.getOptionalWinner().get());
            currentPoints.reset();
        }

        if (isWinningPointGreaterThanPlayersPoints(WINNING_POINT)) {
            return;
        }

        if (!isThereWinningPointDifference(winningPointDifference)) {
            return;
        }

        setWinner();
    }
}
