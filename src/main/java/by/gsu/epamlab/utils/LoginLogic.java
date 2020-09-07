package by.gsu.epamlab.utils;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.UserDaoFactory;
import by.gsu.epamlab.ifaces.UserDao;
import by.gsu.epamlab.model.beans.User;

import java.util.Optional;

public class LoginLogic {

    public static Optional<User> checkLogin(String enterLogin, String enterPass) throws DaoException {
        UserDao userDao = UserDaoFactory.getClassFromFactory();
        return userDao.getUser(enterLogin, enterPass);
    }
}

