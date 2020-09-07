package by.gsu.epamlab.utils;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.UserDaoFactory;
import by.gsu.epamlab.ifaces.UserDao;
import by.gsu.epamlab.model.beans.User;

import java.util.Optional;

public class RegisterLogic {
    public static Optional<User> register(String login, String pass) throws DaoException {
        UserDao userDao = UserDaoFactory.getClassFromFactory();
        return userDao.addUser(login, pass);
    }
}
