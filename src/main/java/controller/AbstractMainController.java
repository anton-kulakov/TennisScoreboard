package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public abstract class AbstractMainController extends HttpServlet {
    protected ObjectMapper objectMapper;
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            super.service(req, resp);
        } catch (AppException e) {
            sendError(e.code, e.message, resp);
        } catch (HibernateException e) {
            sendError(SC_INTERNAL_SERVER_ERROR, "Something happened with the database. Please try again later!", resp);
        } catch (Exception e) {
            sendError(SC_INTERNAL_SERVER_ERROR, "Fatal error", resp);
        }
    }

    private void sendError(int code, String message, HttpServletResponse resp) {
        try {
            resp.setStatus(code);
            resp.getWriter().println();
            resp.getWriter().println(objectMapper.createObjectNode().put("message", message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
