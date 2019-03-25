
import java.io.IOException;  
import java.util.Map;  
import java.util.concurrent.ConcurrentHashMap;  
import javax.websocket.*;  
import javax.websocket.server.PathParam;  
import javax.websocket.server.ServerEndpoint;  
  
  
@ServerEndpoint("/websocket/ser")  
public class websocketServer2 {  
  
    private static int onlineCount = 0;  
    private static Map<String, websocketServer2> clients = new ConcurrentHashMap<String, websocketServer2>();  
    private Session session;  
    private String username;  
      
    @OnOpen  
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {  
  
        this.username = username;  
        this.session = session;  
          
        addOnlineCount();  
        clients.put(username, this);  
        System.out.println("已连接");  
    }  
  
    @OnClose  
    public void onClose() throws IOException {  
        clients.remove(username);  
        subOnlineCount();  
    }  
  
    @OnMessage  
    public void onMessage(String message) throws IOException {  
  System.out.println("--on mesg");
//        JSONObject jsonTo = JSONObject.fromObject(message);  
//          
//        if (!jsonTo.get("To").equals("All")){  
//            sendMessageTo("给一个人", jsonTo.get("To").toString());  
//        }else{  
//            sendMessageAll("给所有人");  
//        }  
    }  
  
    @OnError  
    public void onError(Session session, Throwable error) {  
        error.printStackTrace();  
    }  
  
    public void sendMessageTo(String message, String To) throws IOException {  
        // session.getBasicRemote().sendText(message);  
        //session.getAsyncRemote().sendText(message);  
        for (websocketServer2 item : clients.values()) {  
            if (item.username.equals(To) )  
                item.session.getAsyncRemote().sendText(message);  
        }  
    }  
      
    public void sendMessageAll(String message) throws IOException {  
        for (websocketServer2 item : clients.values()) {  
            item.session.getAsyncRemote().sendText(message);  
        }  
    }  
      
      
  
    public static synchronized int getOnlineCount() {  
        return onlineCount;  
    }  
  
    public static synchronized void addOnlineCount() {  
        websocketServer2.onlineCount++;  
    }  
  
    public static synchronized void subOnlineCount() {  
        websocketServer2.onlineCount--;  
    }  
  
    public static synchronized Map<String, websocketServer2> getClients() {  
        return clients;  
    }  
}  