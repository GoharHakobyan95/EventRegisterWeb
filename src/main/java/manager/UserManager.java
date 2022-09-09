package manager;


import db.DBConnectionProvider;
import model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private EventManager eventManager = new EventManager();

    public void addUser(User user) {
        String sql = "Insert into user (name,surname,email,event_id) Values(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getEvent().getId());
            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }
            System.out.println(user);
            System.out.println("User was added successfully! ");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Invalid event ID.Please try again! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        List<User> users = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(getUserFromResulSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return users;
    }


    public User getById(int id) {
        String sql = "SELECT * FROM user  WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getUserFromResulSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private User getUserFromResulSet(ResultSet resultSet) throws SQLException {
        int eventId = resultSet.getInt("event_id");
        return User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .event(eventManager.getById(eventId))
                .build();

    }

    public void removeUserById(int userId) {
        String sql = "Delete from user where id = " + userId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editUser(User user) {
        String sql = "UPDATE user set `name` =?, surname = ?, email = ?, event_id = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getEvent().getId());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


