package task.one;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseClass implements DatabaseConnection{
    
    private String driver;
    private String url;
    private String userName;
    private String password;
    
    public DatabaseClass(String driver,String url,String userName,String password) {
        
        this.driver = driver;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
    
    public Connection openConnection() throws SQLException, ClassNotFoundException {

            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,userName,password);
            return con;

    }
    public void closeConnection(Connection con) throws SQLException {
            con.close();
    }
}
