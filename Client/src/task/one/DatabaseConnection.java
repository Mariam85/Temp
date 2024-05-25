package task.one;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnection {
    public Connection openConnection() throws SQLException, ClassNotFoundException ;
    public void closeConnection(Connection con) throws SQLException;
}


