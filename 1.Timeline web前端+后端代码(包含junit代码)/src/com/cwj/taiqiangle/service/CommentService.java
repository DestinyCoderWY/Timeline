package com.cwj.taiqiangle.service;

import com.cwj.taiqiangle.model.Comment;
import com.cwj.taiqiangle.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 蛟川小盆友 on 2018/12/18.
 */
public class CommentService {
    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    public List<Comment> getCommentAll() throws SQLException {
        conn = DBUtil.getConnection();
        List<Comment> commentBeans= new ArrayList<Comment>();
        String sql = "select a.comment_id,a.content,a.time,a.picture,b.user_name from comment AS a JOIN user as b on a.user_id=b.user_id order by a.comment_id desc limit 10;";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){

            Comment comment=new Comment();
            comment.setId(rs.getInt("comment_id"));
            comment.setCommment(rs.getString("content"));
            comment.setPicture(rs.getString("picture"));
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("time"));
            comment.setTime(timeStamp);
            comment.setUsername(rs.getString("user_name"));
            commentBeans.add(comment);
        }
        conn.close();

        return commentBeans;
    }
    public List<Comment> getMoreComment(int nowPos) throws SQLException {
        conn = DBUtil.getConnection();
        List<Comment> commentBeans= new ArrayList<Comment>();
        String sql = "select a.comment_id,a.content,a.time,a.picture,b.user_name from comment AS a JOIN user as b on a.user_id=b.user_id where a.comment_id< ? order by a.comment_id desc limit 10;";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,nowPos);
        rs = ps.executeQuery();
        while(rs.next()){

            Comment comment=new Comment();
            comment.setId(rs.getInt("comment_id"));
            comment.setCommment(rs.getString("content"));
            comment.setPicture(rs.getString("picture"));
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("time"));
            comment.setTime(timeStamp);
            comment.setUsername(rs.getString("user_name"));
            commentBeans.add(comment);
        }
        conn.close();

        return commentBeans;
    }

    public int addComment(int user_id,String content,String pic) throws SQLException {
        conn=DBUtil.getConnection();
        String sql="insert into comment(user_id,content,time,picture) values(?,?,?,?)";
        ps=conn.prepareStatement(sql);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ps.setObject(1,user_id);
        ps.setObject(2,content);
        ps.setObject(3,df.format(new Date()));
        ps.setObject(4,pic);
        int i=ps.executeUpdate();
        rs=ps.getGeneratedKeys();
        if(rs!=null)
        {
            rs.next();
            return i;
        }
        conn.close();
        return i;
    }
}
