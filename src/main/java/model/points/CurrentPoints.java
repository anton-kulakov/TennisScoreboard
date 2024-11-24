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
            updatePoints(tiebreakRulePoints, pointWinner);
            refreshCurrentPoints(tiebreakRulePoints);
            return;
        }

        if (isDeuce) {
            updatePoints(deuceRulePoints, pointWinner);

            if (arePointsEqualDeucePoint(deuceRulePoints.getFirstPlayerPoints(), deuceRulePoints.getSecondPlayerPoints())) {
                deuceRulePoints.reset();
            }

            refreshCurrentPoints(deuceRulePoints);
            return;
        }

        updatePoints(regularRulePoints, pointWinner);
        refreshCurrentPoints(regularRulePoints);

        if (arePointsEqualDeuceScorePoint()) {
            isDeuce = true;
        }
    }

    private void refreshCurrentPoints(AbstractPoints points) {
        firstPlayerPoints = points.getFirstPlayerPoints();
        secondPlayerPoints = points.getSecondPlayerPoints();
    }

    private void updatePoints(AbstractPoints points, EnumPlayer pointWinner) {
        points.update(pointWinner);

        if (points.getOptionalWinner().isPresent()) {
            winner = points.getOptionalWinner().get();
            resetOtherRulePoints(points);
            resetStatus(points);
        }
    }

    private void resetStatus(AbstractPoints updatedPoints) {
        if (updatedPoints.equals(tiebreakRulePoints)) {
            isTiebreak = false;
        }

        if (updatedPoints.equals(deuceRulePoints)) {
            isDeuce = false;
        }
    }
    private void resetOtherRulePoints(AbstractPoints updatedPoints) {
        if (updatedPoints.equals(tiebreakRulePoints)) {
            tiebreakRulePoints.reset();
        }

        if (updatedPoints.equals(deuceRulePoints)) {
            deuceRulePoints.reset();
            regularRulePoints.reset();
        }

        if (updatedPoints.equals(regularRulePoints)) {
            regularRulePoints.reset();
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
