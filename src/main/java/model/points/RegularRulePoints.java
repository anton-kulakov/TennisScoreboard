package model.points;

public class RegularRulePoints extends AbstractPoints {
    @Override
    protected int getWinningPoint() {
        return 4;
    }

    @Override
    protected int getWinningPointDifference() {
        return 2;
    }
}