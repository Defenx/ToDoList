package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.ifaces.TaskDao;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.utils.GsonFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public abstract class GetTasksServlet extends TasksServlet {
    @Override
    protected void doAction(User user, TaskDao taskDao, HttpServletRequest request, HttpServletResponse response) throws DaoException, IOException {
        taskDao.updateDate(user.getId());
        List<Task> tasks = getTasks(user, taskDao);

        PrintWriter out = response.getWriter();

        out.print(GsonFactory.getInstance().toJson(tasks));
        out.flush();

    }

    protected abstract List<Task> getTasks(User user, TaskDao taskDao) throws DaoException;
}
