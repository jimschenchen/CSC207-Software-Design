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
        return("语言");
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
}
