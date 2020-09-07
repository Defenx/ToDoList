package by.gsu.epamlab.impls;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.ifaces.UserDao;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.UserFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImplMemory implements UserDao {

    private List<User> users = initUsers();

    private static List<User> initUsers() {


        List<User> result = new ArrayList<>();

        result.add(UserFactory.createUser(1, "root", "root"));
        result.add(UserFactory.createUser(2, "evgen", "evgen"));

        return result;
    }

    @Override
    public Optional<User> addUser(String login, String pass) throws DaoException {

        Optional<User> optionalUser = Optional.empty();

        if (!getUserByLogin(login).isPresent()) {
            User user = UserFactory.createUser(users.size() + 1, login, pass);
            synchronized (this) {
                users.add(user);
                optionalUser = Optional.of(user);
            }
        }

        return optionalUser;
    }

    @Override
    public Optional<User> getUser(String login, String pass) throws DaoException {
        Optional<User> optionalUser;

        synchronized (this) {
            optionalUser = users.stream().filter(user -> user.getLogin().equals(login) && user.getPass().equals(pass)).findFirst();
        }

        return optionalUser;
    }

    @Override
    public Optional<User> getUserByLogin(String login) throws DaoException {
        Optional<User> optionalUser;

        synchronized (this) {
            optionalUser = users.stream().filter(user -> user.getLogin().equals(login)).findFirst();
        }

        return optionalUser;
    }
}
