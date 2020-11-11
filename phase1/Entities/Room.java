public class Room {
    private int room_num;
    private int rid;
    private int capacity;

    public Room(int room_num, int rid){
        this.capacity = 2; //看phase1 sign-up system 介绍,里面说不算上speaker的话，roomsize为2
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
}