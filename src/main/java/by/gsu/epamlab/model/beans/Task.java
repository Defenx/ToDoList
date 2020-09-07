package by.gsu.epamlab.model.beans;

import java.sql.Date;

public class Task {

    private final int id;
    private final String description;
    private final Date date;
    private final int userId;

    public Task(int id, String description, Date date, int userId) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }
}
