import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Store.*;

public class GatewayFacade {
    public static void main(String[] args) {
        mutate ();
    }

    public static void write () throws IOException {
        // create a bean
        DataBase rb = new DataBase();

        // primitive
        rb.setEventNumber(12);
        rb.setUserNumber(5);

        // List Type
        List<UserStore> userList =  new ArrayList<>();
        userList.add(new UserStore(1, "jim"));
        userList.add(new UserStore(2, "Hax"));
        rb.setUserList(userList);

        // upload rb
        Utils.push(rb);
    }

    public static void read () {
        DataBase rb = new DataBase();
        rb = Utils.pull();
        System.out.println(rb.getEventNumber());
        System.out.println(rb.getUserList());
    }

    public static void mutate () {
        DataBase rb = new DataBase();
        rb = Utils.pull();
        rb.setUserNumber(1);
        rb.getUserList().remove(0);
        System.out.println(rb.getUserNumber());
        System.out.println(rb.getUserList());
    }

}
