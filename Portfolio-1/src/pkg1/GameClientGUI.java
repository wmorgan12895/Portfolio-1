package pkg1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameClientGUI extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JButton[] button;
	private JPanel gameBoard;
	private ClientThread ct;
	public Game.state turn;
	private JTextArea text;
	private String num;
	private JButton quit;
	
	public GameClientGUI(ClientThread ct){
		this.ct = ct;
		num = "";
		setTitle("Tic Tac Toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 475);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(3,3));
		gameBoard.setBounds(0, 0, 433, 404);
		contentPane.add(gameBoard);
		button = new JButton[9];
		for(int i = 0; i < 9; i++){
			button[i] = new JButton();
			gameBoard.add(button[i]);
			button[i].setEnabled(true);
			button[i].addActionListener(this);
		}
		
		text = new JTextArea(5,10);
		text.setText("Please wait for your opponent");
		contentPane.add(text, BorderLayout.NORTH);
		
		quit = new JButton();
		quit.setText("Quit");
		contentPane.add(quit, BorderLayout.SOUTH);
		quit.setEnabled(true);
		quit.addActionListener(this);
		
		
	}
	
	public static void main(String[] args){
		
			Socket serverSocket;
			try {
				serverSocket = new Socket("localhost", 4444);
				ClientThread ct = new ClientThread(serverSocket);
				MenuGUI menu = new MenuGUI(ct);
				menu.setVisible(true);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button[0]){
			ct.send(0);
		}
		else if(e.getSource() == button[1]){
			ct.send(1);
		}
		else if(e.getSource() == button[2]){
			ct.send(2);
		}
		else if(e.getSource() == button[3]){
			ct.send(3);
		}
		else if(e.getSource() == button[4]){
			ct.send(4);
		}
		else if(e.getSource() == button[5]){
			ct.send(5);
		}
		else if(e.getSource() == button[6]){
			ct.send(6);
		}
		else if(e.getSource() == button[7]){
			ct.send(7);
		}
		else if(e.getSource() == button[8]){
			ct.send(8);
		}
		else{
			System.out.println("working?");
		}
	}
	
	public void setNum(int game_num){
		num = "Game number: " + game_num + "\n";
	}
	
	public void update(Game.state[] states) {
		if(states != null){
			for (int i = 0; i < 9; i++) {
				Game.state state = states[i];
				if (state == Game.state.ex) {
					button[i].setEnabled(false);
					button[i].setText("X");
				}
				else if (state == Game.state.oh) {
					button[i].setEnabled(false);
					button[i].setText("O");
				}
			}
			turn = states[9];
			disable();
		}
		else{
			for (int i = 0; i < 9; i++) {
			button[i].setEnabled(true);
			button[i].setText("");	
			}
		}
	}
	
	
	public void disable(){
		
		if(turn == Game.state.not_turn){
			for(int i = 0; i<9; i++){
				button[i].setEnabled(false);
				text.setText(num + "Please wait for your opponent");
			}
		} 
		else if(turn == Game.state.your_turn){
			for(int i = 0; i<9; i++){
				if(button[i].getText().equals("")){
					button[i].setEnabled(true);
				}
				text.setText(num + "Your turn");
			}
		}
		else if(turn == Game.state.you_win){
			text.setText(num + "You Win!");
		}
		else if(turn == Game.state.you_lose){
			text.setText(num + "You Lose!");
		}
		else if(turn == Game.state.draw){
			text.setText(num + "Cats Game!");
		}
	}
}


