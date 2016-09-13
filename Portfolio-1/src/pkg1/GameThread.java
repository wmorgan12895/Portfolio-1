package pkg1;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class GameThread implements Runnable {
	Player player1;
	Player player2;
	Game game;
	int game_num;
	GameServer server;
	boolean kill;
	public GameThread(GameServer server, Player player1, Player player2, int game_num){
		this.player1 = player1;
		this.player2 = player2;
		makePlayers();
		kill = false;
		this.server = server;
		this.game_num = game_num;
		game = new Game();
	}
	
	private void makePlayers(){
		Random rand = new Random();
		int pick = rand.nextInt(1);
		if(pick == 0){
			player1.setXplay(true);
			player2.setXplay(false);
		}
		else{
			player1.setXplay(false);
			player2.setXplay(true);
		}
		
	}
	
	
	@Override
	public void run(){
		player1.setGame(game);
		player2.setGame(game);
		try {
			player1.getOutStr().writeObject(game_num);
			player2.getOutStr().writeObject(game_num);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		int move1 = 0;
		int move2 = 0;
		int victor = 0;
		boolean gameOver = false;
		while (!gameOver && !kill) {
			try {
				player1.getOutStr().writeObject(player1.getBoard());
				player2.getOutStr().writeObject(player2.getBoard());
				move1 = (int)player1.getInStr().readObject();
				game.move(move1, 1);
				System.out.println("X player moves " + move1);
				victor = game.victor();
				player1.getOutStr().writeObject(player1.getBoard());
				player2.getOutStr().writeObject(player2.getBoard());
				
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
				victor = game.victor();
				player1.getOutStr().writeObject(player1.getBoard());
				player2.getOutStr().writeObject(player2.getBoard());
				
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
				kill = true;
				handleQuit();
				System.out.println("Player Quit Ending Thread");
				e.printStackTrace();
			}
		}
		if(!kill){
			handleQuit();
		}
	}
	
	private void handleQuit(){
		try {
			player1.getOutStr().writeObject(null);
		} catch (IOException e) {
		}
		try {
			player2.getOutStr().writeObject(null);
		} catch (IOException e) {
		}
	}
}
