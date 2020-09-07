package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.utils.LoginLogic;

import javax.servlet.annotation.WebServlet;
import java.util.Optional;

@WebServlet("/login")
public class UserLoginServlet extends UserServlet {

    @Override
    protected Optional<User> getUser(String login, String pass) throws DaoException {
        return LoginLogic.checkLogin(login, pass);
    }

    @Override
    protected int getErrCode() {
        return 451;
    }

}
