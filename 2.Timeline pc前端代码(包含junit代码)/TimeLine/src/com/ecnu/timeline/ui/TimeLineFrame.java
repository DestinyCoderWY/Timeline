package com.ecnu.timeline.ui;

import com.ecnu.timeline.model.Comment;
import com.ecnu.timeline.webUtil.HttpUtil;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TimeLineFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 900;
    private static final int FONT_SIZE = 36;
    private static final Font DEFAULT_FONT = new Font("宋体", Font.PLAIN, FONT_SIZE);
    public JDialog dialog = null;


    private java.util.List<Comment> commentList = new ArrayList<>();
    private HttpUtil httpUtil = HttpUtil.getHttpUtil();
    private String userName;
    private int userId;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public TimeLineFrame(String userName, int useId){

        this.userName = userName;
        this.userId = useId;

        setTitle("TimeLine");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
        } );


        JLabel timeLineLabel = new JLabel("TimeLine");
        timeLineLabel.setFont(new Font("宋体", Font.PLAIN, 42));
        JButton refreshButton = new JButton("刷新");
        refreshButton.setContentAreaFilled(false);
        refreshButton.setFont(DEFAULT_FONT);
        refreshButton.addActionListener(e -> refresh());

        Box topHBox = Box.createHorizontalBox();
        topHBox.add(Box.createGlue());
        topHBox.add(timeLineLabel);
        // use "glue" to push the two buttons apart
        topHBox.add(Box.createGlue());
        topHBox.add(Box.createGlue());
        topHBox.add(refreshButton);
        topHBox.add(Box.createGlue());


        JButton moreThingsButton = new JButton("更多");
        moreThingsButton.setFont(DEFAULT_FONT);
        moreThingsButton.setContentAreaFilled(false);
        moreThingsButton.addActionListener(e -> more());

        JButton addThingButton = new JButton("添加");
        addThingButton.setFont(DEFAULT_FONT);
        addThingButton.setContentAreaFilled(false);
        addThingButton.addActionListener(e -> {
            showAddCommentDialog();
            dialog.setVisible(true);
        });

        Box bottomHBox = Box.createHorizontalBox();
        bottomHBox.add(Box.createGlue());
        bottomHBox.add(moreThingsButton);
        bottomHBox.add(Box.createGlue());
        bottomHBox.add(addThingButton);
        bottomHBox.add(Box.createGlue());

        add(topHBox, BorderLayout.NORTH);
        add(bottomHBox,BorderLayout.SOUTH);

    }

    private void loadData(){
        //获取comments  -> 在其他函数中设定comments


        Box contentVBox = Box.createVerticalBox();
        for(int i = 0; i < commentList.size(); i++){
            contentVBox.add(ContentBox.createContentBox(commentList.get(i)));
        }

        JScrollPane scrollPane = new JScrollPane(contentVBox);
        add(scrollPane,BorderLayout.CENTER);

        validate();
        repaint();

    }

    private void showAddCommentDialog(){
        dialog = new JDialog(this, "新的消息", true);
        // 设置对话框的宽高
        dialog.setSize(400, 500);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(this);

        // 创建一个标签显示消息内容
        JLabel messageLabel = new JLabel("你的分享");
        messageLabel.setFont(new Font("宋体", Font.PLAIN, 24));
        Box titleBox = Box.createHorizontalBox();
        titleBox.add(Box.createGlue());
        titleBox.add(messageLabel);
        titleBox.add(Box.createGlue());

        JTextArea yourCommentText = new JTextArea(10,25);
        yourCommentText.setBackground(new Color(216, 220, 238));
        yourCommentText.setFont(new Font("宋体", Font.PLAIN, 28));
        yourCommentText.setLineWrap(true);
        yourCommentText.setWrapStyleWord(true);
        JLabel glueLabel1 = new JLabel(" ");
        glueLabel1.setFont(DEFAULT_FONT);
        JLabel glueLabel2 = new JLabel(" ");
        glueLabel2.setFont(DEFAULT_FONT);
        Box inputBox = Box.createHorizontalBox();
        inputBox.add(glueLabel1);
        inputBox.add(yourCommentText);
        inputBox.add(glueLabel2);

        JScrollPane scrollPane = new JScrollPane(inputBox);

        // 创建一个按钮用于关闭对话框
        JButton okButton = new JButton("上传");
        okButton.setContentAreaFilled(false);
        okButton.addActionListener(e1 -> {
            String yourComment =  yourCommentText.getText();
            Comment comment = new Comment(0,userName,yourComment,"",new Date().toString());
            int result = httpUtil.addNewComment(userId,comment);
            if(result != 0){
                JOptionPane.showMessageDialog(null, "添加成功！！！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "添加失败！！！", "提示", JOptionPane.ERROR_MESSAGE);
            }
            dialog.dispose();
        });
        JButton cancelButton = new JButton("取消");
        cancelButton.setContentAreaFilled(false);
        cancelButton.addActionListener(e1 -> dialog.dispose());
        Box bottomHBox = Box.createHorizontalBox();
        bottomHBox.add(Box.createGlue());
        bottomHBox.add(okButton);
        bottomHBox.add(Box.createGlue());
        bottomHBox.add(Box.createGlue());
        bottomHBox.add(cancelButton);
        bottomHBox.add(Box.createGlue());


        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
        JPanel panel = new JPanel();


        panel.setLayout(new BorderLayout());
        // 添加组件到面板
        panel.add(titleBox,BorderLayout.NORTH);
        panel.add(scrollPane,BorderLayout.CENTER);
        panel.add(bottomHBox,BorderLayout.SOUTH);

        // 设置对话框的内容面板
        dialog.setContentPane(panel);
        // 显示对话框

    }

    private void removeContent(){
        Component[] components = getContentPane().getComponents();

        for(Component co:components) {
            if (co.getName() != "") {
                String a = co.getClass().getName();
                if (a.equals(JScrollPane.class.getName())) {
                    getContentPane().remove(co);
                }
            }
        }
    }

    public void initData(){
        EventQueue.invokeLater( () -> {
                    removeContent();
                    commentList = httpUtil.getNewestTenComments();
                    loadData();
                }
        );
    }


    private void refresh(){
        initData();
    }

    private void more(){
        EventQueue.invokeLater(() -> {

                    removeContent();
                    Comment lastComment = commentList.get((commentList.size()-1));
                    java.util.List<Comment> nextTenComments = httpUtil.getNextTenComments(lastComment.getId());

                    commentList.addAll(nextTenComments);

                    loadData();
                }
        );
    }


}
