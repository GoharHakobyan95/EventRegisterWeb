package manager;


import db.DBConnectionProvider;
import model.Event;
import model.User;
import model.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private EventManager eventManager = new EventManager();

    public void add(User user) {
        String sql = "insert into user(name,surname,email, profile_pic, password, user_role) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getProfilePic());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getUserRole().name());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        String sql = "select * from user";
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getById(int id) {
        String sql = "select * from user where id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "select * from user where email = ? and password = ?";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String email) {
        String sql = "select * from user where email = ?";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setUserRole(UserRole.valueOf(resultSet.getString("user_role")));
        user.setProfilePic(resultSet.getString("profile_pic"));

        String userEventSql = "Select event_id from user_event where user_id = ?";
        PreparedStatement ps = connection.prepareStatement(userEventSql);
        ps.setInt(1, user.getId());

        ResultSet eventsResultSet = ps.executeQuery();
        List<Event> events = new ArrayList<>();
        while (eventsResultSet.next()) {
            events.add(eventManager.getById(eventsResultSet.getInt(1)));
        }
        user.setEvents(events);

        return user;
    }

    public void removeById(int userId) {
        String sql = "delete from user where id = " + userId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(User user) {
        String sql = "update user set name = ?,surname = ?,email = ?, profile_pic=?, password=?, user_role=? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getProfilePic());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getUserRole().name());
            ps.setInt(7, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsersByEventId(int eventId) {
        String userEventSql = "Select user_id from event_user where event_id = ?";
        PreparedStatement ps = null;
        List<User> users = new ArrayList<>();
        try {
            ps = connection.prepareStatement(userEventSql);
            ps.setInt(1, eventId);
            ResultSet userResultSet = ps.executeQuery();

            while (userResultSet.next()) {
                users.add(getById(userResultSet.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void editUser(User user) {
        String sql = "update user set name = ?,surname = ?,email = ?, profile_pic=?, password=?, user_role=? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getProfilePic());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getUserRole().name());
            ps.setInt(7, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
