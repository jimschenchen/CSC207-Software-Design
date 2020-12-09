package presenter;


/**
 * This interface is specifically used to reverse the dependency when the presenter wants to update the
 * view.
 */
public interface IMessage {
    void messageSuccess(boolean success);
    void setVisible();
}
