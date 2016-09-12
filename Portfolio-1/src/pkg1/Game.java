package pkg1;

import java.io.Serializable;

public class Game implements Serializable{

	protected enum state { empty, ex, oh, ex_turn, oh_turn, your_turn, not_turn }
	private state[] board;
	private int moves;
	private String[] messages = {"Your turn" , "You're opponent's turn" , "You've won!" , "You've lost!"};
	
	public Game() {
		board = new state[10];
		clearBoard();
	}
	
	private void clearBoard() {
		for (int i = 0; i < board.length; i++) {
			board[i] = state.empty;
		}
		board[9] = state.ex_turn;
		moves = 0;
	}
	
	//makes a move in space for player.
	public void move(int space, int player) {
		if (player == 1) {
			board[space] = state.ex;
			board[9] = state.oh_turn;
		}
		else if (player == 2) {
			board[space] = state.oh;
			board[9] = state.ex_turn;
		}
		moves++;
	}
	
	public state[] getBoard() {
		state[] states = new state[10];
		for (int i = 0; i < 10; i++) {
			states[i] = board[i];
		}
		return states;
	}
	
	public state getState(int i) {
		return board[i];
	}
	
	//determines if there is a victor and returns the number of the victorious player. Returns 0 if no victor, 3 if draw.
	public int victor() {
		if (board[0] == state.ex && board[1] == state.ex && board[2] == state.ex) {
			return 1;
		}
		else if (board[3] == state.ex && board[4] == state.ex && board[5] == state.ex) {
			return 1;
		}
		else if (board[6] == state.ex && board[7] == state.ex && board[8] == state.ex) {
			return 1;
		}
		else if (board[0] == state.ex && board[3] == state.ex && board[6] == state.ex) {
			return 1;
		}
		else if (board[1] == state.ex && board[4] == state.ex && board[7] == state.ex) {
			return 1;
		}
		else if (board[2] == state.ex && board[5] == state.ex && board[8] == state.ex) {
			return 1;
		}
		else if (board[0] == state.ex && board[4] == state.ex && board[8] == state.ex) {
			return 1;
		}
		else if (board[2] == state.ex && board[4] == state.ex && board[6] == state.ex) {
			return 1;
		}
		
		else if (board[0] == state.oh && board[1] == state.oh && board[2] == state.oh) {
			return 2;
		}
		else if (board[3] == state.oh && board[4] == state.oh && board[5] == state.oh) {
			return 2;
		}
		else if (board[6] == state.oh && board[7] == state.oh && board[8] == state.oh) {
			return 2;
		}
		else if (board[0] == state.oh && board[3] == state.oh && board[6] == state.oh) {
			return 2;
		}
		else if (board[1] == state.oh && board[4] == state.oh && board[7] == state.oh) {
			return 2;
		}
		else if (board[2] == state.oh && board[5] == state.oh && board[8] == state.oh) {
			return 2;
		}
		else if (board[0] == state.oh && board[4] == state.oh && board[8] == state.oh) {
			return 2;
		}
		else if (board[2] == state.oh && board[4] == state.oh && board[6] == state.oh) {
			return 2;
		}
		
		else if (moves >= 9) {
			return 3;
		}
		
		else {
			return 0;
		}
	}
}
