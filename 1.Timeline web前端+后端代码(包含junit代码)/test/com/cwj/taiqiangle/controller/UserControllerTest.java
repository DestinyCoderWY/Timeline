package com.cwj.taiqiangle.controller;

import com.cwj.taiqiangle.service.UsersService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertEquals;

/** 
* UserController Tester. 
* 
* @author <Authors name> 

* @version 1.0 
*/ 
public class UserControllerTest {
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
public void testUserLogin() throws Exception {
    assertEquals("200",uc.userLogin("zyw","123").getCode());
} 


@Test
public void testUserRegisterWithExistUsername() throws Exception {
    assertEquals("404",uc.userRegister("zyw","123").getCode());
}

@Test
public void testUserRegisterWithUnExistUsername() throws Exception {
    assertEquals("200",uc.userRegister("zyw1111","123").getCode());
}


} 
