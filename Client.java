package com.mygdx.game;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import com.mygdx.game.component.PositionComponent;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.entity.Player;
import com.mygdx.game.system.RenderSystem;
import com.mygdx.game.system.SystemsManager;

public class Client extends Thread{
	
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	private AssetsManager am;
	public Client(AssetsManager am) {
		this.am=am;
	}

	public void run() {
		
		
		try {
			socket = new Socket("localhost", 5000);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			
			
			sendJoinServerMessage();

			while (true) {
	
				sleep(50);
				sendPosition();
				updatePlayer();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} /*
			 * catch (InterruptedException e) { e.printStackTrace(); }
			 */
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendJoinServerMessage() throws IOException {
		
	
		JSONObject jo = new JSONObject();
		jo.put("Message type", "New player joined!");
		jo.put("ID",Board.p.ID);
		jo.put("x", (float) Board.p.getComponent(PositionComponent.class).position.x);
		jo.put("y", (float) Board.p.getComponent(PositionComponent.class).position.y);
		dos.writeUTF(jo.toString());
		
	}

	@SuppressWarnings("unchecked")
	private void sendPosition() throws  IOException {
		
		JSONObject jo = new JSONObject();
		jo.put("Message type", "Update position");
		jo.put("ID", Board.p.ID);	
		jo.put("x", Board.p.getComponent(PositionComponent.class).position.x);
		jo.put("y", Board.p.getComponent(PositionComponent.class).position.y);
		dos.writeUTF(jo.toString());
	}

	private void updatePlayer() throws IOException{

		if(dis.available()!=0) {
			String msg = dis.readUTF();
			
			if (msg != null) {
				
				JSONObject obj = (JSONObject) JSONValue.parse(msg);
				String type = (String) obj.get("Message type");
				
				if(type.equals("New player joined!")) {	
	
					String id=(String) obj.get("ID");	
					
					if(!EntityManager.entities.containsKey(id)) {
						Player newPlayer = new Player(am);

						newPlayer.getComponent(PositionComponent.class).position.x= ((Double) obj.get("x")).floatValue();
						newPlayer.getComponent(PositionComponent.class).position.y=((Double) obj.get("y")).floatValue();;
						
						EntityManager.entities.put(id, newPlayer);
						RenderSystem ren= (RenderSystem) SystemsManager.systems.get(6);
						ren.addEntityy(EntityManager.getEntity(id));
					}
					
					
					
					if(Server.clientHandlerThreads.size()<2) {
						sendJoinServerMessage();
					}
					
					return;
				}
				else if(type.equals("Update position")) {

					String id =(String) obj.get("ID");
					if(EntityManager.getEntity(id) != null) {
						PositionComponent pc= EntityManager.getEntity(id).getComponent(PositionComponent.class);
						pc.position.x= ((Double) obj.get("x")).floatValue();
						pc.position.y= ((Double) obj.get("y")).floatValue();
					}
					
				}
			}
		}
	}
}
