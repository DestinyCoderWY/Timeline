package com.ecnu.timeline.ui;

import com.ecnu.timeline.model.Comment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 * ContentBox Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>01/07/2019</pre>
 */
public class ContentBoxTest {
    private ContentBox contentBox;
    @Before
    public void before() throws Exception {
        contentBox = new ContentBox();
    }

    @After
    public void after() throws Exception {
        contentBox = null;
    }

    /**
     * Method: createContentBox(Comment comment)
     */
    @Test
    public void testCreateContentBox() throws Exception {
        Comment comment = new Comment(13, "nishitong", "hahahahahahah",
                "www.cctv.con", new Date().toString());
        Box box = ContentBox.createContentBox(comment);
        assertNotNull(box);
    }


} 
