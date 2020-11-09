public class Room {
    private int room_num;
    private static int r_n;
    private int currentcapacity;

    public Room(){
        this.currentcapacity = 2; //看phase1 sign-up system 介绍,里面说不算上speaker的话，roomsize为2
        this.room_num = r_n;
        r_n ++;
    }

    public int getRoom_num() {
        return room_num;
    }

    public int getCurrent_Capacity() {
        return currentcapacity;
    }

    public void update_Current_Capacity(){
        this.currentcapacity -= 1;
    }
}