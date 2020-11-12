public class Message {
    private String content;
    private int sender_id;
    private int receiver_id;

    public Message(String information, int sender_id, int receiver_id){
        this.content = information;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
    }

    public String getInfo() {
        return content;
    }

    public int getSenderId() {
        return sender_id;
    }

    public int getReceiverId() {
        return receiver_id;
    }

    public int getSender() {
        return sender_id;
    }
}
