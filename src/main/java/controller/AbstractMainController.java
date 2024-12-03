package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.AppException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import util.PlayerNameValidator;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public abstract class AbstractMainController extends HttpServlet {
    protected static ObjectMapper objectMapper;
    protected static PlayerNameValidator playerNameValidator;

    static {
        objectMapper = new ObjectMapper();
        playerNameValidator = new PlayerNameValidator();
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            super.service(req, resp);
        } catch (AppException e) {
            sendError(e.code, e.message, req, resp);
        } catch (HibernateException e) {
            sendError(SC_INTERNAL_SERVER_ERROR, "Something happened with the database. Please try again later!", req, resp);
        } catch (Exception e) {
            sendError(SC_INTERNAL_SERVER_ERROR, "Fatal error", req, resp);
        }
    }

    private void sendError(int code, String message, HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("errorMessage", message);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            resp.setStatus(code);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
