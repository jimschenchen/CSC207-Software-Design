package presenter.language;
/**
 * This is the interface used to implement the multi-language version used in our system
 */
public interface Language {
    /** the user name */
    String username();

    /** password */
    String password();

    /** sign up account */
    String signUpAccount();

    /** the meaning of ok*/
    String ok();

    /** exit */
    String exit();

    /** go back */
    String back();

    /** the title  */
    String titleLogin();

    /** the title for signing up */
    String titleSignUp();

    /** language */
    String language();

    /** select language */
    String selectLanguage();

    /** failed */
    String fail();

    /** the title of the message */
    String messageTitle();

    /** create account message */
    String createAccountMessage();

    /** a room has been successfully added */
    String succeedAddRoomMessage();

    /** successful */
    String successful();

    /** failed */
    String failed();

    /** filed to add room message */
    String failAddRoomMessage();

    /** successfully canceled event message */
    String succeedCancelEventMessage();

    /** failed to cancel event message */
    String failCancelEventMessage();

    /** successfully signup event message */
    String succeedSignUpEventMessage();

    /** failed to signup event */
    String failSignUpEvent();

    /** successfully wait event */
    String succeedWaitEvent();

    /** failed to wait event message */
    String failWaitEventMessage();

    /** successfully change event message */
    String succeedChangeEventMessage();

    /** failed to change event message */
    String failChangeEventMessage();

    /** successfully create user message */
    String succeedCreateUserMessage();

    /** failed to create user message */
    String failCreateUserMessage();

    /** successfully create event message */
    String succeedCreateEventMessage();

    /** failed to create event message */
    String failCreateEventMessage();

    /** successfully canceled enrollment message */
    String succeedCancelEnrollmentMessage();

    /** failed to cancel emrollment */
    String failCancelEnrollmentMessage();

    /** successfully removed wait message */
    String succeedRemoveWaitMessage();

    /** failed to removed wait message  */
    String failRemoveWaitMessage();

    /** successfully reset the password */
    String succeedResetPasswordMessage();

    /** failed reset the password */
    String failResetPasswordMessage();

    /** successfully create an account*/
    String succeedCreateAccountMessage();

    /** failed create an account*/
    String failCreateAccount();

    /** welcome */
    String welcome();

    /** my profile */
    String myProfile();

    /** logout  */
    String logOut();

    /** resetPass */
    String resetPass();

    /** schedule */
    String schedule();

    /** signUp */
    String signUp();

    /** sign Up Wait */
    String signUpWait();

    /** sign Up No wait */
    String signUpNowait();

    /**  view all My Events */
    String viewMyEvent();

    /** view My Wait */
    String viewMyWait();

    /** view my wait list */
    String viewMyNoWait();

    /** view all events */
    String viewAllEvent();

    /** messenger */
    String messenger();

    /** new pass */
    String newPass();

    /** event information */
    String eventInfo();

    /** with id */
    String withID();

    /** srart at */
    String startAt();

    /** end at */
    String endAt();

    /** the place that is going take */
    String takePlace();

    /** which is */
    String whichIs();

    /** title */
    String title();

    /** duration */
    String duration();

    /** starting time */
    String startTime();

    /** ending time */
    String endTime();

    /** VIP status */
    String vIPStatus();

    /** waiting list */
    String waitinglist();

    /** signup Questions */
    String signUpQuestion();

    /** yes */
    String yes();

    /** enterroom id */
    String enterRoomNum();

    /** room capacity */
    String enterRoomCapacity();

    /** create */
    String create();

    /** the type of the event */
    String eventType();

    /** party */
    String party();

    /** talk */
    String talk();

    /** discussion */
    String discussion();

    /** topic */
    String topic();

    /** room id */
    String roomNum();

    /** capacity */
    String capacity();

    /** no */
    String no();

    /** select speaker */
    String selectSpeaker();

    /** empty */
    String empty();

    /** user type */
    String userType();

    /** speaker */
    String speaker();

    /** organizer */
    String organizer();

    /** attendee */
    String attendee();

    /** cancel */
    String cancel();

    /** the rank */
    String rank();

    /** questions for confirmation*/
    String reallyQuestion();

    /** change */
    String change();

    /** new word */
    String newWord();

    /** view talks */
    String viewTalks();

    /** a new user */
    String newUser();

    /** a new room */
    String newRoom();

    /** new event */
    String newEvent();

    /** aorganized event */
    String organizedEvent();

    /** change event setting */
    String changeEventSetting();

    /** view all the organized event */
    String viewOrganizedEvent();

    /** event if */
    String eventId();
}
