package pkg1;

import java.net.Socket;
import java.util.Random;

public class GameThread implements Runnable {
	Player player1;
	Player player2;
	
	public GameThread(Socket socket1, Socket socket2){
		makePlayers(socket1, socket2);
	}
	
	private void makePlayers(Socket socket1, Socket socket2){
		Random rand = new Random();
		int pick = rand.nextInt(1);
		if(pick == 0){
			player1 = new Player(socket1, true);
			player2 = new Player(socket2, false);
		}
		else{
			player1 = new Player(socket2, true);
			player2 = new Player(socket1, false);
		}
		
	}
	
	@Override
	public void run(){
		
	}
	
}
