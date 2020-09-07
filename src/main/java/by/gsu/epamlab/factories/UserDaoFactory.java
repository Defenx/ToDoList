package by.gsu.epamlab.factories;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.exceptions.InitException;
import by.gsu.epamlab.ifaces.UserDao;
import by.gsu.epamlab.impls.UserDaoImplDb;
import by.gsu.epamlab.impls.UserDaoImplMemory;
import by.gsu.epamlab.managers.ConfigurationManager;
import by.gsu.epamlab.utils.ServiceDb;

public class UserDaoFactory {

    private static UserDao userDao = null;

    public static UserDao getClassFromFactory() {
        return userDao;
    }

    public static void init() throws InitException {
        userDao = SourceType.valueOf(ConfigurationManager.getProperty(Constants.USERS_SOURCE).toUpperCase()).getImpl();
    }


    private enum SourceType {
        DB {
            @Override
            public UserDao getImpl() throws InitException {
                ServiceDb.init();
                return new UserDaoImplDb();
            }
        },
        MEMORY {
            @Override
            public UserDao getImpl() throws InitException {
                return new UserDaoImplMemory();
            }
        };

        public abstract UserDao getImpl() throws InitException;
    }
}
