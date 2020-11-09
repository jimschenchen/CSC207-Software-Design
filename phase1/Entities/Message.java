public class Message {
    private String content;
    private User sender;
    private User receiver;

    public Message(String information, User sender, User receiver){
        this.content = information;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getInfo() {
        return content;
    }

    public String getSender_name() {
        return sender.getUser_name();
    }

    public String getReceiver_name() {
        return receiver.getUser_name();
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

}
