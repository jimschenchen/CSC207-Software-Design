import java.util.List;

/**
 * The Room Manager class
 */
public class RoomManager {

    /**
     * Retrun all the rooms in the database
     * @param g the database
     * @return the list of room id in the database
     */
    public List<Room> getListOfRooms(Gateway g) {
        return g.getRoomList();
    }

    /**
     * Judge whether a room can be added to the database
     *
     * @param room_num the room number
     * @param g the database
     * @return the boolean shows that whether a room can be added to the database
     */
    public boolean canAddRoom(String room_num, Gateway g) {
        for (Room r : g.getRoomList()) {
            if (r.getRoomNum().equals(room_num)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add a new room to the database.
     *
     * @param roomNumber the room number
     * @param g the database
     */
    public void add_room(String roomNumber, Gateway g) {
        Room room = new Room(roomNumber, g.getNextRoomId());
        g.addRoom(room);
    }

    /**
     * Get the room id by the room number
     *
     * @param roomNumber the room number
     * @param g the database
     * @return the room id
     */
    public int getRoomIDbyRoomNumber(String roomNumber, Gateway g){
        return g.getRoomByRoomNum(roomNumber).getRid();
    }

    /**
     * Get the room number by the room id
     * @param roomId the room id
     * @param g the database
     * @return the room number
     */
    public String getRoomString(int roomId, Gateway g){
        Room room = g.getRoomById(roomId);
        return room.getRoomNum();
    }
}