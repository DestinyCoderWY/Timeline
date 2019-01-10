package com.ecnu.timeline.ui;

import com.ecnu.timeline.webUtil.HttpUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 700;
    private static final int DEFAULT_HEIGHT = 800;
    private static final int FONT_SIZE = 36;
    private static final int TEXT_WIDTH = 150;
    private static final int TEXT_HEIGHT = 50;

    private HttpUtil httpUtil = HttpUtil.getHttpUtil();
    private int userId = 0;
    private String userName = "";

    private boolean login(String userName, String password){
        userId = httpUtil.login(userName,password);
        if(userId != 0){
            this.userName = userName;
            return true;
        }
        return false;
    }

    private boolean register(String userName, String password){
        boolean result = !("".equals(userName));
        if(result){
            userId = httpUtil.register(userName,password);
            if(userId != 0){
                this.userName = userName;
                result = true;
            }
        }
        return result;
    }

    public LoginFrame(){

        setTitle("登录");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {  LoginFrame.super.dispose();
            }
        } );

        // construct the top horizontal box

        JLabel userNameLabel = new JLabel("用户名:");
        userNameLabel.setFont(new Font("宋体", Font.PLAIN, FONT_SIZE));
        JTextField userNameText = new JTextField(20);
        userNameText.setPreferredSize(new Dimension(TEXT_WIDTH,TEXT_HEIGHT));
        userNameText.setMaximumSize(userNameText.getPreferredSize());
        userNameText.setFont(new Font("宋体", Font.PLAIN, FONT_SIZE));

        Box userNameHBox = Box.createHorizontalBox();
        userNameHBox.add(Box.createGlue());
        userNameHBox.add(userNameLabel);
        // separate with a 10-pixel strut
        userNameHBox.add(Box.createHorizontalStrut(50));
        userNameHBox.add(userNameText);
        userNameHBox.add(Box.createGlue());

        // construct the middle horizontal box

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(new Font("宋体", Font.PLAIN, FONT_SIZE));
        JPasswordField  passwordText = new JPasswordField (20);
        passwordText.setPreferredSize(new Dimension(TEXT_WIDTH,TEXT_HEIGHT));
        passwordText.setMaximumSize(userNameText.getPreferredSize());
        passwordText.setFont(new Font("宋体", Font.PLAIN, FONT_SIZE));


        Box passwordHBox = Box.createHorizontalBox();
        passwordHBox.add(Box.createGlue());
        passwordHBox.add(passwordLabel);
        // separate with a 10-pixel strut
        passwordHBox.add(Box.createHorizontalStrut(50));
        passwordHBox.add(passwordText);
        passwordHBox.add(Box.createGlue());

        // construct the bottom horizontal box

        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("宋体", Font.PLAIN, FONT_SIZE));
        loginButton.setContentAreaFilled(false);
        loginButton.addActionListener((ActionEvent e) -> {

            if(login(userNameText.getText(),new String(passwordText.getPassword()))){
                this.setVisible(false);
                TimeLineFrame f = new TimeLineFrame(userName,userId);
                f.initData();
                f.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "登录失败！！！", "提示", JOptionPane.ERROR_MESSAGE);
            }

        });
        JButton clearButton = new JButton("清空");
        clearButton.setFont(new Font("宋体", Font.PLAIN, FONT_SIZE));
        clearButton.setContentAreaFilled(false);
        clearButton.addActionListener((ActionEvent e) -> {
            userNameText.setText("");
            passwordText.setText("");
        });
        JButton registerButton = new JButton("注册新用户");
        registerButton.setFont(new Font("宋体", Font.PLAIN, FONT_SIZE));
        registerButton.setContentAreaFilled(false);
        registerButton.addActionListener((ActionEvent e) -> {

            if(register(userNameText.getText(),new String(passwordText.getPassword()))){
                JOptionPane.showMessageDialog(null, "注册成功！！！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "注册失败！！！", "提示", JOptionPane.ERROR_MESSAGE);
            }

        });

        Box buttonsHBox = Box.createHorizontalBox();
        buttonsHBox.add(Box.createGlue());
        buttonsHBox.add(loginButton);
        // use "glue" to push the two buttons apart
        buttonsHBox.add(Box.createGlue());
        buttonsHBox.add(clearButton);
        buttonsHBox.add(Box.createGlue());
        buttonsHBox.add(registerButton);
        buttonsHBox.add(Box.createGlue());

        // add the three horizontal boxes inside a vertical box

        Box mainVBox = Box.createVerticalBox();
        mainVBox.add(Box.createGlue());
        mainVBox.add(userNameHBox);
        mainVBox.add(passwordHBox);
        mainVBox.add(Box.createGlue());
        mainVBox.add(buttonsHBox);
        mainVBox.add(Box.createGlue());

        add(mainVBox, BorderLayout.CENTER);
    }

}
