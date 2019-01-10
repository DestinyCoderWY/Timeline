package com.cwj.taiqiangle.service;

import com.cwj.taiqiangle.model.user;
import com.cwj.taiqiangle.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蛟川小盆友 on 2018/12/18.
 */
public class UsersService {
    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<user> getUserByName(String name) throws SQLException {
        conn = DBUtil.getConnection();
        List<user> userBeans = new ArrayList<user>();
        String sql = "select * from user where user_name= ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1, name);
        rs = ps.executeQuery();
        while(rs.next()){
            user userBean=new user();
            userBean.setUser_id(rs.getInt("user_id"));
            userBean.setPassword(rs.getString("password"));
            userBean.setUser_name(rs.getString("user_name"));

            userBeans.add(userBean);
        }
        conn.close();
        if(userBeans.size()>0){
            System.out.println("GetUserByName中看看个人描述"+userBeans.get(0).getUser_name());
        }
        return userBeans;
    }
    public int userReg(String name, String pass) throws SQLException {
        conn = DBUtil.getConnection();
        //List<user> userBeans = new ArrayList<user>();
        String sql = "INSERT INTO user (user_name, password) VALUES (?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setObject(1, name);
        ps.setObject(2, pass);
        int t = ps.executeUpdate();
        conn.close();

        return t;
    }
}
