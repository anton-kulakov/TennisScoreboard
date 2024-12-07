package model;

import entity.Match;
import lombok.Getter;

import java.util.List;

@Getter
public class Page {
    private final List<Match> matches;
    private final int totalPagesNumber;
    private final int pageNumber;
    private final String playerName;
    public Page(List<Match> matches, int pageNumber, int totalPagesNumber) {
        this.matches = matches;
        this.pageNumber = pageNumber;
        this.totalPagesNumber = totalPagesNumber;
        this.playerName = "";
    }

    public Page(List<Match> matches, int pageNumber, int totalPagesNumber, String playerName) {
        this.matches = matches;
        this.pageNumber = pageNumber;
        this.totalPagesNumber = totalPagesNumber;
        this.playerName = playerName;
    }
}
