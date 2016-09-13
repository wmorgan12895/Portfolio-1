package pkg1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread implements Runnable{
	public ObjectOutputStream outStr;
	private Socket sock;
	
	public ClientThread(Socket s){
		sock = s;
		try {
			outStr = new ObjectOutputStream(s.getOutputStream());
			outStr.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Socket getSocket() {
		return sock;
	}
	
	public ObjectOutputStream getOutStream() {
		return outStr;
	}
	
	public void send(int move){
		try {
			outStr.writeObject(move);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
	}
}


class Listener implements Runnable {
	private ObjectInputStream inStr;
	private Socket socket;
	GameClientGUI gui;
	ObjectOutputStream outStr;
	ClientThread ct;
	
	public Listener(GameClientGUI gui, ClientThread ct) {
		try {
			this.ct = ct;
			this.gui = gui;
			this.socket = ct.getSocket();
			this.outStr = ct.getOutStream();
			inStr = new ObjectInputStream(socket.getInputStream());
			int game_num = (int) inStr.readObject();
			gui.setNum(game_num);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true){
			//read board
			Game.state[] game = null;
			try {
				game = (Game.state[]) inStr.readObject();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			//use given board to set title and disable buttons
			gui.update(game);
			
		}
	}
}