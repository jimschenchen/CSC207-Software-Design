import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: group_0173
 * @description:
 * @author:
 * @create: 2020-11-09 21:58
 **/
public class Gateway {
    /**
    * @Description: deserializing database from file and return the database
    * @Param: []
    * @return: DataBase
    * @Author:
    * @Date: 2020-11-14
    */
    public DataBase init () throws IOException {
        DataBase db = SerUtils.pull();
        return db;
    }

    /**
    * @Description: Serializing database to file
    * @Param: [database]
    * @return: void
    * @Author:
    * @Date: 2020-11-14
    */
    public void termination (DataBase database) throws IOException {
        SerUtils.push(database);
    }

}
