package pkg1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class GameServer {
	private ServerSocket serverSocket;
	
	private ArrayList<Player> inQueue = new ArrayList<Player>();
	private ArrayList<Player> inGame = new ArrayList<Player>();
	public Player player1;
	public Player player2;
	
	public ArrayList<Player> getInQueue() {
		return inQueue;
	}

	public ArrayList<Player> getInGame() {
		return inGame;
	}

	public GameServer(){
		serverSocket = null;
		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void start(){
		int game_num = 0;
		while(true){
			Socket socket1;
			Socket socket2;
			try {
				System.out.println("Waiting on player 1 to connect");
				socket1 = serverSocket.accept();
				player1 = new Player(socket1);
				System.out.println("Waiting on player 2 to connect");
				socket2 = serverSocket.accept();
				player2 = new Player(socket2);
				inGame.add(player1);
				inGame.add(player2);
				game_num+=1;
				GameThread game = new GameThread(this, player1, player2, game_num);
				System.out.println("Launching thread");
				new Thread(game).start();
			} catch (IOException e) {
				System.out.println("server IO");
				e.printStackTrace();
			}
			
		}
	}
	

	
	public static void main(String[] args){
		GameServer gs = new GameServer();
		gs.start();
	}
	
}