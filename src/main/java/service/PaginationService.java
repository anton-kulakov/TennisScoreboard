package service;

import dao.MatchDAO;
import model.Page;

import java.util.Optional;

public class PaginationService {
    private final static int DEFAULT_PAGE_NUMBER = 1;
    private final static int DEFAULT_MATCHES_PER_PAGE_NUMBER = 5;

    public Page getPage(String stringPage, String playerName, MatchDAO matchDAO) {
        int currentPageNumber = DEFAULT_PAGE_NUMBER;
        int matchesPerPageNumber = DEFAULT_MATCHES_PER_PAGE_NUMBER;
        Optional<String> stringCurrentPageNumber = Optional.ofNullable(stringPage);

        if (stringCurrentPageNumber.isPresent() && !stringCurrentPageNumber.get().isBlank()) {
            currentPageNumber = Integer.parseInt(stringCurrentPageNumber.get());
        }

        Page page;

        if (playerName.isBlank()) {
            page = matchDAO.getAll(currentPageNumber, matchesPerPageNumber);
        } else {
            page = matchDAO.getByName(currentPageNumber, matchesPerPageNumber, playerName);
        }

        return page;
    }
}
