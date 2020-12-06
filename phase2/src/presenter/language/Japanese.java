package presenter.language;

public class Japanese implements Language {

    private final String fail = "失敗しました。もう一度確認してください。";
    private final String permission = "権限がないかもしれません。";

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
        return "失敗しました、もう一度確認してください。";
    }

    @Override
    public String messageTitle() {
        return "メール";
    }

    @Override
    public String createAccountMessage() {
        return "アカウントを作成しました";
    }

    @Override
    public String succeedAddRoomMessage() {
        return "新しい会場を作成しました";
    }

    @Override
    public String successful() {
        return "成功しました";
    }

    @Override
    public String failed() {
        return "失敗しました";
    }

    @Override
    public String failAddRoomMessage() {
        return "新しい会場の作成を" + fail;
    }

    @Override
    public String succeedCancelEventMessage() {
        return "新しいイベントを作りました。";
    }

    @Override
    public String failCancelEventMessage() {
        return "新しいイベントを作りません。もう一度確認してください。";
    }

    @Override
    public String succeedSignUpEventMessage() {
        return "イベントの申し込みを受け付きました。";
    }

    @Override
    public String failSignUpEvent() {
        return "イベントの申し込みを" + fail;
    }

    @Override
    public String succeedWaitEvent() {
        return "ウェイトリストの申し込みを受け付きました。";
    }

    @Override
    public String failWaitEventMessage() {
        return "ウェイトリストの申し込みを" + fail;
    }

    @Override
    public String succeedChangeEventMessage() {
        return "イベント情報を変更しました。";
    }

    @Override
    public String failChangeEventMessage() {
        return "イベント情報変更を" + fail + permission;
    }

    @Override
    public String succeedCreateUserMessage() {
        return "新しいユーザーを作りました。";
    }

    @Override
    public String failCreateUserMessage() {
        return fail + permission;
    }

    @Override
    public String succeedCreateEventMessage() {
        return "新しいイベントを作りました。";
    }

    @Override
    public String failCreateEventMessage() {
        return "新しいイベントの作成するのは" + fail;
    }

    @Override
    public String succeedCancelEnrollmentMessage() {
        return "イベントを辞めました。";
    }

    @Override
    public String failCancelEnrollmentMessage() {
        return "イベントを辞めるのは" + fail;
    }

    @Override
    public String succeedRemoveWaitMessage() {
        return "ウェイトリストをキャンセルしました。";
    }

    @Override
    public String failRemoveWaitMessage() {
        return "ウェイトリストをキャンセルする事を" + fail;
    }

    @Override
    public String succeedResetPasswordMessage() {
        return "パスワードをリセットしました。";
    }

    @Override
    public String failResetPasswordMessage() {
        return "パスワードをリセットのは" + fail;
    }

    @Override
    public String succeedCreateAccountMessage() {
        return "アカウントを作りました。";
    }

    @Override
    public String failCreateAccount() {
        return fail;
    }
}
