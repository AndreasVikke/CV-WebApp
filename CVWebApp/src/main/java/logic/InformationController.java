package logic;

import data.DataSourceMySql;
import data.exceptions.InformationException;
import data.mappers.InformationMapper;
import data.models.Information;
import java.util.List;

/**
 *
 * @author Andreas Vikke
 */
public class InformationController {

    private static final InformationMapper mapper = new InformationMapper(new DataSourceMySql().getDataSource());

    public static void createInfo(String name, String text) throws InformationException {
        mapper.add(new Information(0, name, text));
    }

    public static Information getUser(int id) throws InformationException {
        return mapper.get(id);
    }

    public static List<Information> getAllUsers(String email) throws InformationException {
        return mapper.getAll();
    }
}
