/**
 * The Room class
 */
public class Room implements java.io.Serializable {
    private String roomNum;
    private final int rid;
    private int capacity;

    /**
     * Constructor of Room object.
     *
     * @param roomNum the room number
     * @param rid the room id
     */
    public Room(String roomNum, int rid){
        this.capacity = 2;
        this.roomNum = roomNum;
        this.rid = rid;
    }

    /**
     * A getter for the room number
     *
     * @return the room number
     */
    public String getRoomNum() {
        return roomNum;
    }

    /**
     * A getter for the room capacity
     *
     * @return the room capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * A getter for the room id
     *
     * @return the room id
     */
    public int getRid() {
        return rid;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room num=" + roomNum +
                ", room id=" + rid +
                ", capacity=" + capacity +
                '}';
    }
}