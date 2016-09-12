package pkg1;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class GameThread implements Runnable {
	Player player1;
	Player player2;
	Game game;
	
	public GameThread(Socket socket1, Socket socket2){
		makePlayers(socket1, socket2);
		game = new Game();
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
		player1.setGame(game);
		player2.setGame(game);
		int move1 = 0;
		int move2 = 0;
		int victor = 0;
		boolean gameOver = false;
		while (!gameOver) {
			try {
				 
				player1.getOutStr().writeObject(player1.getBoard());
				player2.getOutStr().writeObject(player2.getBoard());
				move1 = (int)player1.getInStr().readObject();
				game.move(move1, 1);
				System.out.println("X player moves " + move1);
				player1.getOutStr().writeObject(player1.getBoard());
				player2.getOutStr().writeObject(player2.getBoard());
				victor = game.victor();
				if (victor == 1) {
					gameOver = true;
					System.out.println("X player wins!");
					break;
				}
				if (victor == 3) {
					gameOver = true;
					System.out.println("Draw!");
					break;
				}
				move2 = (int)player2.getInStr().readObject();
				game.move(move2, 2);
				System.out.println("O player moves " + move2);
				player1.getOutStr().writeObject(player1.getBoard());
				player2.getOutStr().writeObject(player2.getBoard());
				victor = game.victor();
				if (victor == 2) {
					gameOver = true;
					System.out.println("O player wins!");
				}
				if (victor == 3) {
					gameOver = true;
					System.out.println("Draw!");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
