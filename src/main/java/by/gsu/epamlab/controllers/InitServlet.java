package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exceptions.InitException;
import by.gsu.epamlab.factories.LoggerFactory;
import by.gsu.epamlab.factories.TaskDaoFactory;
import by.gsu.epamlab.factories.UserDaoFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "init", urlPatterns = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    @Override
    public void init(ServletConfig sc) throws ServletException {

        try {
            UserDaoFactory.init();
            TaskDaoFactory.init();
            LoggerFactory.init();
        } catch (InitException e) {
            throw new ServletException(e);
        }
    }

}
