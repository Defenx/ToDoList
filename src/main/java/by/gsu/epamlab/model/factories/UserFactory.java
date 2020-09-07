package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.beans.User;

public class UserFactory {

    public static User createUser(int id, String login, String pass) {
        return new User(id, login, pass);
    }


}
