package task.one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FileClass implements FileConnection {
    private String filePath;
    private Scanner myReader;
    private File myObj;
    
    public FileClass(String filePath) {
        if (filePath.length() == 0) 
        {
            filePath = "";
            System.out.println("No file path was given");
        }
        else
        {
            this.filePath = filePath;
        }
    }
    
    public void openReadingConnection() throws FileNotFoundException {  

            myObj = new File(filePath);
            myReader = new Scanner(myObj);
            
    }
    
    public void closeReadingConnection(){
        myReader.close();
    }
    
    public String readFile(){
        String query ="";
        
                                                
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          String[] res = data.split("[,]");
            
          query += "INSERT INTO dummy(id, name, phone,address,salary) VALUES" + "("+  res[0]+",'"+ res[1]+"',"+ res[2]+",'"+
            res[3]+"',"+
            res[4]+")\n"; 
        }
        return query;
    }
    
    public Boolean deleteFile(){
        return myObj.delete();
    }
    
    public void writeToFile(ResultSet rs) {    
        
        try{
                // Check if the file does not exist create it:
                myObj = new File(filePath);
                if (myObj.createNewFile()) {
                  System.out.println("New file created: " + myObj.getName());
                } else {
                  System.out.println("The file "+ myObj.getName() + " exists.");
                }
                
                // Write the results of the SQL statement to the file.
                String c ="";
                while(rs.next()){
                         c +=rs.getString("id")+','+
                        rs.getString("name")+','+
                        rs.getString("phone")+','+
                        rs.getString("address")+','+
                        rs.getInt("salary")+'\n';
                }
                FileWriter myWriter = new FileWriter(filePath);
                System.out.println("Currently writing to the file....");
                myWriter.write(c);
                myWriter.close();
                System.out.println("File successfully written to and closed....");
                
        }
        catch(SQLException s) {
            System.out.println("SQL error writing to the file: "+ s.getMessage());
        }
        catch(IOException i) {
            System.out.println("IO error writing to the file: "+ i.getMessage());
        }
        
    }
    
}
