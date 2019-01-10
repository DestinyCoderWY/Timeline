package com.ecnu.timeline.webUtil;

import com.ecnu.timeline.model.Comment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javafx.scene.input.KeyCode.J;

public class HttpUtil {
    private static HttpUtil httpUtil;
    private static Gson gson = new Gson();
    private static String hostPort = "http://111.230.53.39:8080";

    private HttpUtil(){

    }

    public static HttpUtil getHttpUtil(){
        if(httpUtil == null){
            httpUtil = new HttpUtil();
        }
        return httpUtil;
    }

    public List<Comment> getNextTenComments(int lastId){
        String url = hostPort + "/CarManage_war/comment/getmore" + "?nowPos=" + lastId;
        String jsonString = getHttpResponse(url);
        JsonParser jsonParser = new JsonParser();
        JsonObject result = (JsonObject) jsonParser.parse(jsonString);


        if("200".equals(result.get("code").getAsString())){
            return gson.fromJson(result.get("data").toString(),new TypeToken<List<Comment>>(){}.getType());
        }

        return null;

    }

    public List<Comment> getNewestTenComments(){
        String url = hostPort + "/CarManage_war/comment/getall";
        String jsonString = getHttpResponse(url);
        JsonParser jsonParser = new JsonParser();
        JsonObject result = (JsonObject) jsonParser.parse(jsonString);


        if("200".equals(result.get("code").getAsString())){
            return gson.fromJson(result.get("data").toString(),new TypeToken<List<Comment>>(){}.getType());
        }

        return null;

    }

    public int addNewComment(int userId, Comment comment){
        String url = hostPort + "/CarManage_war/comment/addcomment" + "?id=" + userId + "&content=" + comment.getCommment()
                + "&pic=" + comment.getPicture();

        String jsonString = getHttpResponse(url);
        JsonParser jsonParser = new JsonParser();
        JsonObject result = (JsonObject) jsonParser.parse(jsonString);

        if("200".equals(result.get("code").getAsString())){

            return result.get("data").getAsInt();
        }
        return 0;

    }


    public int login(String userName, String password){

        String url = hostPort + "/CarManage_war/user1/userLogin" + "?username=" + userName + "&password=" + password;
        String jsonString = getHttpResponse(url);
        JsonParser jsonParser = new JsonParser();
        JsonObject result = (JsonObject) jsonParser.parse(jsonString);

        if("200".equals(result.get("code").getAsString())){

            return result.get("data").getAsInt();
        }
        return 0;

    }

    public int register(String userName, String password){

        String url = hostPort + "/CarManage_war/user1/userRegister" + "?username=" + userName + "&password=" + password;
        String jsonString = doPost(url,"");
        JsonParser jsonParser = new JsonParser();
        JsonObject result = (JsonObject) jsonParser.parse(jsonString);

        if("200".equals(result.get("code").getAsString())){
            return result.get("data").getAsInt();
        }
        return 0;

    }

    private static String getHttpResponse(String allConfigUrl) {
        BufferedReader in = null;
        StringBuffer result = null;
        try {

            URI uri = new URI(allConfigUrl);
            URL url = uri.toURL();
            URLConnection connection = url.openConnection();

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "utf-8");

            connection.connect();

            result = new StringBuffer();
            //读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return null;

    }

    private static String doPost(String httpUrl, String param) {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(60000);

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            os = connection.getOutputStream();
            os.write(param.getBytes());
            if (connection.getResponseCode() == 200) {

                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();
        }
        return result;
    }
}

