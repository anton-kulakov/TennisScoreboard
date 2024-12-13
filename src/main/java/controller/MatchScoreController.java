package controller;

import dao.MatchDAO;
import entity.Match;
import exception.AppException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.score.EnumPlayer;
import model.score.MatchResult;
import service.MatchResultService;
import service.OngoingMatchesService;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet("/match-score")
public class MatchScoreController extends AbstractMainController {
    MatchDAO matchDAO;
    OngoingMatchesService ongoingMatchesService;
    MatchResultService matchResultService;
    public void init(ServletConfig config) {
        matchDAO = (MatchDAO) config.getServletContext().getAttribute("matchDAO");
        ongoingMatchesService = (OngoingMatchesService) config.getServletContext().getAttribute("ongoingMatchesService");
        matchResultService = (MatchResultService) config.getServletContext().getAttribute("matchResultService");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");

        if (uuid.isBlank()) {
            throw new AppException(SC_BAD_REQUEST, "Match UUID is empty");
        }

        Match ongoingMatch = ongoingMatchesService.get(uuid)
                .orElseThrow(() -> new AppException(SC_NOT_FOUND, "The match with UUID %s was not found".formatted(uuid)));

        req.setAttribute("match", ongoingMatch);
        req.setAttribute("uuid", uuid);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String uuid = req.getParameter("uuid");
        String playerName = req.getParameter("playerName");

        if (uuid.isBlank()) {
            throw new AppException(SC_BAD_REQUEST, "Match UUID is empty");
        }

        if (playerName.isBlank()) {
            throw new AppException(SC_BAD_REQUEST, "Player's name is empty");
        }

        Match ongoingMatch = ongoingMatchesService.get(uuid)
                .orElseThrow(() -> new AppException(SC_NOT_FOUND, "The match with UUID %s was not found".formatted(uuid)));

        EnumPlayer pointWinner;

        if (playerName.equals(ongoingMatch.getPlayer1().getName())) {
            pointWinner = EnumPlayer.FIRST_PLAYER;
        } else {
            pointWinner = EnumPlayer.SECOND_PLAYER;
        }

        ongoingMatch.getMatchScore().update(pointWinner);

        if (matchResultService.isMatchFinished(ongoingMatch)) {
            matchResultService.setMatchResult(ongoingMatch);

            MatchResult matchResult = ongoingMatch.getMatchScore().getMatchResult();
            matchDAO.merge(ongoingMatch);
            ongoingMatchesService.remove(uuid);

            req.setAttribute("matchResult", matchResult);
            req.getRequestDispatcher("/completed-match.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect("match-score?uuid=" + uuid);
    }
}
