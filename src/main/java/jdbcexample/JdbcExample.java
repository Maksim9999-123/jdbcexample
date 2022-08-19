package jdbcexample;

import jdbcexample.manager.UserManager;
import jdbcexample.model.User;


import java.sql.SQLException;
import java.util.List;


public class JdbcExample {

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        User user = new User();
       try {
           userManager.addUser(user);
           System.out.println(user);
            List<User> allUsers = userManager.getAllUsers();
           for (User users : allUsers) {
               System.out.println(users);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
