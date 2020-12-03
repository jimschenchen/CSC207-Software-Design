package gateway;

import entity.*;
import entity.event.Event;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @program: group_0173
 * @description:
 * @author:
 * @create: 2020-12-03 22:18
 **/
public class GatewayCli extends Gateway{

    public static void main(String[] args) {
        GatewayFacade gatewayFacade = new GatewayFacade();
        Scanner scan = new Scanner(System.in);
        String input = "";
        GatewayCli gcli = new GatewayCli();
        while (!input.equals("0")) {
            System.out.println("---------- ---------- ---------- ---------- ---------- ----------");
            System.out.println("Gateway: Welcome to Gateway CLI");
            System.out.println("1. Display all data");
            System.out.println("2. Check gateway errors");
            System.out.println("3. Format Database");
            System.out.println("0. Exit");
            input = scan.nextLine();
            switch (input) {
                case "1":
                    gcli.printDataBase();
                    break;
                case "2":
                    gcli.testCases();
                    break;
                case "3":
                    gcli.rmrf();
                    break;
                case "0":
                    System.out.println("Gateway: CLI exit");
                    break;
                default:
                    System.out.println("Gateway: Please type correct operation number");
            }
        }
    }


    private void rmrf() {
        Jedis jedis = getJedis();
        Scanner scan = new Scanner(System.in);
        System.out.println("Gateway: Warning! Are you sure want to FORMAT Database! (N/Y)");
        String input = scan.nextLine();
        if (input.equals("Y")){
            System.out.println("Gateway: FORMATTING Database ...");
            System.out.print("Process:");
            jedis.del(Config.NEXT_USER_ID);
            System.out.print("**");
            jedis.del(Config.NEXT_EVENT_ID);
            System.out.print("**");
            jedis.del(Config.NEXT_ROOM_ID);
            System.out.print("**");
            jedis.del(Config.USER_HASH);
            System.out.print("**");
            jedis.del(Config.EVENT_HASH);
            System.out.print("**");
            jedis.del(Config.ROOM_HASH);
            System.out.print("**");
            jedis.del(Config.MESSAGE_LIST);
            System.out.print("**");
            System.out.println("\nGateway: Database has been formatted");
        } else {
            System.out.println("Gateway: Format operation cancelled");
        }
        closeJedis(jedis);
    }

    /** Enable '-ea' in VM option in config before testing*/
    private void testCases () {
        System.out.println("Gateway: Testing...");
        GatewayFacade gf = new GatewayFacade();
        testUser (gf);
        testEvent (gf);
        testRoom (gf);
        testMessage();
        System.out.println("\nGateway: All tests passed");
    }
    /** This method is used for test    */
    public void printDataBase () {
        GatewayFacade gf = new GatewayFacade();
        Jedis jedis = getJedis();
        System.out.println("DataBase: Displaying DATA:");
        System.out.println("---------- ---------- ---------- ---------- ---------- ----------");
        System.out.println("---------- ---------- ---------- ---------- ---------- ----------");
        System.out.println("+ UserNextId: " + jedis.get(Config.NEXT_USER_ID));
        System.out.println("+ EventNextId: " + jedis.get(Config.NEXT_EVENT_ID));
        System.out.println("+ RoomNextId: " + jedis.get(Config.NEXT_ROOM_ID));
        System.out.println("+ User List");
        gf.getUserList().forEach((u) -> System.out.println("   - " + u.toString()));
        System.out.println("+ Event List");
        gf.getEventList().forEach((e) -> System.out.println("  - " + e.toString()));
        System.out.println("+ Room List");
        gf.getRoomList().forEach((r) -> System.out.println("   - " + r.toString()));
        System.out.println("+ Message List");
        gf.getMessageList().forEach((m) -> System.out.println("    - " + m.toString()));
        System.out.println("---------- ---------- ---------- ---------- ---------- ----------");
        jedis.close();
    }

    private void testUser (GatewayFacade gf) {
        User u1 = new Attendee(998, "114514", "testJIMA");
        User u2 = new Organizer(999, "234234", "testJim2");
        gf.addUser(u1);
        gf.addUser(u2);
        assert (gf.getUserById(998).getUserName().equals("testJIMA"));
        assert (gf.getOrganizerById(998) == null);
        assert (gf.getOrganizerById(999).getUserName().equals("testJim2"));
        assert (gf.getUserList().get(999).getClass().equals(Organizer.class));
        gf.deleteUser(u1);
        gf.deleteUser(u2);
        System.out.print("**");
    }

    private void testEvent (GatewayFacade gf) {
//        gf.addEvent(new Event(LocalDateTime.now(), 998,0, "First Event", 0));
//        gf.addEvent(new Event(LocalDateTime.now(), 999,125, "Second Event", 0));
//        assert (gf.getEventById(998).getTitle().equals("First Event"));
//        assert (gf.getEventList().get(999).getSpeakerId() == 125);
//        gf.deleteFromHash(EVENT_HASH, 999);
//        gf.deleteFromHash(EVENT_HASH, 998);
        System.out.print("**");
    }

    private void testRoom (GatewayFacade gf) {
        gf.addRoom(new Room("123313", 999));
        assert (gf.getRoomById(999).getRoomNum().equals("123313"));
        assert (gf.getRoomByRoomNum("123313").getRid() == 999);
//        deleteFromHash(ROOM_HASH, 999);
        System.out.print("**");
    }

    private void testMessage () {
//        boolean check = false;
//        List<Message> messageList = getSentMessageListByUserId(999);
//        for (Message message : messageList) {
//            check = (message.getInfo().equals("Hello Message") || check);
//        }
//        if (!check) {
//            Message m = new Message("Hello Message", 999, 998);
//            addMessage(m);
//        }
//        messageList = getSentMessageListByUserId(999);
//        check = false;
//        for (Message message : messageList) {
//            check = (message.getInfo().equals("Hello Message") || check);
//        }
    }
}
