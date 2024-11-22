package model.point;

public class DeucePoint extends BaseGamePoint {
    @Override
    protected int getWinningPoint() {
        return 2;
    }

    @Override
    protected int getWinningPointDifference() {
        return 2;
    }
}
