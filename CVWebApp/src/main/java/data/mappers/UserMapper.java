package data.mappers;

import data.DatabaseConnector;
import data.exceptions.UserException;
import data.models.RoleEnum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import data.models.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Vikke
 */
public class UserMapper implements DataMapperInterface<User, String> {

    private static final DatabaseConnector connector = new DatabaseConnector();

    public UserMapper(DataSource ds) {
        connector.setDataSource(ds);
    }
    
    @Override
    public void add(User user) throws UserException {
        try {
            try {
                connector.open();
                String SQL = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = connector.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, user.getUsername());
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getRole().toString());
                ps.executeUpdate();
            } finally {
                connector.close();
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new UserException("An error occured while adding User to Database (SQL)");
        }
    }
    
    @Override
    public User get(String username) throws UserException  {
        try{
            try {
                connector.open();
                String SQL = "SELECT username, email, role FROM users WHERE lower(username) = ?";
                PreparedStatement ps = connector.prepareStatement(SQL);
                
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()) {
                    User user = new User(rs.getString("username"), rs.getString("email"), null, RoleEnum.valueOf(rs.getString("role")));
                    return user;
                }
            } finally {
                connector.close();
            }
            return null;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new UserException("An error occured while getting the User from the Database (SQL)");
        }
    }

    @Override
    public List<User> getAll() throws UserException  {
        try {
            try {
                connector.open();
                List<User> users = new ArrayList();
                String SQL = "SELECT id, email, role FROM users";
                Statement stmt = connector.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while(rs.next()) {
                    User user = new User(rs.getString("username"), rs.getString("email"), null, RoleEnum.valueOf(rs.getString("role")));
                    users.add(user);
                }
                return users;
            } finally {
                connector.close();
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new UserException("An error occured while getting the Users from the Database (SQL)");
        }
    }

    public static boolean validateUser(String username, String password) throws UserException {
        try {
            try {
                connector.open();
                String SQL = "SELECT username FROM users WHERE lower(username) = ? AND password = ?";
                PreparedStatement ps = connector.prepareStatement(SQL);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    if(username.equals(rs.getString("username").toLowerCase()))
                        return true;
                } else {
                    return false;
                }
            } finally {
                connector.close();
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new UserException("An error occured while getting the Users from the Database (SQL)");
        }
    }
}
