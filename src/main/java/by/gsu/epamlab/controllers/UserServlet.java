package by.gsu.epamlab.controllers;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.LoggerFactory;
import by.gsu.epamlab.managers.MessageManager;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.utils.GsonFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.ConstantsJsp.*;

public abstract class UserServlet extends HttpServlet {

    protected abstract Optional<User> getUser(String login, String pass) throws DaoException;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASS);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Optional<User> optionalUser = getUser(login, pass);
            if (optionalUser.isPresent()) {
                request.getSession().setAttribute(ATTRIBUTE_NAME_USER, optionalUser.get());
                out.print(GsonFactory.getInstance().toJson(optionalUser.get()));
                out.flush();
            } else {
                response.sendError(getErrCode());
            }
        } catch (DaoException e) {
            Logger logger = LoggerFactory.getClassFromFactory();
            logger.log(Level.SEVERE, MessageManager.getProperty(Constants.DAO_PROBLEM_MSG), e);
            response.sendError(523);
        }


    }

    protected abstract int getErrCode();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
