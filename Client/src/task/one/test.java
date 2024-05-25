package task.one;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class test{
    
    public static void main(String[] args) {      
              
                try{
                    
                    // Reading the contents of dummy.txt.
                    FileClass obj = new FileClass("C:\\JDeveloper\\mywork\\Application1\\Client\\src\\task\\one\\dummy.txt");
                    obj.openReadingConnection();
                    String query = obj.readFile();
                    obj.closeReadingConnection();
                    
                    // Connecting to the database.
                    ResourceBundle rb = ResourceBundle.getBundle("client.info");    
                    String url = rb.getString("url");
                    String userName = rb.getString("userName");
                    String password = rb.getString("password");
                    String driver = rb.getString("driver");                                    
                    DatabaseClass db = new DatabaseClass(driver,url,userName,password);
                    Connection con =db.openConnection();
                    con.setAutoCommit(false);
                    
                    // Reading the SQL files.
                    String queryOne = readSqlFile("C:\\JDeveloper\\mywork\\Application1\\Client\\src\\task\\one\\QueryOne.sql");
                    String queryTwo = readSqlFile("C:\\JDeveloper\\mywork\\Application1\\Client\\src\\task\\one\\QueryTwo.sql");
                    String queryThree = readSqlFile("C:\\JDeveloper\\mywork\\Application1\\Client\\src\\task\\one\\QueryThree.sql");

                    // Creating an object from business class to execute SQL statements.
                    Business b = new Business(con);                    
                    
                    // Creating the table, sequence, and trigger.
                    b.createTransaction(queryOne);
                    b.createTransaction(queryTwo);
                    b.createTransaction(queryThree);
                    
                    // Deleting dummy.txt.
                    obj.deleteFile();
                    
                    // Inserting the contents of dummy.txt into the dummy table in the database.
                    System.out.println(b.insertTransaction(query)+ " rows inserted.");
                    
                    // Selecting the dummy table rows and creating dummy.txt to write the query results to it. 
                    ResultSet rs =b.selectTransaction("SELECT * FROM DUMMY");
                    obj.writeToFile(rs);
                    
                }
                catch(Exception ex){
                    System.out.println(ex);
                }        
          }
    
    // A method to read an SQL file.
    public static String readSqlFile(String filePath) throws IOException {
            
           String contents ="";
           FileReader fr = new FileReader(filePath);
           BufferedReader r = new BufferedReader(fr);
           String reading ="";
           while((reading = r.readLine())!=null){
               contents+= reading;
           }
           
           r.close();
           return contents;
        }
}



