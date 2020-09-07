package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.beans.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> addUser(String login, String pass) throws DaoException;

    Optional<User> getUser(String login, String pass) throws DaoException;

    Optional<User> getUserByLogin(String login) throws DaoException;
}
