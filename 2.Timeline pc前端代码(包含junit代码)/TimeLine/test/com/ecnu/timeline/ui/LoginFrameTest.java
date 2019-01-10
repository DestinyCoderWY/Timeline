package com.ecnu.timeline.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.awt.event.ActionEvent.ACTION_PERFORMED;
import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

/**
 * LoginFrame Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>01/08/2019</pre>
 */
public class LoginFrameTest {
    private LoginFrame loginFrame;
    private String username = "wy";
    private String password = "123";

    @Before
    public void before() throws Exception {
        loginFrame = new LoginFrame();
    }

    @After
    public void after() throws Exception {

        loginFrame.dispose();
    }

    /*@Test
    public void testWIndos() throws Exception{
        *//*loginFrame.setVisible(true);*//*
        *//*try {
            sleep(1000);
        }catch (Exception e){

        }*//*
        Component[] components = loginFrame.getComponents();
        for(Component co : components){
            if(co.getName() != ""){
                if(co.getClass().getName().equals(Box.class.getName())){
                    Box buttonBox = (Box)((Box)co).getComponent(2);
                    JButton loginButton = (JButton) buttonBox.getComponent(0);
                    loginButton.dispatchEvent(new ActionEvent(loginFrame,ActionEvent.ACTION_PERFORMED,""));

                }
            }
        }
 *//*       loginFrame.dispatchEvent(new WindowEvent(loginFrame,WindowEvent.WINDOW_CLOSING));*//*


    }*/

    /**
     * Method: login(String userName, String password)
     */
    @Test
    public void testLogin() throws Exception {

        try {
            Method method = loginFrame.getClass().getDeclaredMethod("login", String.class, String.class);
            method.setAccessible(true);
            boolean result = (boolean)method.invoke(loginFrame,username,password);
            assertTrue(result);
            boolean result1 = (boolean)method.invoke(loginFrame,username,"1234");
            assertFalse(result1);

        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }

    }

    /**
     * Method: register(String userName, String password)
     */
    @Test
    public void testRegister() throws Exception {

        try {
            Method method = loginFrame.getClass().getDeclaredMethod("register", String.class, String.class);
            method.setAccessible(true);
            boolean result = (boolean)method.invoke(  loginFrame,username+8,password );
            assertTrue(result);
            boolean result1 = (boolean)method.invoke(  loginFrame,"",password );
            assertFalse(result1);

        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }

    }


} 
