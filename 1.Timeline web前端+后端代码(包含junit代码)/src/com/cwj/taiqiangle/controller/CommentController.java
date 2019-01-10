package com.cwj.taiqiangle.controller;

import com.cwj.taiqiangle.model.JsonMsg;
import com.cwj.taiqiangle.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * Created by 蛟川小盆友 on 2018/12/18.
 */
@Controller
@RequestMapping(value="/comment")
public class CommentController {
    CommentService commentService=new CommentService();

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg msgTraversal()
    {
        JsonMsg jsonMsg=new JsonMsg();
        try {
            jsonMsg.setCode("200");
            jsonMsg.setData(commentService.getCommentAll());
        } catch (SQLException e) {
            jsonMsg.setData(-1);
            jsonMsg.setCode("404");
            e.printStackTrace();
        }
        return jsonMsg;
    }

    @RequestMapping(value = "/getmore", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getmore(int nowPos)
    {
        JsonMsg jsonMsg=new JsonMsg();
        try {
            jsonMsg.setCode("200");
            jsonMsg.setData(commentService.getMoreComment(nowPos));
        } catch (SQLException e) {
            jsonMsg.setData(-1);
            jsonMsg.setCode("404");
            e.printStackTrace();
        }
        return jsonMsg;
    }

    @RequestMapping(value = "/addcomment", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg addComment(int id,String content,String pic)
    {
        JsonMsg jsonMsg=new JsonMsg();
        try {
            jsonMsg.setCode("200");
            jsonMsg.setData(commentService.addComment(id,content,pic));
        } catch (SQLException e) {
            jsonMsg.setData(-1);
            jsonMsg.setCode("404");
            e.printStackTrace();
        }
        return jsonMsg;
    }
}
