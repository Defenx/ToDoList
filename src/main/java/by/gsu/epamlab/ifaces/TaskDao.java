package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.enums.StateEnum;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.beans.Task;

import java.sql.Date;
import java.util.List;

public interface TaskDao {
    List<Task> getTasksByDateByUserByState(Date date, int userId, StateEnum state) throws DaoException;

    List<Task> getTasksByUserByState(int id, StateEnum state) throws DaoException;

    void addTask(int id, String description, Date date) throws DaoException;

    void deleteTasksFromBin(int[] idTasks) throws DaoException;

    void moveActiveTasksToRecycleBin(int[] idTasks) throws DaoException;

    void moveFixedTasksToRecycleBin(int[] idTasks) throws DaoException;

    void fixTasks(int[] idTasks) throws DaoException;

    void restoreTasks(int[] idTasks) throws DaoException;

    void updateDate(int userId) throws DaoException;
}
