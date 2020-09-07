package by.gsu.epamlab.controllers;

import by.gsu.epamlab.ConstantsJsp;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.ifaces.TaskDao;
import by.gsu.epamlab.model.beans.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/addTask")
public class AddTaskServlet extends TasksServlet {
    @Override
    protected void doAction(User user, TaskDao taskDao, HttpServletRequest request, HttpServletResponse response) throws DaoException, IOException {
        taskDao.addTask(user.getId(), request.getParameter(ConstantsJsp.PARAM_NAME_TASK_DESCRIPTION), Date.valueOf(request.getParameter(ConstantsJsp.PARAM_NAME_TASK_DATE)));
    }
}
