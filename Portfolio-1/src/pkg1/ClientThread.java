package pkg1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread implements Runnable{
	private ObjectOutputStream outStr;
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
			synchronized(this) {
					this.wait();
					outStr.writeObject(move);
			}
		} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		} catch (IOException e) {
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
	
	public Listener(GameClientGUI gui, ClientThread ct) {
		try {
			this.gui = gui;
			this.socket = ct.getSocket();
			this.outStr = ct.getOutStream();
			inStr = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true){
			//read board
			Game.state[] game = null;
			try {
				synchronized(this) {
				//reset outputstream
				game = (Game.state[]) inStr.readObject();
				outStr.reset();
				this.notify();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//use given board to set title and disable buttons
			gui.update(game);
			
		}
	}
}