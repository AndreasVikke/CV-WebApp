package data.mappers;

import data.DatabaseConnector;
import data.exceptions.InformationException;
import data.models.Information;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas Vikke
 */
public class InformationMapper implements DataMapperInterface<Information, Integer> {

    private static final DatabaseConnector connector = new DatabaseConnector();

    public InformationMapper(DataSource ds) {
        connector.setDataSource(ds);
    }
    
    @Override
    public void add(Information information) throws InformationException {
        try {
            try {
                connector.open();
                String SQL = "INSERT INTO informations (name, text) VALUES (?, ?)";
                PreparedStatement ps = connector.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, information.getName());
                ps.setString(1, information.getText());
                ps.executeUpdate();
            } finally {
                connector.close();
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new InformationException("An error occured while adding the Information to Database (SQL)");
        }
    }
    
    @Override
    public Information get(Integer id) throws InformationException  {
        try{
            try {
                connector.open();
                String SQL = "SELECT id, name, text FROM informations WHERE id = ?";
                PreparedStatement ps = connector.prepareStatement(SQL);
                
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()) {
                    Information info = new Information(id, rs.getString("name"), rs.getString("text"));
                    return info;
                }
            } finally {
                connector.close();
            }
            return null;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new InformationException("An error occured while getting the Information from the Database (SQL)");
        }
    }

    @Override
    public List<Information> getAll() throws InformationException  {
        try {
            try {
                connector.open();
                List<Information> infos = new ArrayList();
                String SQL = "SELECT id, email, role FROM users";
                Statement stmt = connector.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while(rs.next()) {
                    Information info = new Information(rs.getInt("id"), rs.getString("name"), rs.getString("text"));
                    infos.add(info);
                }
                return infos;
            } finally {
                connector.close();
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new InformationException("An error occured while getting the Informations from the Database (SQL)");
        }
    }
}
