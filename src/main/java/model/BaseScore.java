package model;

import lombok.Getter;

import java.util.Optional;

public class BaseScore {
    @Getter
    protected int firstPlayerPoints;
    @Getter
    protected int secondPlayerPoints;
    protected EnumPlayer winner;
    protected void addPoint(EnumPlayer pointWinner) {
        if (pointWinner.equals(EnumPlayer.FIRST_PLAYER)) {
            firstPlayerPoints++;
        } else {
            secondPlayerPoints++;
        }
    }

    protected void setWinner() {
        winner = (firstPlayerPoints > secondPlayerPoints) ? EnumPlayer.FIRST_PLAYER : EnumPlayer.SECOND_PLAYER;
    }

    public Optional<EnumPlayer> getOptionalWinner() {
        return Optional.ofNullable(this.winner);
    }

}
