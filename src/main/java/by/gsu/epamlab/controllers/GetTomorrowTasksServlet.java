package by.gsu.epamlab.controllers;

import org.apache.commons.lang.time.DateUtils;

import javax.servlet.annotation.WebServlet;
import java.sql.Date;

@WebServlet("/tasks/tomorrow")
public class GetTomorrowTasksServlet extends GetTasksDateServlet {

    @Override
    protected Date getDate() {
        return new Date(DateUtils.addDays(new java.util.Date(), 1).getTime());
    }
}
