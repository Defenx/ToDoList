package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.ifaces.TaskDao;

import javax.servlet.annotation.WebServlet;

@WebServlet("/moveActiveToRecBin")
public class MoveActiveToRecBinServlet extends ModifyTasksServlet {
    @Override
    protected void modify(TaskDao taskDao, int[] idTasks) throws DaoException {
        taskDao.moveActiveTasksToRecycleBin(idTasks);
    }
}
