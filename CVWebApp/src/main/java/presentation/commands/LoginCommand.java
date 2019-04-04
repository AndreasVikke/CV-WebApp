package presentation.commands;

import data.exceptions.CommandException;
import data.exceptions.UserException;
import data.models.Redirect;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.models.User;
import logic.UserController;

/**
 *
 * @author Andreas Vikke
 */
public class LoginCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            boolean valid = UserController.validateUser(username, password);

            if (valid) {
                User user = UserController.getUser(username);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                return new Redirect(true, false, "/admin");
            } else {
                throw new CommandException("Incorrect username and/or password");
            }
        } catch (UserException ex) {
            ex.printStackTrace();
            throw new CommandException(ex.getMessage());
        }
    }
}
