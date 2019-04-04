package presentation.commands;

import data.exceptions.CommandException;
import data.models.Redirect;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andreas Vikke
 */
public class UnknownCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        throw new CommandException("Unknown command");
    }
}
