public class Message {
    private String info;
    private String sender;
    private String receiver;

    public Message(String information, String send, String receive){
        this.info = information;
        this.sender = send;
        this.receiver = receive;
    }

    public String getInfo() {
        return info;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }
}
