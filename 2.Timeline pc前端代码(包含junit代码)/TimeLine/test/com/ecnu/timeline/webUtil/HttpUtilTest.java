package com.ecnu.timeline.webUtil;

import com.ecnu.timeline.model.Comment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

/**
 * HttpUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>01/07/2019</pre>
 */
public class HttpUtilTest {
    HttpUtil httpUtil;

    @Before
    public void before() throws Exception {
        httpUtil = HttpUtil.getHttpUtil();
    }

    @After
    public void after() throws Exception {
        httpUtil = null;
    }

    /**
     * Method: getHttpUtil()
     */
    @Test
    public void testGetHttpUtil() throws Exception {
        httpUtil = HttpUtil.getHttpUtil();
        assertNotNull(httpUtil);
    }

    /**
     * Method: getNextTenComments(int lastId)
     */
    @Test
    public void testGetNextTenComments() throws Exception {

        List<Comment> preComments = httpUtil.getNewestTenComments();
        int lastId = preComments.get(preComments.size() - 1).getId();
        List<Comment> nextComments = httpUtil.getNextTenComments(lastId);
        int nextId = nextComments.get(0).getId();
        assertTrue(lastId - nextId > 0);

        List<Comment> nextComments1 = httpUtil.getNextTenComments(-190);
        assertTrue(nextComments1.size() == 0);
    }

    /**
     * Method: getNewestTenComments()
     */
    @Test
    public void testGetNewestTenComments() throws Exception {

        List<Comment> comments = httpUtil.getNewestTenComments();
        assertEquals(10, comments.size());

    }

    /**
     * Method: addNewComment(Comment comment)
     */
    @Test
    public void testAddNewComment() throws Exception {
        int userId = httpUtil.login("wy", "123");
        assertTrue(userId > 0);
        List<Comment> comments = httpUtil.getNewestTenComments();
        Comment comment = new Comment(0, "nishitong",/*"n this small example, " +*/
//            "this technique gives us only a rather small gain in convenience for the" +
            /*" proofs we've seen; however, using reflect consistently often leads to noticeably" +
            " shorter and clearer scripts as proofs get larger. We'll see many more examples in" +*/
                "laterchaptersandinProgrammingLanguageFoundations.", "", new Date().toString());
        int result = httpUtil.addNewComment(userId, comment);
        sleep(1000);
        assertTrue(result != 0);

        int result1 = httpUtil.addNewComment(-1, comment);
        sleep(1000);
        assertTrue(result1 == 0);

    }

    /**
     * Method: login(String userName, String password)
     */
    @Test
    public void testLogin() throws Exception {

        int userId = httpUtil.login("nishitong11", "123");
        assertTrue(userId > 0);
        int userId1 = httpUtil.login("nishitong11", "1234");
        assertTrue(userId1 == 0);
    }

    /**
     * Method: register(String userName, String password)
     */
    @Test
    public void testRegister() throws Exception {
        int userId = httpUtil.register("nishitong112345", "123");
        assertTrue(userId > 0);
    /*int userId1 = httpUtil.register("","123");
    assertTrue(userId1 == 0);*/
    }

    /**
     * Method: doPost(String httpUrl, String param)
     */
    @Test
    public void testDoPost() throws Exception {

    }


    /**
     * Method: getHttpResponse(String allConfigUrl)
     */
    @Test
    public void testGetHttpResponse() throws Exception {

/* 
try { 
   Method method = HttpUtil.getClass().getMethod("getHttpResponse", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
