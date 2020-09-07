package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.managers.ConfigurationManager;
import by.gsu.epamlab.model.beans.Task;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskFactory {

    public static Task createTask(int id, String description, Date date, int userId) {
        return new Task(id, description, date, userId);
    }

    public static Task createTask(ResultSet rs) throws SQLException {
        return new Task(
                rs.getInt(ConfigurationManager.getProperty(Constants.PARAM_NAME_TASK_ID)),
                rs.getString(ConfigurationManager.getProperty(Constants.PARAM_NAME_TASK_DESCRIPTION)),
                rs.getDate(ConfigurationManager.getProperty(Constants.PARAM_NAME_TASK_DATE)),
                rs.getInt(ConfigurationManager.getProperty(Constants.PARAM_NAME_TASK_USER_ID))
        );
    }


}
