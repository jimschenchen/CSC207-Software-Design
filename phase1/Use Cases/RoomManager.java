import java.util.ArrayList;
import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class RoomManager {

    public List<Room> getListOfRooms(DataBase bd) {
        return bd.getRoomList();
    }

    public boolean canAddRoom(String room_num, DataBase bd) {
        for (Room r : bd.getRoomList()) {
            if (r.getRoom_num().equals(room_num)) {
                return false;
            }
        }
        return true;
    }

    public void add_room(String roomNumber, DataBase d) {
        Room room = new Room(roomNumber, d.getNextRoomId());
        d.addRoom(room);
    }

    public int getRoomIDbyRoomNumber(String roomNumber, DataBase db){
        return db.getRoomByRoomNum(roomNumber).getRid();
    }

    public String getRoomString(int roomId, DataBase db){
        Room room = db.getRoomById(roomId);
        return room.getRoom_num();
    }
}