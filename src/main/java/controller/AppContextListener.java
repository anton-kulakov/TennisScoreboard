package controller;

import dao.MatchDAO;
import dao.PlayerDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import service.*;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        PlayerDAO playerDAO = new PlayerDAO();
        MatchDAO matchDAO = new MatchDAO();

        servletContext.setAttribute("matchDAO", matchDAO);
        servletContext.setAttribute("newMatchService", new NewMatchService(playerDAO));
        servletContext.setAttribute("ongoingMatchesService", new OngoingMatchesService());
        servletContext.setAttribute("matchResultService", new MatchResultService());
        servletContext.setAttribute("finishedMatchesPersistenceService", new FinishedMatchesPersistenceService(playerDAO, matchDAO));
    }
}
