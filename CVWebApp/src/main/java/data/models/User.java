package data.models;

/**
 *
 * @author Andreas Vikke
 */
public class User {
    private String username;
    private String email;
    private String password;
    private RoleEnum role;

    public User(String username, String email, String password, RoleEnum role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RoleEnum getRole() {
        return role;
    }
}
