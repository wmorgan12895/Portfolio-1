package pkg1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameClientGUI extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JButton[] button;
	private JPanel gameBoard;
	
	public GameClientGUI(){
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
		GameClientGUI gc = new GameClientGUI();
		gc.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("working?");
	}
}


