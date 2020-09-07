package by.gsu.epamlab.controllers;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.ConstantsJsp;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.LoggerFactory;
import by.gsu.epamlab.factories.TaskDaoFactory;
import by.gsu.epamlab.ifaces.TaskDao;
import by.gsu.epamlab.managers.MessageManager;
import by.gsu.epamlab.model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class TasksServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute(ConstantsJsp.ATTRIBUTE_NAME_USER);
        TaskDao taskDao = TaskDaoFactory.getClassFromFactory();
        try {
            doAction(user, taskDao, request, response);
        } catch (DaoException e) {
            Logger logger = LoggerFactory.getClassFromFactory();
            logger.log(Level.SEVERE, MessageManager.getProperty(Constants.DAO_PROBLEM_MSG), e);
            response.sendError(523);
        }
    }

    protected abstract void doAction(User user, TaskDao taskDao, HttpServletRequest request, HttpServletResponse response) throws DaoException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
