package gateway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.*;
import entity.event.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @program: group_0173
 * @description: This Gateway connect to remote Redis Database and store all the entities in serialized json format.
 *      Since Nosql DB does not perform well on User storage, the runtime may be longer than expectation. It is better
 *      to change to sql DB to have a better performance.
 * @author:
 * @create: 2020-12-3 10:55
 **/
public class GatewayFacade {



    /** Return the next user id and self increase by 1 */
    public int getNextUserId() {

    }

    /** Return the next event id and self increase by 1 */
    public int getNextEventId() {

    }

    /** Return the next room id and self increase by 1 */
    public int getNextRoomId() {

    }


    // Helper Function
    /** Helper Func, adding (id, value) to the <key>map */
    private void addToHash (String key, int id, String value) {

    }
    private void updateToHash(String key, int id, String value) {

    }

    private Map<String, String> getAllFromHash(String key) {

    }

    private String getByIdFromHash (String key, int id) {

    }

    private void deleteFromHash(String key, int id) {

    }

    private void addToList (String key, String value) {

    }

    private List<String> getAllFromList (String key) {

    }

    // ===== User: Hash=====
    /**
     * @Description: Add user to DB. It will automatically substitute the user in DB with the same uid
     * @Param: [user]
     * @return: void
     * @Author:
     * @Date: 2020-11-28
     */
    public void addUser(User user) {

    }

    public void updateUser(User user) {

    }

    public void deleteUser(User user) {

    }
    /**
     * @Description: Get user by given id.
     * @Param: [id]
     * @return: User
     * @Author:
     * @Date: 2020-11-28
     */
    public User getUserById(int id) {

    }
    /**
     * @Description: Get the Whole User List. *This is method may lag the performance.
     * @Param: []
     * @return: java.util.List<User>
     * @Author:
     * @Date: 2020-11-28
     */
    public List<User> getUserList() {

    }
    /**
     * @Description: Get User by given username. *This is method may lag the performance. Then use `getUserById` instead.
     * @Param: [username]
     * @return: User
     * @Author:
     * @Date: 2020-11-28
     */
    public User getUserByUserName(String username) {

    }

    /**
     * @Description: Return Attendee by given <id>, if the user does not exist or is not Attendee, return null
     * @Param: [id]
     * @return: Attendee
     * @Author:
     * @Date: 2020-11-12
     */
    public Attendee getAttendeeById(int id) {

    }

    /**
     * @Description: get attendee by given username
     * @Param: [username]
     * @return: Attendee
     * @Author:
     * @Date: 2020-11-13
     */
    public Attendee getAttendeeByUserName(String username) {

    }

    /**
     * @Description: Return Speaker by given <id>, if the user does not exist or is not Speaker, return null
     * @Param: [id]
     * @return: Speaker
     * @Author:
     * @Date: 2020-11-12
     */
    public Speaker getSpeakerById(int id) {

    }

    /**
     * @Description: get speaker by given username
     * @Param: [username]
     * @return: Speaker
     * @Author:
     * @Date: 2020-11-13
     */
    public Speaker getSpeakerByUserName(String username) {

    }
    /**
     * @Description:
     * @Param: [username]
     * @return: Organizer
     * @Author:
     * @Date: 2020-11-13
     */
    public Organizer getOrganizerByUserName(String username) {

    }

    /**
     * @Description: Return Organizer by given <id>, if the user does not exist or is not Organizer, return null
     * @Param: [id]
     * @return: Organizer
     * @Author:
     * @Date: 2020-11-12
     */
    public Organizer getOrganizerById(int id) {

    }


    // ===== Event: Hash=====
    /**
     * @Description: Add event to event list
     * @Param: [event]
     * @return: void
     * @Author:
     * @Date: 2020-11-28
     */
    public void addEvent(Event event) {

    }
    public void updateEvent(Event event) {

    }
    public void deleteEvent(Event event) {

    }

    /**
     * @Description: Get list of all events. *This is method may lag the performance.
     * @Param: []
     * @return: java.util.List<Event>
     * @Author:
     * @Date: 2020-11-28
     */
    public List<Event> getEventList() {

    }
    /**
     * @Description: Get event by given id
     * @Param: [id]
     * @return: Event
     * @Author:
     * @Date: 2020-11-28
     */
    public Event getEventById(int id) {

    }


    // ===== Room: Hash=====
    /**
     * @Description: add room to room list
     * @Param: [room]
     * @return: void
     * @Author:
     * @Date: 2020-11-28
     */
    public void addRoom(Room room) {

    }
    public void updateRoom(Room room) {

    }
    public void deleteRoom(Room room) {

    }
    /**
     * @Description: get list of rooms
     * @Param: []
     * @return: java.util.List<Room>
     * @Author:
     * @Date: 2020-11-28
     */
    public List<Room> getRoomList() {

    }
    /**
     * @Description: get room by given id
     * @Param: [id]
     * @return: Room
     * @Author:
     * @Date: 2020-11-28
     */
    public Room getRoomById(int id) {

    }
    /**
     * @Description: get room by given roomNum
     * @Param: [roomNum]
     * @return: Room
     * @Author:
     * @Date: 2020-11-28
     */
    public Room getRoomByRoomNum(String roomNum) {

    }


