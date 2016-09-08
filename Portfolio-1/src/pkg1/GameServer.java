package pkg1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
	private ServerSocket serverSocket;
	
	public GameServer(){
		serverSocket = null;
		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void start(){
		while(true){
			Socket socket1;
			Socket socket2;
			try {
				System.out.println("Waiting on player 1 to connect");
				socket1 = serverSocket.accept();
				System.out.println("Waiting on player 2 to connect");
				socket2 = serverSocket.accept();
				GameThread game = new GameThread(socket1, socket2);
				System.out.println("Launching thread");
				new Thread(game).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args){
		GameServer gs = new GameServer();
		gs.start();
	}
	
}