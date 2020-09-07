package by.gsu.epamlab.controllers;

import by.gsu.epamlab.enums.StateEnum;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.ifaces.TaskDao;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;

import java.sql.Date;
import java.util.List;

public abstract class GetTasksDateServlet extends GetTasksServlet {

    @Override
    protected List<Task> getTasks(User user, TaskDao taskDao) throws DaoException {
        return taskDao.getTasksByDateByUserByState(getDate(), user.getId(), StateEnum.ACTIVE);
    }

    protected abstract Date getDate();
}
