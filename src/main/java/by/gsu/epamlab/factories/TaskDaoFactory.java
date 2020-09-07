package by.gsu.epamlab.factories;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.exceptions.InitException;
import by.gsu.epamlab.ifaces.TaskDao;
import by.gsu.epamlab.impls.TaskDaoImplDb;
import by.gsu.epamlab.managers.ConfigurationManager;
import by.gsu.epamlab.utils.ServiceDb;

public class TaskDaoFactory {

    private static TaskDao taskDao = null;

    public static TaskDao getClassFromFactory() {
        return taskDao;
    }

    public static void init() throws InitException {
        taskDao = SourceType.valueOf(ConfigurationManager.getProperty(Constants.TASKS_SOURCE).toUpperCase()).getImpl();
    }

    private static enum SourceType {
        DB {
            @Override
            public TaskDao getImpl() throws InitException {
                ServiceDb.init();
                return new TaskDaoImplDb();
            }
        };

        public abstract TaskDao getImpl() throws InitException;
    }

}
