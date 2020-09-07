package by.gsu.epamlab.controllers;

import javax.servlet.annotation.WebServlet;
import java.sql.Date;

@WebServlet("/tasks/today")
public class GetTodayTasksServlet extends GetTasksDateServlet {
    @Override
    protected Date getDate() {
        return new Date(System.currentTimeMillis());
    }
}
