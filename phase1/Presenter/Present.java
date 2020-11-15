public class Present {
    public static void main(String[] args) {
        ViewModel model = new ViewModel();
        Presenter p = new Presenter(model);
        p.Introduction();
        p.IsUserRegistered();
    }
}
