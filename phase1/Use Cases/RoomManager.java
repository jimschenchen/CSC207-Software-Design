import java.util.ArrayList;
import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class RoomManager {

    public List<Room> all_Rooms(DataBase bd) {
        return bd.getRoomList();
    }

    // is it possible to separate the method to 2 methods?
    // canAddRoom and addRoom
    // canAddRoom = check if we can add a room
    // addRoom = just adding the room -grace

    public boolean canAddRoom(Room room, DataBase bd) {
        for (Room r : bd.getRoomList()) {
            if (r.getRoom_num() == room.getRoom_num()) {
                return false;
            }
        }
        return true;
    }

    public void add_room(Room new_room, DataBase bd) {
        if (canAddRoom(new_room, bd) == true){
            bd.addRoom(new_room);
        }
    }
}