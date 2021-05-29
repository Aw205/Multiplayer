package com.mygdx.game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
	
	private Socket s;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos){
		this.s=s;
		this.dis=dis;
		this.dos=dos;
	}
	
	public void run(){
		
		while (true) {
			
			try {
				 String msg = dis.readUTF();
				if(msg!=null) {
					broadcastMessage(msg);
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void broadcastMessage(String s) throws IOException{
		for(ClientHandler ch : Server.clientHandlerThreads){
			if(ch!=this){
				ch.dos.writeUTF(s);
			}	
		}
	}
}
