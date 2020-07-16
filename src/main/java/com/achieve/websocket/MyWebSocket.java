package com.achieve.websocket;

import com.achieve.domain.SocketMsg;
import com.achieve.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Auther: zj
 * @Date: 2018/8/16 17:55
 * @Description: websocket的具体实现类
 * 使用springboot的唯一区别是要@Component声明下，而使用独立容器是由容器自己管理websocket的，
 * 但在springboot中连容器都是spring管理的。
虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，
所以可以用一个静态set保存起来。
 */
@ServerEndpoint(value = "/websocket",configurator= GetHttpSessionConfigurator.class)
@Component
public class MyWebSocket {

    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private  Session session;

    private User user;
    //用来记录sessionId和该session进行绑定
    private static Map<String,Session> map = new HashMap<String, Session>();
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        System.out.println(httpSession.toString());
   //     this.user = (User) httpSession.getAttribute("currentUser");
        String userName = "路人甲";
      //  userName = user.getUserName();
        map.put(session.getId(),session); //加入map中
        webSocketSet.add(this);     //加入set中
        System.out.println("有新连接加入！当前在线人数为" + webSocketSet.size());
        this.session.getAsyncRemote().sendText("恭喜"+userName+"成功连接上WebSocket(其频道号为："+session.getId()+"-->当前在线人数为："+webSocketSet.size());
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        ObjectMapper objectMapper = new ObjectMapper();
        SocketMsg socketMsg;

        try {
            socketMsg = objectMapper.readValue(message,SocketMsg.class);
            if(socketMsg.getType() == 1){
                socketMsg.setFromUser(session.getId());
                Session fromSession = map.get(socketMsg.getFromUser());
                Session toSession = map.get(socketMsg.getToUser());

                if(toSession != null){
                    fromSession.getAsyncRemote().sendText(socketMsg.getMsg());
                    toSession.getAsyncRemote().sendText(socketMsg.getMsg());
                }else{
                    fromSession.getAsyncRemote().sendText("对方不在线或者您输入的频道号不对");
                }
            }else{
                broadcast(socketMsg.getMsg());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
    /**
     * 发生错误时调用
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    /**
     * 群发自定义消息
     * */
    public  void broadcast(String message){
        for (MyWebSocket item : webSocketSet) {
            //同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
            //this.session.getBasicRemote().sendText(message);
            item.session.getAsyncRemote().sendText(message);//异步发送消息.
        }
    }



}
