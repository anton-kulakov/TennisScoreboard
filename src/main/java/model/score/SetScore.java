package model.score;

public class SetScore extends BaseScore {
    private final GameScore gameScore;
    private final MatchResult matchResult;
    private int setCounter = 1;
    private static final int WINNING_POINT = 2;
    public SetScore(GameScore gameScore, MatchResult matchResult) {
        this.gameScore = gameScore;
        this.matchResult = matchResult;
    }
    void update(EnumPlayer pointWinner) {
        gameScore.update(pointWinner);

        if (gameScore.getOptionalWinner().isPresent()) {
            addPoint(gameScore.getOptionalWinner().get());
            saveSetResult();
            gameScore.resetWinningPointDifference();
            gameScore.reset();
        }

        if (arePointsLessThanWinningPoint(WINNING_POINT)) {
            return;
        }

        setWinner();
    }

    private void saveSetResult() {
        if (setCounter == 1) {
            matchResult.getFirstSet().put("firstPlayer", gameScore.getFirstPlayerPoints());
            matchResult.getFirstSet().put("secondPlayer", gameScore.getSecondPlayerPoints());
        }

        if (setCounter == 2) {
            matchResult.getSecondSet().put("firstPlayer", gameScore.getFirstPlayerPoints());
            matchResult.getSecondSet().put("secondPlayer", gameScore.getSecondPlayerPoints());
        }

        if (setCounter == 3) {
            matchResult.getThirdSet().put("firstPlayer", gameScore.getFirstPlayerPoints());
            matchResult.getThirdSet().put("secondPlayer", gameScore.getSecondPlayerPoints());
        }

        setCounter++;
    }
}
