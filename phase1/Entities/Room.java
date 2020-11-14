public class Room implements java.io.Serializable {
    private int room_num;
    private final int rid;
    private int capacity;

    public Room(int room_num, int rid){
        this.capacity = 2;
        this.room_num = room_num;
        this.rid = rid;
    }

    public int getRoom_num() {
        return room_num;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRid() {
        return rid;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_num=" + room_num +
                ", rid=" + rid +
                ", capacity=" + capacity +
                '}';
    }
}