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
        return "糟糕！請重試一次！";
    }

    @Override
    public String messageTitle() {
        return "訊息";
    }

    @Override
    public String createAccountMessage() {
        return "成功註冊新帳號";
    }

    @Override
    public String succeedAddRoomMessage() {
        return "成功增加活動場地";
    }

    @Override
    public String successful() {
        return "成功";
    }

    @Override
    public String failed() {
        return "失敗";
    }

    @Override
    public String failAddRoomMessage() {
        return "活動場地已存在，或輸入有誤";
    }

    @Override
    public String succeedCancelEventMessage() {
        return "成功取消活動";
    }

    @Override
    public String failCancelEventMessage() {
        return "你沒有權限取消活動，或輸入有誤";
    }

    @Override
    public String succeedSignUpEventMessage() {
        return "成功報名";
    }

    @Override
    public String failSignUpEvent() {
        return "報名失敗，或輸入有誤";
    }

    @Override
    public String succeedWaitEvent() {
        return "成功加入活動等候名單";
    }

    @Override
    public String failWaitEventMessage() {
        return "加入活動等候名單失敗，或輸入有誤";
    }

    @Override
    public String succeedChangeEventMessage() {
        return "成功更改活動詳情";
    }

    @Override
    public String failChangeEventMessage() {
        return "更改活動詳情失敗，或輸入有誤";
    }

    @Override
    public String succeedCreateUserMessage() {
        return "成功新增賬戶";
    }

    @Override
    public String failCreateUserMessage() {
        return "新增賬戶失敗，或輸入有誤";
    }

    @Override
    public String succeedCreateEventMessage() {
        return "成功增加活動";
    }

    @Override
    public String failCreateEventMessage() {
        return "增加活動失敗，或輸入有誤";
    }

    @Override
    public String succeedCancelEnrollmentMessage() {
        return "成功退出活動";
    }

    @Override
    public String failCancelEnrollmentMessage() {
        return "退出活動失敗，或輸入有誤";
    }

    @Override
    public String succeedRemoveWaitMessage() {
        return "成功離開等候名單";
    }

    @Override
    public String failRemoveWaitMessage() {
        return "離開等候名單失敗，或輸入有誤";
    }

    @Override
    public String succeedResetPasswordMessage() {
        return "成功重設密碼";
    }

    @Override
    public String failResetPasswordMessage() {
        return "重設密碼失敗，或輸入有誤";
    }

    @Override
    public String succeedCreateAccountMessage() {
        return "註冊成功！";
    }

    @Override
    public String failCreateAccount() {
        return "註冊失敗，或輸入有誤";
    }
}
