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
}
