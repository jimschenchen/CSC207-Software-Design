public class Room {
    private int room_num;
    private int capacity;

    public Room(int room_n, int cap){
        this.room_num = room_n;
        this.capacity = cap;
    }

    public int getRoom_num() {
        return room_num;
    }

    public int getCapacity() {
        return capacity;
    }
}
