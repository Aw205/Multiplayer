package com.mygdx.game;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Server {

	public static List<ClientHandler> clientHandlerThreads = new ArrayList<ClientHandler>();
	public static Set<String> playerIDs = new HashSet<String>();

	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(5000);
		DataOutputStream dos;
		DataInputStream dis;
		 
		while (true) {
			Socket s = serverSocket.accept();
			dos= new DataOutputStream(s.getOutputStream());
			dis= new DataInputStream(s.getInputStream());
			Thread t = new ClientHandler(s, dis, dos);
			clientHandlerThreads.add((ClientHandler) t);
			t.start();
		}
	}
}
