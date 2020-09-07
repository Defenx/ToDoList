package by.gsu.epamlab;

public class ConstantsSql {
    public static final String SELECT_USER = "SELECT id,login,pass FROM users WHERE login = ? AND pass = ?";
    public static final String ADD_USER = "INSERT INTO users(login,pass) VALUES(?,?)";
    public static final String SELECT_USERS_BY_NAME = "SELECT id,login,pass FROM users WHERE login = ?";
    public static final String SELECT_TASKS_BY_USER_ID_BY_DATE = "SELECT * FROM tasks WHERE userId = ? AND date = ? AND stateId = ?";
    public static final String SELECT_TASKS_BY_USER_ID = "SELECT * FROM tasks WHERE userId = ? AND stateId = ?";
    public static final String SET_TASK_STATE_BY_ID = "UPDATE tasks SET stateId = ? WHERE id = ?";
    public static final String ADD_TASK = "INSERT INTO tasks(userId,description,date) VALUES(?,?,?)";
    public static final String DELETE_TASK_BY_ID = "DELETE FROM tasks WHERE id = ?";
    public static final String CALL_RESTORE_PROCEDURE = "{call setTaskStateWithCheckState(?)}";
    public static final String UPDATE_ACTIVE_TASKS_DATE = "UPDATE tasks SET date = CURDATE() WHERE userId = ? AND stateId = 1 AND date < CURDATE()";
}
