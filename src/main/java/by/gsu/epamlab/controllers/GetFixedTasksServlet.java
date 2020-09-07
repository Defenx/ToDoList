package by.gsu.epamlab.controllers;

import by.gsu.epamlab.enums.StateEnum;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.ifaces.TaskDao;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/tasks/fixed")
public class GetFixedTasksServlet extends GetTasksServlet {
    @Override
    protected List<Task> getTasks(User user, TaskDao taskDao) throws DaoException {
        return taskDao.getTasksByUserByState(user.getId(), StateEnum.FIXED);
    }
}
