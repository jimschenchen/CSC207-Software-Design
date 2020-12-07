package presenter.language;

import presenter.language.Language;

public class SimplifiedChinese implements Language {
    @Override
    public String username(){
        return ("用户名：");
    };

    @Override
    public String password(){
        return ("密码: ");
    };

    @Override
    public String signUpAccount(){
        return ("免费注册账户！");
    };

    @Override
    public String ok(){
        return ("确认");
    };

    @Override
    public String exit(){
        return ("退出");
    };

    @Override
    public String titleLogin(){
        return ("欢迎来到最NB的会议");
    };

    @Override
    public String back() {
        return ("返回");
    }

    @Override
    public String titleSignUp() {
        return ("你正在注册!");
    }

    @Override
    public String language(){
        return("简体中文");
    }

    @Override
    public String selectLanguage(){
        return("选择语言！ ");
    }

    @Override
    public String fail() {
        return ("糟糕！ 好像有什么东西搞错了");
    }

    @Override
    public String messageTitle() {
        return ("消息");
    }

    @Override
    public String createAccountMessage() {
        return ("亲！你成功创建了一个账户呢！");
    }

    @Override
    public String succeedAddRoomMessage(){
        return ("创建房间成功");
    }
    @Override
    public String successful(){
        return ("成功！");
    }
    @Override
    public String failed(){
        return ("失败");
    }
    @Override
    public String failAddRoomMessage(){
        return("创建房间失败");
    }
    @Override
    public String succeedCancelEventMessage(){
        return("取消事件成功");
    }

    @Override
    public String failCancelEventMessage(){
        return("取消事件失败");
    }

    @Override
    public String succeedSignUpEventMessage(){
        return("报名事件成功");
    }

    @Override
    public String failSignUpEvent(){
        return("报名事件失败");
    }

    @Override
    public String succeedWaitEvent(){
        return("成功加入等待名单");
    }

    @Override
    public String failWaitEventMessage(){
        return("加入等待名单失败");
    }

    @Override
    public String succeedChangeEventMessage(){
        return("成功修改事件");
    }

    @Override
    public String failChangeEventMessage(){
        return("修改事件失败");
    }

    @Override
    public String succeedCreateUserMessage(){
        return("成功创建用户");
    }

    @Override
    public String failCreateUserMessage(){
        return("创建用户失败");
    }

    @Override
    public String succeedCreateEventMessage(){
        return("成功创建事件");
    }

    @Override
    public String failCreateEventMessage(){
        return("创建事件失败");
    }

    @Override
    public String succeedCancelEnrollmentMessage(){
        return("成功取消事件资格");
    }

    @Override
    public String failCancelEnrollmentMessage(){
        return("取消事件资格失败");
    }

    @Override
    public String succeedRemoveWaitMessage(){
        return("成功退出等待名单");
    }

    @Override
    public String failRemoveWaitMessage(){
        return("退出等待名单失败");
    }

    @Override
    public String succeedResetPasswordMessage(){
        return("成功设置新密码");
    }

    @Override
    public String failResetPasswordMessage(){
        return("设置新密码失败");
    }

    @Override
    public String succeedCreateAccountMessage(){
        return("成功创建账户");
    }

    @Override
    public String failCreateAccount(){
        return("创建账户失败");
    }

    @Override
    public String welcome(){
        return ("欢迎");
    }
    @Override
    public String myProfile(){
        return ("个人资料");
    }
    @Override
    public String logOut(){
        return ("登出");
    }
    @Override
    public String resetPass(){
        return ("重置密码");
    }
    @Override
    public String schedule(){
        return ("日程表");
    }
    @Override
    public String signUp(){
        return ("活动报名");
    }

    @Override
    public String signUpWait(){
        return ("加入活动的等待列表");
    }
    @Override
    public String signUpNowait(){
        return ("无需等待");
    }
    @Override
    public String viewMyEvent(){
        return ("我的所有活动");
    }
    @Override
    public String  viewMyWait(){
        return ("我的等待列表");
    }
    @Override
    public String  viewMyNoWait(){
        return ("我的非等待列表");
    }
    @Override
    public String  viewAllEvent(){
        return ("查看所有活动");
    }
    @Override
    public String  messenger(){
        return ("消息系统");
    }
    @Override
    public String  newPass(){
        return ("新密码");
    }
    @Override
    public String  eventInfo(){
        return ("活动信息");
    }
    @Override
    public String  withID(){
        return (",ID 为 ");
    }
    @Override
    public String  startAt(){
        return (" 开始于 ");
    }
    @Override
    public String  endAt(){
        return (" 结束于 ");
    }
    @Override
    public String  takePlace(){
        return (" 举办在 ");
    }
    @Override
    public String  whichIs(){
        return (" 是 ");
    }

    @Override
    public String  title(){
        return ("标题: ");
    }
    @Override
    public String  duration(){
        return ("时长: ");
    }
    @Override
    public String  startTime(){
        return ("开始时间: ");
    }
    @Override
    public String  endTime(){
        return ("结束时间: ");
    }
    @Override
    public String  vIPStatus(){
        return ("是否为VIP活动: ");
    }
    @Override
    public String  waitinglist(){
        return ("等待列表: ");
    }
    @Override
    public String  signUpQuestion(){
        return ("报名?");
    }
    @Override
    public String  yes(){
        return ("是!");
    }
    @Override
    public String  enterRoomNum(){
        return ("输入你想新建的房间号码: ");
    }
    @Override
    public String  enterRoomCapacity(){
        return ("输入新房间能容纳的最大人数:");
    }
    @Override
    public String  create(){
        return ("创建");
    }
    @Override
    public String  eventType(){
        return ("活动类型: ");
    }
    @Override
    public String  party(){
        return ("聚会");
    }
    @Override
    public String  talk(){
        return ("演讲");
    }
    @Override
    public String  discussion(){
        return ("论坛会");
    }
    @Override
    public String  topic(){
        return ("标题: ");
    }
    @Override
    public String  roomNum(){
        return ("房间号: ");
    }
    @Override
    public String  capacity(){
        return ("容量: ");
    }
    @Override
    public String  no(){
        return ("否!");
    }
    @Override
    public String  selectSpeaker(){
        return ("选择演讲者: ");
    }
    @Override
    public String  empty(){
        return ("清空");
    }

    @Override
    public String  userType(){
        return ("用户类型: ");
    }
    @Override
    public String  speaker(){
        return ("演讲者");
    }
    @Override
    public String  organizer(){
        return ("举办者");
    }
    @Override
    public String  attendee(){
        return ("参加者");
    }
    @Override
    public String  cancel(){
        return ("取消");
    }
    @Override
    public String  rank(){
        return ("排名: ");
    }
    @Override
    public String  reallyQuestion(){
        return ("真的确定么??");
    }
    @Override
    public String  change(){
        return ("改动: ");
    }
    @Override
    public String  newWord(){
        return ("活动新设定: ");
    }
    @Override
    public String  viewTalks(){
        return ("查看我的演讲: ");
    }
    @Override
    public String  newUser(){
        return ("新的用户");
    }
    @Override
    public String  newRoom(){
        return ("新的房间");
    }
    @Override
    public String  newEvent(){
        return ("新活动");
    }
    @Override
    public String  organizedEvent(){
        return ("举办的活动");
    }
    @Override
    public String  changeEventSetting(){
        return ("改变活动设定");
    }
    @Override
    public String viewOrganizedEvent(){
        return ("查看举办的活动");
    }
    @Override
    public String eventId(){
        return ("会议代码");
    }

}
