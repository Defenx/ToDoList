package by.gsu.epamlab.controllers;

import by.gsu.epamlab.enums.StateEnum;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.ifaces.TaskDao;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/tasks/recycleBin")
public class GetRecycleBinTasksServlet extends GetTasksServlet {

    @Override
    protected List<Task> getTasks(User user, TaskDao taskDao) throws DaoException {
        List<Task> tasks = taskDao.getTasksByUserByState(user.getId(), StateEnum.BIN);
        tasks.addAll(taskDao.getTasksByUserByState(user.getId(), StateEnum.BIN_FIXED));
        return tasks;
    }
}
