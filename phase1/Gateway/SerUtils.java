import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.*;

public class SerUtils {

    /** Relative Path of the DB the file */
    static String relativePath = "./phase1/Gateway/data.ser";
    
    /**
    * @Description: Overrite the file
    * @Param: [data]
    * @return: void
    * @Author: 
    * @Date: 2020-11-09
    */
    public static void push (DataBase data) throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream(relativePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
    * @Description: Get all the data from file
    * @Param: []
    * @return: java.lang.Object
    * @Author: 
    * @Date: 2020-11-09
    */
    public static DataBase pull () throws IOException {
        DataBase db = getData();
        return db;
    }
    
    private static DataBase getData() {
        DataBase db = null;
        try {
            FileInputStream fileIn = new FileInputStream(relativePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            db = (DataBase) in.readObject();
            in.close();
            fileIn.close();
            return db;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return null;
        }
    }

}
