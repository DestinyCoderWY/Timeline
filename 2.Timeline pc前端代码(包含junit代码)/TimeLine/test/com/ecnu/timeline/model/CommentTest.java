package com.ecnu.timeline.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Comment Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>01/07/2019</pre>
 */
public class CommentTest {
    private Comment comment;
    private String time;
    private String userName;
    private String content;
    private int commentId;
    private String pic;

    @Before
    public void before() throws Exception {
        commentId = 13;
        userName = "nishitong";
        content = "hahahahaha";
        pic = "www.cctv.com";
        time = new Date().toString();
        comment = new Comment(commentId, userName, content, pic, time);
    }

    @After
    public void after() throws Exception {
        comment = null;
    }

    /**
     * Method: getSeriTime()
     */
    @Test
    public void testGetSeriTime() throws Exception {
        String getTime = comment.getSeriTime();
        assertEquals(time, getTime);
    }

    /**
     * Method: getId()
     */
    @Test
    public void testGetId() throws Exception {
        int getId = comment.getId();
        assertEquals(commentId, getId);
    }

    /**
     * Method: setId(int id)
     */
    @Test
    public void testSetId() throws Exception {
        int myId = 20;
        int oriId = comment.getId();
        comment.setId(myId);
        assertEquals(myId, comment.getId());
        comment.setId(oriId);
    }

    /**
     * Method: getUsername()
     */
    @Test
    public void testGetUsername() throws Exception {
        String getUsername = comment.getUsername();
        assertEquals(userName, getUsername);
    }

    /**
     * Method: setUsername(String username)
     */
    @Test
    public void testSetUsername() throws Exception {
        String myName = "wuyue";
        String oriName = comment.getUsername();
        comment.setUsername(myName);
        assertEquals(myName, comment.getUsername());
        comment.setUsername(oriName);
    }

    /**
     * Method: getCommment()
     */
    @Test
    public void testGetCommment() throws Exception {
        String getContent = comment.getCommment();
        assertEquals(content, getContent);
    }

    /**
     * Method: setCommment(String commment)
     */
    @Test
    public void testSetCommment() throws Exception {
        String myContent = "wuwuwuwuwuwuwuwu";
        String oriContent = comment.getCommment();
        comment.setCommment(myContent);
        assertEquals(myContent, comment.getCommment());
        comment.setUsername(oriContent);
    }

    /**
     * Method: getPicture()
     */
    @Test
    public void testGetPicture() throws Exception {
        String getPic = comment.getPicture();
        assertEquals(pic, getPic);
    }

    /**
     * Method: setPicture(String picture)
     */
    @Test
    public void testSetPicture() throws Exception {
        String myPic = "www.chine.org";
        String oriPic = comment.getPicture();
        comment.setPicture(myPic);
        assertEquals(myPic, comment.getPicture());
        comment.setUsername(oriPic);
    }

    /**
     * Method: getTime()
     */
    @Test
    public void testGetTime() throws Exception {
        String getTime = comment.getTime();
        assertEquals(time, getTime);
    }

    /**
     * Method: setTime(String time)
     */
    @Test
    public void testSetTime() throws Exception {
        String myTime = "1998-04-07";
        String oriTime = comment.getTime();
        comment.setTime(myTime);
        assertEquals(myTime, comment.getTime());
        comment.setTime(oriTime);
    }


} 
