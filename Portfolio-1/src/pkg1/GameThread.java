package pkg1;

import java.io.IOException;
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
		int move1 = 0;
		int move2 = 0;
		for(int i = 0; i < 9; i++){
			try {
				move1 = (int)player1.getInStr().readObject();
				System.out.println("X player moves " + move1);
				move2 = (int)player2.getInStr().readObject();
				System.out.println("O player moves " + move2);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
