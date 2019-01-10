package com.cwj.taiqiangle.service;

import com.cwj.taiqiangle.controller.UserController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** 
* UsersService Tester. 
* 
* @author <Authors name> 

* @version 1.0 
*/ 
public class UsersServiceTest {
    private UserController uc;
    private UsersService us;
    private int id;
@Before
public void before() throws Exception {
    uc = new UserController();
    us = new UsersService();
    id = us.getUserByName("zyw").get(0).getUser_id();
} 

@After
public void after() throws Exception { 
} 

@Test
public void testGetUserByNameSuccess() throws Exception {
    assertEquals("zyw",us.getUserByName("zyw").get(0).getUser_name());
}
@Test
public void testGetUserByNameFail() throws Exception {
    assertEquals(0,us.getUserByName("zzz").size());
}

@Test
public void testUserReg() throws Exception {
    assertEquals(1,us.userReg("zyw222","123"));
} 


} 
