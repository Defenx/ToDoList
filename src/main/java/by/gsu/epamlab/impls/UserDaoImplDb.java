package by.gsu.epamlab.impls;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.ConstantsSql;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.ifaces.UserDao;
import by.gsu.epamlab.managers.ConfigurationManager;
import by.gsu.epamlab.managers.MessageManager;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.UserFactory;
import by.gsu.epamlab.utils.ServiceDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImplDb implements UserDao {
    @Override
    public Optional<User> addUser(String login, String pass) throws DaoException {

        Optional<User> optionalUser = Optional.empty();

        try (Connection connection = ServiceDb.getConnection();
             PreparedStatement pInsertUser = connection.prepareStatement(ConstantsSql.ADD_USER)) {
            if (!getUserByLogin(login).isPresent()) {
                pInsertUser.setString(1, login);
                pInsertUser.setString(2, pass);
                synchronized (this) {
                    pInsertUser.executeUpdate();
                    optionalUser = getUser(login, pass);
                }

            }

        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(Constants.DAO_PROBLEM_MSG));
        }

        return optionalUser;
    }


    @Override
    public Optional<User> getUser(String login, String pass) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = ServiceDb.getConnection();
             PreparedStatement pSelectUser = connection.prepareStatement(ConstantsSql.SELECT_USER)) {
            pSelectUser.setString(1, login);
            pSelectUser.setString(2, pass);
            synchronized (this) {
                ResultSet rs = pSelectUser.executeQuery();
                if (rs.next()) {
                    optionalUser = Optional.of(UserFactory.createUser(rs.getInt(ConfigurationManager.getProperty(Constants.PARAM_NAME_USER_ID)),
                            rs.getString(ConfigurationManager.getProperty(Constants.PARAM_NAME_USER_LOGIN_)),
                            rs.getString(ConfigurationManager.getProperty(Constants.PARAM_NAME_USER_PASS)))
                    );
                }
            }

        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(Constants.DAO_PROBLEM_MSG));
        }
        return optionalUser;
    }

    @Override
    public Optional<User> getUserByLogin(String login) throws DaoException {
        Optional<User> result = Optional.empty();

        try (Connection connection = ServiceDb.getConnection();
             PreparedStatement pSelectUser = connection.prepareStatement(ConstantsSql.SELECT_USERS_BY_NAME)) {
            pSelectUser.setString(1, login);
            synchronized (this) {
                ResultSet rs = pSelectUser.executeQuery();
                if (rs.next()) {
                    result = Optional.of(UserFactory.createUser(rs.getInt(ConfigurationManager.getProperty(Constants.PARAM_NAME_USER_ID)),
                            rs.getString(ConfigurationManager.getProperty(Constants.PARAM_NAME_USER_LOGIN_)),
                            rs.getString(ConfigurationManager.getProperty(Constants.PARAM_NAME_USER_PASS)))
                    );
                }
            }


        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(Constants.DAO_PROBLEM_MSG));
        }

        return result;
    }

}
