package com.ecnu.timeline.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

/**
 * TimeLineFrame Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>01/08/2019</pre>
 */
public class TimeLineFrameTest {
    private TimeLineFrame timeLineFrame;
    private String username = "wy";
    private int userId = 18;
    @Before
    public void before() throws Exception {
        timeLineFrame = new TimeLineFrame(username,userId);
    }

    @After
    public void after() throws Exception {
        timeLineFrame.dispose();
    }


    private int countScroll(){
        int count = 0;
        Component[] components = timeLineFrame.getContentPane().getComponents();

        for(Component co:components) {
            if (co.getName() != "") {
                String a = co.getClass().getName();
                if (a.equals(JScrollPane.class.getName())) {
                    count++;
                }
            }
        }
        return count;
    }

    public void testGetCommentList() throws Exception{
        timeLineFrame.initData();
        assertEquals(10,timeLineFrame.getCommentList().size());
    }

    /**
     * Method: loadData()
     */
    @Test
    public void testLoadData() throws Exception {
        try {
            timeLineFrame.initData();
            Method method = timeLineFrame.getClass().getDeclaredMethod("loadData");
            method.setAccessible(true);
            method.invoke(timeLineFrame,null);
            sleep(1000);
            assertEquals(2,countScroll());


        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
    }

    /**
     * Method: initData()
     */
    @Test
    public void testInitData() throws Exception {
        timeLineFrame.initData();
        sleep(1000);
        assertEquals(1,countScroll());
    }

    /**
     * Method: refresh()
     */
    @Test
    public void testRefresh() throws Exception {
        try {

            Method method = timeLineFrame.getClass().getDeclaredMethod("refresh");
            method.setAccessible(true);
            method.invoke(timeLineFrame,null);
            sleep(1000);
            assertEquals(1,countScroll());


        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
    }

    /**
     * Method: more()
     */
    @Test
    public void testMore() throws Exception {
        try {

            timeLineFrame.initData();
            Method method = timeLineFrame.getClass().getDeclaredMethod("more");
            method.setAccessible(true);
            method.invoke(timeLineFrame,null);
            sleep(1000);
            assertEquals(20,timeLineFrame.getCommentList().size());


        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
    }

    /**
     * Method: removeContent()
     */
    @Test
    public void testRemoveContent() throws Exception {
        try {


            Method method = timeLineFrame.getClass().getDeclaredMethod("removeContent");
            method.setAccessible(true);
            method.invoke(timeLineFrame,null);
            assertEquals(0,countScroll());


        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
/*
try {
   Method method = TimeLineFrame.getClass().getMethod("removeContent");
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }

    /**
     * Method: showAddCommentDialog()
     */
    @Test
    public void testShowAddCommentDialog() throws Exception {
        try {

            Method method = timeLineFrame.getClass().getDeclaredMethod("showAddCommentDialog");
            method.setAccessible(true);
            method.invoke(timeLineFrame,null);

            assertNotNull(timeLineFrame.dialog);


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
/*
try {
   Method method = TimeLineFrame.getClass().getMethod("showAddCommentDialog");
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }


} 


