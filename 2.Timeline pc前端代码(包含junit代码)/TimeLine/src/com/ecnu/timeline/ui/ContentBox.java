package com.ecnu.timeline.ui;

import com.ecnu.timeline.model.Comment;

import javax.swing.*;
import java.awt.*;

public class ContentBox {

    private static final Font CONTENT_FONT = new Font("宋体", Font.PLAIN, 20);


    public static Box createContentBox(Comment comment){
        JLabel authorLabel1 = new JLabel();
        authorLabel1.setText(comment.getUsername());
        authorLabel1.setFont(CONTENT_FONT);
        JLabel timeLabel1 = new JLabel();
        timeLabel1.setText(comment.getSeriTime());
        timeLabel1.setFont(CONTENT_FONT);

        Box titleHBox1 = Box.createHorizontalBox();
        titleHBox1.add(Box.createHorizontalStrut(50));
        titleHBox1.add(authorLabel1);
        titleHBox1.add(Box.createGlue());
        titleHBox1.add(Box.createGlue());
        titleHBox1.add(timeLabel1);
        titleHBox1.add(Box.createHorizontalStrut(50));

        JTextArea contentText1 = new JTextArea(1,15);
        contentText1.setText(comment.getCommment());
        contentText1.setBackground(new Color(213, 228, 238));
        contentText1.setFont(new Font("宋体", Font.PLAIN, 28));
        contentText1.setLineWrap(true);
        contentText1.setWrapStyleWord(true);



        Box timeVBox1 = Box.createVerticalBox();
        timeVBox1.add(titleHBox1);
        timeVBox1.add(contentText1);

        return timeVBox1;

    }
}
