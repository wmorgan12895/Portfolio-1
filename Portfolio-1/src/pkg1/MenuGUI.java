package pkg1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuGUI extends JFrame implements ActionListener {
	
	private JPanel contentPane;
	private JButton playGame;
	private JButton quit;
	private GameClientGUI oldGame;
	
	public MenuGUI(GameClientGUI oldGame){
		this.oldGame = oldGame;
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 500);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		playGame = new JButton();
		playGame.setText("Play again?");
		contentPane.add(playGame, BorderLayout.CENTER);
		playGame.setEnabled(true);
		playGame.addActionListener(this);
		
//		quit = new JButton();
//		quit.setText("Quit");
//		contentPane.add(quit, BorderLayout.EAST);
//		quit.setEnabled(true);
//		quit.addActionListener(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == playGame){
			Socket serverSocket;
			oldGame.setVisible(false);
			try {
				serverSocket = new Socket("localhost", 4444);
				ClientThread ct = new ClientThread(serverSocket);
				GameClientGUI gc = new GameClientGUI(ct);
				gc.setVisible(true);
				Thread t = new Thread(ct);
				t.start();
				Listener listener = new Listener(gc, ct);
				Thread lt = new Thread(listener);
				lt.start();
				this.setVisible(false);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
		} 
		
//		else if(e.getSource() == quit){
//			oldGame.setVisible(false);
//			try {
//				oldGame.ct.outStr.writeObject(oldGame.states);
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			oldGame.ct.run = false;
//			this.setVisible(false);
//		}
	}

}
