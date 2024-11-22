package model.points;

import lombok.Getter;
import lombok.Setter;
import model.EnumPlayer;

import java.util.Optional;

public class CurrentPoints {
    private final RegularRulePoints regularRulePoints;
    private final TiebreakRulePoints tiebreakRulePoints;
    private final DeuceRulePoints deuceRulePoints;
    @Getter
    private int firstPlayerPoints;
    @Getter
    private int secondPlayerPoints;
    @Setter
    private boolean isTiebreak;
    private boolean isDeuce;
    private EnumPlayer winner;
    private static final int DEUCE_POINT = 1;
    private static final int DEUCE_SCORE_POINT = 3;
    public CurrentPoints(RegularRulePoints regularRulePoints, TiebreakRulePoints tiebreakRulePoints, DeuceRulePoints deuceRulePoints) {
        this.regularRulePoints = regularRulePoints;
        this.tiebreakRulePoints = tiebreakRulePoints;
        this.deuceRulePoints = deuceRulePoints;
    }

    public void update(EnumPlayer pointWinner) {
        if (isTiebreak) {
            tiebreakRulePoints.update(pointWinner);

            if (tiebreakRulePoints.getOptionalWinner().isPresent()) {
                winner = tiebreakRulePoints.getOptionalWinner().get();
                tiebreakRulePoints.reset();
                isTiebreak = false;
            }

            firstPlayerPoints = tiebreakRulePoints.getFirstPlayerPoints();
            secondPlayerPoints = tiebreakRulePoints.getSecondPlayerPoints();
        } else if (isDeuce) {
            deuceRulePoints.update(pointWinner);

            if (deuceRulePoints.getOptionalWinner().isPresent()) {
                winner = deuceRulePoints.getOptionalWinner().get();
                deuceRulePoints.reset();
                regularRulePoints.reset();
                isDeuce = false;
            }

            if (arePointsEqualDeucePoint(deuceRulePoints.getFirstPlayerPoints(), deuceRulePoints.getSecondPlayerPoints())) {
                deuceRulePoints.reset();
            }

            firstPlayerPoints = deuceRulePoints.getFirstPlayerPoints();
            secondPlayerPoints = deuceRulePoints.getSecondPlayerPoints();
        } else {
            regularRulePoints.update(pointWinner);

            if (regularRulePoints.getOptionalWinner().isPresent()) {
                winner = regularRulePoints.getOptionalWinner().get();
                regularRulePoints.reset();
            }

            firstPlayerPoints = regularRulePoints.getFirstPlayerPoints();
            secondPlayerPoints = regularRulePoints.getSecondPlayerPoints();

            if (arePointsEqualDeuceScorePoint()) {
                isDeuce = true;
            }
        }
    }

    private boolean arePointsEqualDeuceScorePoint() {
        return DEUCE_SCORE_POINT == firstPlayerPoints && DEUCE_SCORE_POINT == secondPlayerPoints;
    }

    private boolean arePointsEqualDeucePoint(int deuceFirstPlayerPoint, int deuceSecondPlayerPoint) {
        return DEUCE_POINT == deuceFirstPlayerPoint && DEUCE_POINT == deuceSecondPlayerPoint;
    }
    public Optional<EnumPlayer> getOptionalWinner() {
        return Optional.ofNullable(this.winner);
    }

    public void reset() {
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
        winner = null;
    }
}
