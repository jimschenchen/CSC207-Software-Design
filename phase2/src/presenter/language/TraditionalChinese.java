package presenter.language;

import presenter.language.Language;

public class TraditionalChinese implements Language {
    @Override
    public String username() {
        return "用戶名稱";
    }

    @Override
    public String password() {
        return "密碼";
    }

    @Override
    public String signUpAccount() {
        return "立即註冊";
    }

    @Override
    public String ok() {
        return "確定";
    }

    @Override
    public String exit() {
        return "退出";
    }

    @Override
    public String back() {
        return "返回";
    }

    @Override
    public String titleLogin() {
        return "歡迎蒞臨世界上最厲害的會議！";
    }

    @Override
    public String titleSignUp() {
        return "你正在註冊";
    }

    @Override
    public String language() {
        return "繁體中文";
    }

    @Override
    public String selectLanguage() {
        return "請選擇語言";
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
