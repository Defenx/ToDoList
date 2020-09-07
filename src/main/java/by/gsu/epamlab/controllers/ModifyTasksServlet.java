package by.gsu.epamlab.controllers;

import by.gsu.epamlab.ConstantsJsp;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.ifaces.TaskDao;
import by.gsu.epamlab.model.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public abstract class ModifyTasksServlet extends TasksServlet {
    @Override
    protected void doAction(User user, TaskDao taskDao, HttpServletRequest request, HttpServletResponse response) throws DaoException, IOException {
        int[] idTasks = Arrays.stream(request.getParameterValues(ConstantsJsp.PARAM_ID_TASK)).mapToInt(Integer::valueOf).toArray();
        modify(taskDao, idTasks);
    }

    protected abstract void modify(TaskDao taskDao, int[] idTasks) throws DaoException;
}
