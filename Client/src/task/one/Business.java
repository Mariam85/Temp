package task.one;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// This class handles the database transactions.

public class Business {
    
    private Connection con;
    
    public Business(Connection con) {
        this.con = con;
    }
    
    public int insertTransaction(String query) throws SQLException {
        
        String[] queries = query.split("[\n]"); 
        
        try(PreparedStatement add = con.prepareStatement(query)) {
            
            int numberOfRows = 0;
            for(int i=0;i<queries.length;i++){
                numberOfRows+= add.executeUpdate(queries[i]);
            }
            con.commit();
            return numberOfRows;
            
        } 
        catch (SQLException e) {
            
            try {
                con.rollback();
            } catch (SQLException f) {
                System.out.println("Insert statement: "+ f.getMessage());
            }
            
            System.out.println("Insert statement: "+ e.getMessage());
            return 0;
        }        
    }
    
    
    public void createTransaction(String query){

        try(Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);  
        } 
        catch (SQLException e) {
            
            try {
                con.rollback();
            } catch (SQLException f) {
                System.out.println("Create statement: "+ f.getMessage());
            }
            
            System.out.println("Create statement: "+ e.getMessage());
        }
    }
    
    public ResultSet selectTransaction(String query){
        
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        }
        catch (SQLException e) {
            System.out.println("Insert statement: "+ e.getMessage());
            return null;
        }
    }
    
    
    
}