    // ===== Message: List =====
    /**
     * @Description: add Message to the Message List
     * @Param: [message]
     * @return: void
     * @Author:
     * @Date: 2020-11-12
     */
    public void addMessage(Message message) {


    }
    /**
     * @Description: get List of all messages
     * @Param: []
     * @return: java.util.List<Message>
     * @Date: 2020-11-28
     */
    private List<Message> getMessageList() {

    }
    /**
     * @Description: Return the List of messages related to <userId>; Cannot add message
     * @Param: [userId]
     * @return: java.util.List<Message>
     * @Date: 2020-11-11
     */
    public List<Message> getAllMessageListByUserId(int userId) {

    }
    /**
     * @Description: Return the List of Sent messages related to <userId>; Cannot add message
     * @Param: [userId]
     * @return: java.util.List<Message>
     * @Author:
     * @Date: 2020-11-14
     */
    public List<Message> getSentMessageListByUserId(int userId) {

    }
    /**
     * @Description: Return the List of Received messages related to <userId>; Cannot add message
     * @Param: [userId]
     * @return: java.util.List<Message>
     * @Author:
     * @Date: 2020-11-14
     */
    public List<Message> getReceivedMessageListByUserId(int userId) {

    }



//    /** Testing */
//    public static void main(String[] args) {
//        GatewayFacade gatewayFacade = new GatewayFacade();
//        gatewayFacade.init();
//        Scanner scan = new Scanner(System.in);
//        String input = "";
//        while (!input.equals("0")) {
//            System.out.println("---------- ---------- ---------- ---------- ---------- ----------");
//            System.out.println("Gateway: Welcome to Gateway CLI");
//            System.out.println("1. Display all data");
//            System.out.println("2. Check gateway errors");
//            System.out.println("3. Format Database");
//            System.out.println("0. Exit");
//            input = scan.nextLine();
//            switch (input) {
//                case "1":
//                    gatewayFacade.printDataBase();
//                    break;
//                case "2":
//                    gatewayFacade.testCases();
//                    break;
//                case "3":
//                    gatewayFacade.rmrf();
//                    break;
//                case "0":
//                    System.out.println("Gateway: CLI exit");
//                    break;
//                default:
//                    System.out.println("Gateway: Please type correct operation number");
//            }
//        }
//    }
//
//    private void rmrf() {
//        Jedis jedis = getJedis();
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Gateway: Warning! Are you sure want to FORMAT Database! (N/Y)");
//        String input = scan.nextLine();
//        if (input.equals("Y")){
//            System.out.println("Gateway: FORMATTING Database ...");
//            System.out.print("Process:");
//            jedis.del(NEXT_USER_ID);
//            System.out.print("**");
//            jedis.del(NEXT_EVENT_ID);
//            System.out.print("**");
//            jedis.del(NEXT_ROOM_ID);
//            System.out.print("**");
//            jedis.del(USER_HASH);
//            System.out.print("**");
//            jedis.del(EVENT_HASH);
//            System.out.print("**");
//            jedis.del(ROOM_HASH);
//            System.out.print("**");
//            jedis.del(MESSAGE_LIST);
//            System.out.print("**");
//            System.out.println("\nGateway: Database has been formatted");
//        } else {
//            System.out.println("Gateway: Format operation cancelled");
//        }
//        closeJedis(jedis);
//    }
//
//    /** Enable '-ea' in VM option in config before testing*/
//    private void testCases () {
//        System.out.println("Gateway: Testing...");
//        testUser ();
//        testEvent ();
//        testRoom ();
//        testMessage();
//        System.out.println("\nGateway: All tests passed");
//    }
//    /** This method is used for test    */
//    public void printDataBase () {
//        Jedis jedis = getJedis();
//
//        System.out.println("DataBase: Displaying DATA:");
//        System.out.println("---------- ---------- ---------- ---------- ---------- ----------");
//        System.out.println("---------- ---------- ---------- ---------- ---------- ----------");
//        System.out.println("+ UserNextId: " + jedis.get(NEXT_USER_ID));
//        System.out.println("+ EventNextId: " + jedis.get(NEXT_EVENT_ID));
//        System.out.println("+ RoomNextId: " + jedis.get(NEXT_ROOM_ID));
//        System.out.println("+ User List");
//        getUserList().forEach((u) -> System.out.println("   - " + u.toString()));
//        System.out.println("+ Event List");
//        getEventList().forEach((e) -> System.out.println("  - " + e.toString()));
//        System.out.println("+ Room List");
//        getRoomList().forEach((r) -> System.out.println("   - " + r.toString()));
//        System.out.println("+ Message List");
//        getMessageList().forEach((m) -> System.out.println("    - " + m.toString()));
//        System.out.println("---------- ---------- ---------- ---------- ---------- ----------");
//
//        // Close
//        closeJedis(jedis);
//    }
//
//    private void testUser () {
//        addUser(new Attendee(998, "114514", "testJIm"));
//        addUser(new Organizer(999, "234234", "testJim2"));
//        assert (getUserById(998).getUserName().equals("testJIm"));
//        assert (getOrganizerById(998) == null);
//        assert (getOrganizerById(999).getUserName().equals("testJim2"));
//        assert (getUserList().get(999).getClass().equals(Organizer.class));
//        deleteFromHash(USER_HASH, 999);
//        deleteFromHash(USER_HASH, 998);
//        System.out.print("**");
//    }
//
//    private void testEvent () {
////        addEvent(new (LocalDateTime.now(), 998,0, "First Event", 0));
////        addEvent(new Event(LocalDateTime.now(), 999,125, "Second Event", 0));
////        assert (getEventById(998).getTitle().equals("First Event"));
////        assert (getEventList().get(999).getSpeakerId() == 125);
////        deleteFromHash(EVENT_HASH, 999);
////        deleteFromHash(EVENT_HASH, 998);
//        System.out.print("**");
//    }
//
//    private void testRoom () {
//        addRoom(new Room("123313", 999));
//        assert (getRoomById(999).getRoomNum().equals("123313"));
//        assert (getRoomByRoomNum("123313").getRid() == 999);
//        deleteFromHash(ROOM_HASH, 999);
//        System.out.print("**");
//    }
//
//    private void testMessage () {
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
//    }
}
