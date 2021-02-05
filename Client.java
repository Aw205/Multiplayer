package com.mygdx.game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;

public class Client extends Thread{
	
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public Client() {

	}

	public void run() {

		try {
			socket = new Socket("localhost", 5000);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());

			while (true) {
				sleep(200);
				JSONObject jo = new JSONObject();
				sendPosition(jo);
				updatePlayer(jo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sendPosition(JSONObject jo) throws JSONException, IOException {
		jo.put("ID", Board.p.id);
		jo.put("x", Board.p.position.x);
		jo.put("y", Board.p.position.y);
		dos.writeUTF(jo.toString());
	}

	private void updatePlayer(JSONObject jo) throws JSONException, IOException {
		String msg = dis.readUTF();
		if (msg != null) {
			jo = new JSONObject(msg);
			if(Board.players.get(jo.get("ID")) == null){
				Board.players.put(jo.getString("ID"), new Player());
			}
			Board.players.get(jo.get("ID")).position.x = (int) jo.get("x");
			Board.players.get(jo.get("ID")).position.y = (int) jo.get("y");
		}
	}
}
