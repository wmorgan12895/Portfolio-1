package pkg1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Player {
	
	private Socket sock;
	private boolean xPlayer;
	private ObjectInputStream inStr;
	private ObjectOutputStream outStr;
	
	public Player(Socket s, boolean xPlayer){
		sock = s;
		this.xPlayer = xPlayer;
		try {
			inStr = new ObjectInputStream(sock.getInputStream());
			outStr = new ObjectOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Socket getSock() {
		return sock;
	}

	public boolean isxPlayer() {
		return xPlayer;
	}

	public ObjectInputStream getInStr() {
		return inStr;
	}

	public ObjectOutputStream getOutStr() {
		return outStr;
	}
	
	
}
