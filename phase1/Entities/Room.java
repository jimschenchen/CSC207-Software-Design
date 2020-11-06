public class Room {
    private int room_num;
    private static int r_n;
    private int capacity;

    public Room(int cap){
        this.capacity = cap;
        this.room_num = r_n;
        r_n ++;
    }

    public int getRoom_num() {
        return room_num;
    }

    public int getCapacity() {
        return capacity;
    }
}