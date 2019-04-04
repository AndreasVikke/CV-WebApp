package data;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *
 * @author Andreas Vikke (MF)
 */
public class DataSourceMySql {

    private MysqlDataSource dataSource = new MysqlDataSource();
    private MysqlDataSource localDataSource = new MysqlDataSource();

    public DataSourceMySql() {
        {
            try {
                dataSource.setServerName("andreasvikke.dk");
                dataSource.setPort(3306);
                dataSource.setDatabaseName("andreasVikke");
                dataSource.setUser("transformer");
                dataSource.setPassword("f7qGtArm");
                dataSource.setUseSSL(false);

                localDataSource.setServerName("localhost");
                localDataSource.setPort(3306);
                localDataSource.setDatabaseName("andreasVikke");
                localDataSource.setUser("root");
                localDataSource.setPassword("f7qGtArm");
                localDataSource.setUseSSL(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public MysqlDataSource getDataSource() {
        return localDataSource;
    }
}
