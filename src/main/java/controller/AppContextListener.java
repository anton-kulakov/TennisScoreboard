package controller;

import dao.MatchDAO;
import dao.PlayerDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import service.MatchResultService;
import service.NewMatchService;
import service.OngoingMatchesService;

import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        MatchDAO matchDAO = new MatchDAO();

        servletContext.setAttribute("matchDAO", matchDAO);
        servletContext.setAttribute("newMatchService", new NewMatchService(new PlayerDAO()));
        servletContext.setAttribute("ongoingMatchesService", new OngoingMatchesService(new ConcurrentHashMap<>()));
        servletContext.setAttribute("matchResultService", new MatchResultService());
    }
}
