package logic;

import data.DataSourceMySql;
import data.exceptions.UserException;
import data.mappers.UserMapper;
import data.models.RoleEnum;
import data.models.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Andreas Vikke
 */
public class UserController {

    private static final UserMapper mapper = new UserMapper(new DataSourceMySql().getDataSource());

    public static void createUser(String username, String email, String password) throws UserException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

            mapper.add(new User(username.toLowerCase(), email, passwordHash, RoleEnum.EDITOR));
        } catch(NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            throw new UserException("An error occured while trying to hash password");
        }
    }

    public static User getUser(String username) throws UserException {
        return mapper.get(username.toLowerCase());
    }

    public static List<User> getAllUsers(String email) throws UserException {
        return mapper.getAll();
    }

    public static boolean validateUser(String username, String password) throws UserException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

            return mapper.validateUser(username.toLowerCase(), passwordHash);
        } catch(NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            throw new UserException("An error occured while trying to hash password");
        }
    }
}
