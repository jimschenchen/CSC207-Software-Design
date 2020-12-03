package presenter.language;

public class Japanese implements Language {
    @Override
    public String username() {
        return "ユーザー名";
    }

    @Override
    public String password() {
        return "パスワード";
    }

    @Override
    public String signUpAccount() {
        return "今すぐアカウントを作成する";
    }

    @Override
    public String ok() {
        return "確認する";
    }

    @Override
    public String exit() {
        return "終了する";
    }

    @Override
    public String back() {
        return "戻る";
    }

    @Override
    public String titleLogin() {
        return "最高の会議へようこそ！";
    }

    @Override
    public String titleSignUp() {
        return "アカウントを作成中";
    }

    @Override
    public String language() {
        return "日本語";
    }

    @Override
    public String selectLanguage() {
        return "言語を選ぶ";
    }

    @Override
    public String fail() {
        return null;
    }

    @Override
    public String messageTitle() {
        return null;
    }

    @Override
    public String createAccountMessage() {
        return null;
    }
}
