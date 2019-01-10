package com.cwj.taiqiangle.service;

import com.cwj.taiqiangle.controller.CommentController;
import com.cwj.taiqiangle.controller.UserController;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertEquals;

/** 
* CommentService Tester. 
* 

* @version 1.0 
*/ 
public class CommentServiceTest {
    private CommentController cc;
    private CommentService cs;
    private UserController uc;
    private UsersService us;
    private int id;
@Before
public void before() throws Exception {
    cc = new CommentController();
    cs = new CommentService();
    uc = new UserController();
    us = new UsersService();
    id = us.getUserByName("zyw").get(0).getUser_id();
} 

@After
public void after() throws Exception { 
} 


@Test
public void testGetCommentAllSizeShouldBe10() throws Exception {
    assertEquals(10,cs.getCommentAll().size());
} 

@Test
public void testGetMoreCommentSizeShouldBe0() throws Exception {
    assertEquals(0,cs.getMoreComment(0).size());
}

@Test
public void testGetMoreCommentSizeShouldBe10() throws Exception {
    assertEquals(10,cs.getMoreComment(999999).size());
}

@Test
public void testAddCommentSuccess() throws Exception {
    assertEquals(1,cs.addComment(id,"test",""));
}


} 
