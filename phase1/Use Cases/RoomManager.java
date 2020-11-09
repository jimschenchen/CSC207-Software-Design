import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class RoomManager {
    private Room room;
    private List<Room> all_rooms;

    public RoomManager(Room rm, List<Room> all_rm){
        this.room = rm;
        this.all_rooms = all_rm;  //我觉得这个应该从database中读取吧？而不是我们自己输入
    }

    public boolean add_room(Room new_room){
        boolean flag = true;
        for (int i = 0; i < all_rooms.size(); i ++){
            if (new_room.getRoom_num() == all_rooms.get(i).getRoom_num()){
                flag = false;
            }
        }
        if (flag == true) {
            all_rooms.add(new_room);
            return true;
        }
        return false;
    }


    /*public void create_add_room(int roomnum){
     *  Room r = new Room();
     *  all_rooms.add(r);
     *  //这一行是用来添加这个room到database的，但是我不会写。
     * }
     */
}