package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.utils.RegisterLogic;

import javax.servlet.annotation.WebServlet;
import java.util.Optional;

@WebServlet("/register")
public class UserRegisterServlet extends UserServlet {

    @Override
    protected Optional<User> getUser(String login, String pass) throws DaoException {
        return RegisterLogic.register(login, pass);
    }

    @Override
    protected int getErrCode() {
        return 452;
    }


}
