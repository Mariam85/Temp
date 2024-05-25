package task.one;

import java.io.FileNotFoundException;


public interface FileConnection {
    public void openReadingConnection() throws FileNotFoundException;
    public void closeReadingConnection();
}
