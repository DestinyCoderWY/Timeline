package com.cwj.taiqiangle.controller;

import com.cwj.taiqiangle.service.UsersService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * CommentController Tester.
 *
 * @author <Authors name>
 * @since <pre>һ�� 8, 2019</pre>
 * @version 1.0
 */
public class CommentControllerTest {

    private CommentController cc;
    private UserController uc;
    private UsersService us;
    private int id;
    @Before
    public void before() throws Exception {
        cc = new CommentController();
        uc = new UserController();
        us = new UsersService();
        id = us.getUserByName("zyw").get(0).getUser_id();
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testMsgTraversal() throws Exception {
        assertEquals("200",cc.msgTraversal().getCode());
    }


    @Test
    public void testGetmore() throws Exception {
        assertEquals("200",cc.getmore(1).getCode());
    }


    @Test
    public void testAddComment() throws Exception {
        assertEquals("200",cc.addComment(id,"test","").getCode());
    }


} 
