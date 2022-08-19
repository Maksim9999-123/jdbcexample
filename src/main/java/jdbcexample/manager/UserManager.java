package jdbcexample.manager;

import jdbcexample.db.DBConnectorProvider;
import jdbcexample.model.User;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserManager {
    private Connection connection = DBConnectorProvider.getProvider().getConnection();

    public void addUser(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into user(name,surname,email,password) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
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

    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from user");
        List<User> users = new LinkedList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            users.add(user);
        }
        return users;

    }

    public void deleteUserById(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("DELETE from user where id= ?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
