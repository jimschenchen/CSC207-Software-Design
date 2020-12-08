package presenter.language;

public class Japanese implements Language{

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
        return "アカウントを作成する";
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

    @Override
    public String welcome() {
        return null;
    }

    @Override
    public String myProfile() {
        return null;
    }

    @Override
    public String logOut() {
        return null;
    }

    @Override
    public String resetPass() {
        return null;
    }

    @Override
    public String schedule() {
        return null;
    }

    @Override
    public String signUp() {
        return null;
    }

    @Override
    public String signUpWait() {
        return null;
    }

    @Override
    public String signUpNowait() {
        return null;
    }

    @Override
    public String viewMyEvent() {
        return null;
    }

    @Override
    public String viewMyWait() {
        return null;
    }

    @Override
    public String viewMyNoWait() {
        return null;
    }

    @Override
    public String viewAllEvent() {
        return null;
    }

    @Override
    public String messenger() {
        return null;
    }

    @Override
    public String newPass() {
        return null;
    }


    @Override
    public String withID() {
        return null;
    }

    @Override
    public String startAt() {
        return null;
    }

    @Override
    public String endAt() {
        return null;
    }

    @Override
    public String takePlace() {
        return null;
    }

    @Override
    public String whichIs() {
        return null;
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public String duration() {
        return null;
    }

    @Override
    public String startTime() {
        return null;
    }

    @Override
    public String endTime() {
        return null;
    }

    @Override
    public String vIPStatus() {
        return null;
    }

    @Override
    public String waitinglist() {
        return null;
    }

    @Override
    public String signUpQuestion() {
        return null;
    }

    @Override
    public String yes() {
        return null;
    }

    @Override
    public String enterRoomNum() {
        return null;
    }

    @Override
    public String enterRoomCapacity() {
        return null;
    }

    @Override
    public String create() {
        return null;
    }

    @Override
    public String eventType() {
        return null;
    }

    @Override
    public String party() {
        return null;
    }

    @Override
    public String talk() {
        return null;
    }

    @Override
    public String discussion() {
        return null;
    }

    @Override
    public String topic() {
        return null;
    }

    @Override
    public String roomNum() {
        return null;
    }

    @Override
    public String capacity() {
        return null;
    }

    @Override
    public String no() {
        return null;
    }

    @Override
    public String selectSpeaker() {
        return null;
    }

    @Override
    public String empty() {
        return null;
    }

    @Override
    public String userType() {
        return null;
    }

    @Override
    public String speaker() {
        return null;
    }

    @Override
    public String organizer() {
        return null;
    }

    @Override
    public String attendee() {
        return null;
    }

    @Override
    public String cancel() {
        return null;
    }

    @Override
    public String rank() {
        return null;
    }

    @Override
    public String reallyQuestion() {
        return null;
    }

    @Override
    public String change() {
        return null;
    }


    @Override
    public String viewTalks() {
        return null;
    }

    @Override
    public String newUser() {
        return null;
    }

    @Override
    public String newRoom() {
        return null;
    }

    @Override
    public String newEvent() {
        return null;
    }

    @Override
    public String organizedEvent() {
        return null;
    }

    @Override
    public String changeEventSetting() {
        return null;
    }

    @Override
    public String viewOrganizedEvent() {
        return null;
    }

    @Override
    public String eventId() {
        return null;
    }

    @Override
    public String cancelled() {
        return null;
    }

    @Override
    public String signedUp() {
        return null;
    }

    @Override
    public String addSpeaker() {
        return null;
    }

    @Override
    public String newCapacity() {
        return null;
    }

    @Override
    public String newStatus() {
        return null;
    }

    @Override
    public String send() {
        return null;
    }

    @Override
    public String receivedMessages() {
        return null;
    }

    @Override
    public String sentMessage(){
        return ("Sent Message");
    }

    @Override
    public String close() {
        return null;
    }

    @Override
    public String messengerOptions() {
        return null;
    }

    @Override
    public String viewMyMessages() {
        return null;
    }

    @Override
    public String message() {
        return null;
    }

    @Override
    public String writeMessage() {
        return null;
    }

    @Override
    public String writeTitle() {
        return null;
    }

    @Override
    public String allAttendees() {
        return null;
    }

    @Override
    public String oneAttendee() {
        return "1人の参加者";
    }

    @Override
    public String sendTo() {
        return "このメッセージを誰に送信しますか";
    }

    @Override
    public String messageInformation() {
        return "メッセージ情報";
    }

    @Override
    public String enterIdEvent() {
        return "イベントのIDを入力してください";
    }

    @Override
    public String enterNameAttendee() {
        return "出席者のユーザー名を入力します";
    }

    @Override
    public String writeNewMes() {
        return "新しいメッセージを書く";
    }

    @Override
    public String reply() {
        return "応答";
    }

    @Override
    public String writeMesHere() {
        return "ここにメッセージを書いてください";
    }

    @Override
    public String chooseTitle() {
        return "タイトルを選択してください";
    }

    @Override
    public String succeedSendMes() {
        return "メッセージは正常に送信されました";
    }

    @Override
    public String enterTitleMes() {
        return "メッセージのタイトルを入力してください";
    }

    @Override
    public String mesAllSpeakers() {
        return "すべてのスピーカーにメッセージを送る";
    }

    @Override
    public String mesOneSpeaker() {
        return "1人のスピーカーにメッセージを送る";
    }

    @Override
    public String mesAllAttendees() {
        return "すべての参加者にメッセージを送る";
    }

    @Override
    public String mesOneAttendees() {
        return "1人の参加者にメッセージを送る";
    }

    @Override
    public String enterNameSpeaker() {
        return "スピーカーのユーザー名を入力します";
    }

    @Override
    public String mesAllAttendeesEvent() {
        return "私のイベントのすべての参加者にメッセージを送る";
    }

    @Override
    public String mesOneAttendeeEvent() {
        return "私のイベントの1人の参加者にメッセージを送る";
    }

    @Override
    public String receivedEmail() {
        return ("受信したメール");
    }
}
