package com.example.plucky.mytree.mail;


import android.support.annotation.NonNull;


import com.example.plucky.mytree.connection.RemoteData;

import java.io.File;

public class SendMailUtil {
    //qq
    private static final String HOST = "smtp.qq.com";
    private static final String PORT = "587";
    private static final String FROM_ADD = "1828151761@qq.com"; //发送方邮箱
    private static final String FROM_PSW = "ywcwphforxtucffg";//发送方邮箱授权码

    public static void send(String toAdd,String content){
        final MailInfo mailInfo = creatMail(toAdd,content);
        final MailSender sms = new MailSender();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sms.sendTextMail(mailInfo);
            }
        }).start();
    }

    @NonNull
    private static MailInfo creatMail(String toAdd,String content) {
        final MailInfo mailInfo = new MailInfo();
        mailInfo.setMailServerHost(HOST);
        mailInfo.setMailServerPort(PORT);
        mailInfo.setValidate(true);
        mailInfo.setUserName(FROM_ADD); // 你的邮箱地址
        mailInfo.setPassword(FROM_PSW);// 您的邮箱密码
        mailInfo.setFromAddress(FROM_ADD); // 发送的邮箱
        mailInfo.setToAddress(toAdd); // 发到哪个邮件去
        mailInfo.setSubject("Tree App密码找回"); // 邮件主题
        mailInfo.setContent("【Tree】欢迎使用Tree App密码找回服务，验证码"+content+"。\n太阳系爱你哟（づ￣3￣）づ╭❤～"); // 邮件文本
        return mailInfo;
    }
}
