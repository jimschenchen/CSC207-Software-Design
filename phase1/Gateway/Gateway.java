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

    public DataBase init () throws IOException {
        DataBase db = Utils.pull();
        return db;
    }

    public void termination (DataBase database) throws IOException {
        Utils.push(database);
    }


    public static void main(String[] args) throws IOException {
        // init
        Gateway gw = new Gateway();
        DataBase db = new DataBase();
        // read from file
        db = gw.init();

        // print after reading from file
        System.out.println(db.getUserList());
        db.getUserList().forEach((u) -> System.out.println(u.getUser_name()));

        // manipulate db
        List<User> lst = new ArrayList<>();
        User u1 = new Attendee("123", "Jim");
        User u2 = new Attendee("123", "Jim2");
        Organizer o1 = new Organizer("123", "Organizaer");
        lst.add(u1);
        lst.add(u2);
        lst.add(o1);
        db.setUserList(lst);

        // save db
        gw.termination(db);
    }
}
