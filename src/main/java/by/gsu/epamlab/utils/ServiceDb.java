package by.gsu.epamlab.utils;

import by.gsu.epamlab.exceptions.InitException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ServiceDb {

    private static DataSource dataSource;

    public static void init() throws InitException {
        if (dataSource == null) {
            try {
                Context context = new InitialContext();
                dataSource = (DataSource) context.lookup("java:comp/env/jdbc/jsWeb2");
            } catch (NamingException e) {
                throw new InitException(e);
            }
        }

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
