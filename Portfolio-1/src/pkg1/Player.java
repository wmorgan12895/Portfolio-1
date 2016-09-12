package pkg1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import pkg1.Game.state;

public class Player {
	
	private Socket sock;
	private boolean xPlayer;
	private ObjectInputStream inStr;
	private ObjectOutputStream outStr;
	private Game game;
	
	public Player(Socket s, boolean xPlayer){
		sock = s;
		this.xPlayer = xPlayer;
		try {
			outStr = new ObjectOutputStream(sock.getOutputStream());
			outStr.flush();
			inStr = new ObjectInputStream(sock.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Socket getSock() {
		return sock;
	}
	public void setGame(Game g){
		game = g;
	}
	public boolean isxPlayer() {
		return xPlayer;
	}
	public Game.state[] getBoard(){
		Game.state[] board = game.getBoard();
		if(xPlayer && board[9] == state.ex_turn){
			board[9] = state.your_turn;
		} 
		else if(!xPlayer && board[9] == state.oh_turn){
			board[9] = state.your_turn;
		}
		else{
			board[9] = state.not_turn;
		}
		return board;
	}
	public ObjectInputStream getInStr() {
		return inStr;
	}

	public ObjectOutputStream getOutStr() {
		return outStr;
	}
	
	
}
