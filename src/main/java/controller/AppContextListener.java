package controller;

import dao.PlayerDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import service.NewMatchService;
import service.OngoingMatchesService;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        PlayerDAO playerDAO = PlayerDAO.getInstance();

        servletContext.setAttribute("playerDAO", playerDAO);
        servletContext.setAttribute("newMatchService", new NewMatchService(playerDAO));
        servletContext.setAttribute("ongoingMatchesService", new OngoingMatchesService());
    }
}
