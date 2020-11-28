/**
 * The Message class.
 */
public class Message implements java.io.Serializable {
    private String content;
    private int senderId;
    private int receiverId;

    /**
     * Constructor of a message object.
     *
     * @param information the message content
     * @param senderId the sender id
     * @param receiverId the receiver id
     */
    public Message(String information, int senderId, int receiverId){
        this.content = information;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    /**
     * A getter for the content
     *
     * @return the content of this message
     */
    public String getInfo() {
        return content;
    }

    /**
     * A getter for the sender id
     *
     * @return the sender id of this message
     */
    public int getSenderId() {
        return senderId;
    }

    /**
     * A getter for the receiver id
     *
     * @return the receiver id of this message
     */
    public int getReceiverId() {
        return receiverId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", sender_id=" + senderId +
                ", receiver_id=" + receiverId +
                '}';
    }
}
