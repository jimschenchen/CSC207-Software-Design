package presenter;

public interface IWindow {
    //This is the interface for the dependency inversion between View(GUI) and the presenter.

    void update(String type);
}
