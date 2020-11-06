import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class RoomManager {
    private Room room;
    private List<Room> all_rooms;

    public RoomManager(Room rm, List<Room> all_rm){
        this.room = rm;
        this.all_rooms = all_rm;
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
}