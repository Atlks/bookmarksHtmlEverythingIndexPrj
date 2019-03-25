import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
@ClientEndpoint
public class websocketClient {

	public static void main(String[] args) throws DeploymentException, IOException {
		String uri = "ws://127.0.0.1:17190/656f714e-cdaf-4ad6-a87c-d405eeb01b46";
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();

		URI wsUrl = URI.create(uri);
		Session session = container.connectToServer(websocketClient.class, wsUrl);
		session.getBasicRemote().sendText("javaclient");
		System.out.println("--0");

	}

}
