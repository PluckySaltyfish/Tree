package com.example.plucky.mytree.mail;


import android.support.annotation.NonNull;


import com.example.plucky.mytree.connection.RemoteData;

import java.io.File;

public class SendMailUtil {
    //qq
    private static final String HOST = "smtp.qq.com";
    private static final String PORT = "587";
    private static final String FROM_ADD = "1828151761@qq.com"; //发送方邮箱
    private static final String FROM_PSW = "Bowtiesarecool11";//发送方邮箱授权码

    public static void send(String toAdd){
        final MailInfo mailInfo = creatMail(toAdd);
        final MailSender sms = new MailSender();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sms.sendTextMail(mailInfo);
            }
        }).start();
    }

    @NonNull
    private static MailInfo creatMail(String toAdd) {
        final MailInfo mailInfo = new MailInfo();
        mailInfo.setMailServerHost(HOST);
        mailInfo.setMailServerPort(PORT);
        mailInfo.setValidate(true);
        mailInfo.setUserName(FROM_ADD); // 你的邮箱地址
        mailInfo.setPassword(FROM_PSW);// 您的邮箱密码
        mailInfo.setFromAddress(FROM_ADD); // 发送的邮箱
        mailInfo.setToAddress(toAdd); // 发到哪个邮件去
        mailInfo.setSubject("Tree密码找回"); // 邮件主题

        String RandomPassword ="";

        RemoteData mRemoteData = new RemoteData();
        mRemoteData.AlterPassword(RandomPassword);
        mailInfo.setContent("您的账户密码已经重置为 "+ RandomPassword +" ,请在24h内登录账号进行密码修改。"); // 邮件文本
        return mailInfo;
    }
}
