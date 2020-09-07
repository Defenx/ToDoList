package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.ifaces.TaskDao;

import javax.servlet.annotation.WebServlet;

@WebServlet("/fixTasks")
public class FixTasksServlet extends ModifyTasksServlet {
    @Override
    protected void modify(TaskDao taskDao, int[] idTasks) throws DaoException {
        taskDao.fixTasks(idTasks);
    }
}
