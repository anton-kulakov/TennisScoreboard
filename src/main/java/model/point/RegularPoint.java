package model.point;

public class RegularPoint extends BaseGamePoint {
    @Override
    protected int getWinningPoint() {
        return 4;
    }

    @Override
    protected int getWinningPointDifference() {
        return 2;
    }
}