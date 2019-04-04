package presentation.commands;

import data.exceptions.CommandException;
import data.models.Redirect;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andreas Vikke
 */
public class LogoutCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            return new Redirect(true, false, "/");
    }
}
