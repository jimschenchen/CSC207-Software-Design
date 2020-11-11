import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import javax.xml.crypto.Data;
import java.io.*;

public class Utils {

    /** Relative Path of the DB the file */
    static String relativePath = "./phase1/Gateway/data.json";
    
    /**
    * @Description: Overrite the file
    * @Param: [data]
    * @return: void
    * @Author: 
    * @Date: 2020-11-09
    */
    public static void push (DataBase data) throws IOException {
        // init builder
        GsonBuilder builder = new GsonBuilder();
        // set the interface config
        builder.registerTypeAdapter(User.class, new InterfaceAdapter());
        // create gson
        Gson gson = builder.serializeNulls().create();
        String buff = gson.toJson(data);
        File file=new File(relativePath);
        FileUtils.writeStringToFile(file, buff,"UTF-8", false);
    }

    /**
    * @Description: Get all the data from file
    * @Param: []
    * @return: java.lang.Object
    * @Author: 
    * @Date: 2020-11-09
    */
    public static DataBase pull () throws IOException {
        try {
            DataBase db = getData();
            return db;
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("Utils Alert: --- File does not exist---");
            System.out.println("Utils Alert: --- Creating File in the dir---");
            push(new DataBase());
            DataBase db = getData();
            return db;
        }
    }

    private static DataBase getData() throws FileNotFoundException {
        // init builder
        GsonBuilder builder = new GsonBuilder();
        // set the interface config
        builder.registerTypeAdapter(User.class, new InterfaceAdapter());
        // create gson
        Gson gson = builder.serializeNulls().create();

        // init reader
        BufferedReader reader = new BufferedReader(new FileReader(relativePath));
        DataBase data = gson.fromJson(reader, DataBase.class);
        return data;
    }

}
