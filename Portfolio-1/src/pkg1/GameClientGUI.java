package pkg1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameClientGUI extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JButton[] button;
	private JPanel gameBoard;
	private ClientThread ct;
	
	public GameClientGUI(ClientThread ct){
		this.ct = ct;
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 450);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(3,3));
		gameBoard.setBounds(0, 0, 433, 404);
		contentPane.add(gameBoard);
		button = new JButton[9];
		for(int i = 0; i<9; i++){
			button[i] = new JButton();
			gameBoard.add(button[i]);
			button[i].setEnabled(true);
			button[i].addActionListener(this);
		}
		
	}
	
	public static void main(String[] args){
		
		try {
			Socket serverSocket = new Socket("localhost", 4444);
			ClientThread ct = new ClientThread(serverSocket);
			GameClientGUI gc = new GameClientGUI(ct);
			gc.setVisible(true);
			Thread t = new Thread(ct);
			t.start();
			
		} catch (UnknownHostException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Client could not connect");
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
}


