package model;

public class SetScore extends BaseScore {
    private final GameScore gameScore;
    private final int WINNING_POINT = 2;
    public SetScore(GameScore gameScore) {
        this.gameScore = gameScore;
    }
    void update(EnumPlayer pointWinner) {
        gameScore.update(pointWinner);

        if (gameScore.getOptionalWinner().isPresent()) {
            addPoint(gameScore.getOptionalWinner().get());
            gameScore.resetWinningPointDifference();
            gameScore.reset();
        }

        if (isPointsLessThanWinningPoint()) {
            return;
        }

        setWinner();
    }

    private boolean isPointsLessThanWinningPoint() {
        return firstPlayerPoints < WINNING_POINT && secondPlayerPoints < WINNING_POINT;
    }
}
