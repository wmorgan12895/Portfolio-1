package pkg1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuGUI extends JFrame implements ActionListener {
	
	private JPanel contentPane;
	private JButton playGame;
	private JButton spectate;
	private ClientThread ct;
	
	public MenuGUI(ClientThread ct){
		this.ct = ct;
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 500);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		playGame = new JButton();
		playGame.setText("Play Game");
		contentPane.add(playGame, BorderLayout.WEST);
		playGame.setEnabled(true);
		playGame.addActionListener(this);
		
		spectate = new JButton();
		spectate.setText("Spectate");
		contentPane.add(spectate, BorderLayout.EAST);
		spectate.setEnabled(true);
		spectate.addActionListener(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == playGame){
			GameClientGUI gc = new GameClientGUI(ct);
			gc.setVisible(true);
			Thread t = new Thread(ct);
			t.start();
			
			Listener listener = new Listener(gc, ct);
			Thread lt = new Thread(listener);
			lt.start();
			this.setVisible(false);
			
		} 
		
		else if(e.getSource() == spectate){
			
		}
	}

}
