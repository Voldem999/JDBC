package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS person " +
                "(ID SERIAL PRIMARY KEY," +
                " NAME TEXT, " +
                "LASTNAME TEXT, " +
                "AGE INT)";
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
                System.out.println("Таблица успешно создана");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("При создании таблицы произошла ошибка");
            }
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE if exists person";
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
                System.out.println("Таблица успешно удалена");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("При удалении таблицы произошла ошибка");
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person (name, lastname, age) VALUES (?,?,?)")) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
                System.out.println("Пользователь с именем " + name + " " + lastName + " " + age + " добавлен в базу данных.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Пользователь с именем " + name + " " + lastName + " " + age + " НЕ добавлен в базу данных.");
            }
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM person WHERE id=?";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Пользователь удален");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Удаление пользователя не удалось");
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM person";
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));

                    userList.add(user);
                    System.out.println("Пользователи получены");
                }
            } catch (SQLException e) {
                System.err.println("Получение пользователей не удалось");
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM person";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
                System.out.println("Таблица успешно очищена");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("При очищении таблицы произошла ошибка");
            }
        }

    }
}
