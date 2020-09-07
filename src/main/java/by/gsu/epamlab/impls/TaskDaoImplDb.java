package by.gsu.epamlab.impls;

import by.gsu.epamlab.ConstantsSql;
import by.gsu.epamlab.enums.StateEnum;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.ifaces.TaskDao;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.utils.ServiceDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImplDb implements TaskDao {
    @Override
    public List<Task> getTasksByDateByUserByState(Date date, int userId, StateEnum state) throws DaoException {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = ServiceDb.getConnection();
             PreparedStatement psGetTasksByDate = connection.prepareStatement(ConstantsSql.SELECT_TASKS_BY_USER_ID_BY_DATE)) {
            psGetTasksByDate.setInt(1, userId);
            psGetTasksByDate.setDate(2, date);
            psGetTasksByDate.setInt(3, state.getId());
            ResultSet rs = psGetTasksByDate.executeQuery();
            while (rs.next()) {
                tasks.add(TaskFactory.createTask(rs));
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tasks;
    }

    @Override
    public List<Task> getTasksByUserByState(int userId, StateEnum stateEnum) throws DaoException {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = ServiceDb.getConnection();
             PreparedStatement psGetTasksByDate = connection.prepareStatement(ConstantsSql.SELECT_TASKS_BY_USER_ID)) {
            psGetTasksByDate.setInt(1, userId);
            psGetTasksByDate.setInt(2, stateEnum.getId());
            ResultSet rs = psGetTasksByDate.executeQuery();
            while (rs.next()) {
                tasks.add(TaskFactory.createTask(rs));
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tasks;
    }

    private void setTasksStateByIds(StateEnum stateEnum, int[] idTasks) throws DaoException {
        try (Connection connection = ServiceDb.getConnection()) {
            try (PreparedStatement psSetTasksStateByIds = connection.prepareStatement(ConstantsSql.SET_TASK_STATE_BY_ID)) {
                connection.setAutoCommit(false);
                for (int idTask : idTasks) {
                    psSetTasksStateByIds.setInt(1, stateEnum.getId());
                    psSetTasksStateByIds.setInt(2, idTask);
                    psSetTasksStateByIds.addBatch();
                }
                psSetTasksStateByIds.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addTask(int userId, String description, Date date) throws DaoException {
        try (Connection connection = ServiceDb.getConnection();
             PreparedStatement psGetTasksByDate = connection.prepareStatement(ConstantsSql.ADD_TASK)) {
            psGetTasksByDate.setInt(1, userId);
            psGetTasksByDate.setString(2, description);
            psGetTasksByDate.setDate(3, date);
            psGetTasksByDate.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteTasksFromBin(int[] idTasks) throws DaoException {
        try (Connection connection = ServiceDb.getConnection()) {
            try (PreparedStatement psDeleteTaskById = connection.prepareStatement(ConstantsSql.DELETE_TASK_BY_ID)) {
                connection.setAutoCommit(false);
                for (int idTask : idTasks) {
                    psDeleteTaskById.setInt(1, idTask);
                    psDeleteTaskById.addBatch();
                }
                psDeleteTaskById.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void moveActiveTasksToRecycleBin(int[] idTasks) throws DaoException {
        setTasksStateByIds(StateEnum.BIN, idTasks);
    }

    @Override
    public void moveFixedTasksToRecycleBin(int[] idTasks) throws DaoException {
        setTasksStateByIds(StateEnum.BIN_FIXED, idTasks);
    }

    @Override
    public void fixTasks(int[] idTasks) throws DaoException {
        setTasksStateByIds(StateEnum.FIXED, idTasks);
    }

    @Override
    public void restoreTasks(int[] idTasks) throws DaoException {
        try (Connection connection = ServiceDb.getConnection()) {
            try (CallableStatement csSetTasksState = connection.prepareCall(ConstantsSql.CALL_RESTORE_PROCEDURE)) {
                connection.setAutoCommit(false);
                for (int idTask : idTasks) {
                    csSetTasksState.setInt(1, idTask);
                    csSetTasksState.addBatch();
                }
                csSetTasksState.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateDate(int userId) throws DaoException {
        try (Connection connection = ServiceDb.getConnection();
             PreparedStatement psUpdateActiveTasksDate = connection.prepareStatement(ConstantsSql.UPDATE_ACTIVE_TASKS_DATE)) {
            psUpdateActiveTasksDate.setInt(1, userId);
            psUpdateActiveTasksDate.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
