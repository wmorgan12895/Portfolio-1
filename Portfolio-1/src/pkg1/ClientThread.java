package pkg1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread implements Runnable{
	private ObjectInputStream inStr;
	private ObjectOutputStream outStr;
	private Socket sock;
	
	public ClientThread(Socket s){
		sock = s;
		try {
			outStr = new ObjectOutputStream(s.getOutputStream());
			outStr.flush();
			inStr = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void send(int move){
		try {
			outStr.writeObject(move);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
	}
}
