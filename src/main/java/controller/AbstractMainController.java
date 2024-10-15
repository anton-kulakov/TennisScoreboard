package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class AbstractMainController extends HttpServlet {
    protected ObjectMapper objectMapper;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            handleGet(req, resp);
        } catch (Exception e) {
            sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Fatal error", resp);
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

    abstract protected void handleGet(HttpServletRequest req, HttpServletResponse resp);
}
