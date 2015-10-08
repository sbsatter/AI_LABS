package board;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tictactoe.*;

public class Game extends JFrame {
	public static JButton [][] button= new JButton [3][3];
	static Container container;
	final static Font font= new Font("Monospaced",Font.BOLD,40);
	public Game(){
		init();
	}

	private void init() {
		// TODO Auto-generated method stub

		setTitle("Tic Tac Toe");
		setSize(500, 400);
		
		addWindowListener(new board.WindowListeners());
		container= getContentPane();
		container.setLayout(new GridLayout(3,3,5,5));
		container.setBackground(new Color(0));
		container.setBounds(0, 0, 400, 400);
		initializeBoard();
//		pack();
		setVisible(true);
	}

	private void initializeBoard() {
		// TODO Auto-generated method stub
		int x=0;
		int y=0;
		for(x=0; x<3; x++){
			for(y=0; y<3; y++){
//				JTextField text=new JTextField(x+", "+y);
//				boolean displayCoordinate=false;
//				text.setOpaque(false);
//				text.setVisible(displayCoordinate);
//				text.setLocation(10,10);
				boolean isDisabled=false;
				String text="";
				switch(Gameplay.tictactoe[x][y]){
				case 1:
					text="X";
					isDisabled=true;
					break;
				case 2:
					isDisabled=true;
					text="O";
					break;
					
				}
				
				button[x][y]= new JButton();
				button[x][y].setEnabled(!isDisabled);
				button[x][y].setSize(new Dimension(100,100));
				button[x][y].setFont(font);
				button[x][y].setText(text);
				button[x][y].addActionListener(new MouseClickActionListener(x,y));
				container.add(button[x][y]);
			}
		}
//		repaint();
		
	}
	
	public static void setButtonAppearance(int x, int y, tictactoe.Player player){
		String label="";
		switch(player){
		case player1:
			label="X";
			break;
		case player2:
			label="O";
			break;
		default:
			label="";
			break;
		}
		button[x][y].setFont(font);
		button[x][y].setText(label);
		Gameplay.tictactoe[x][y]=Player.getPlayer_id();
		System.out.println("player "+Player.getPlayer_id()+" played "+x+","+y);
		
		
		if (button[x][y].isEnabled()) {
			button[x][y].setEnabled(false);
			button[x][y].setBackground(Color.DARK_GRAY);
		}else{
			button[x][y].setBackground(Color.YELLOW);
		}
		
		container.repaint();
//		String move=x+""+y;
//		if(Player.getPlayer().equals(Player.player1)){
//			Player.player1_moves.add(move);
//		}else{
//			Player.player2_moves.add(move);
//		}
		
		if (!isGameOver(x,y)) {
			tictactoe.Player.nextPlayer();
			if (Player.getPlayer_id() == Player.COMPUTER) {
//				if(Gameplay.moves_played==0 && Gameplay.tictactoe[1][1]==-1){
//					setButtonAppearance(1, 1, Player.getPlayer());
//				}else{
					Gameplay.playComputerMove();
//				}
				Gameplay.moves_played++;
			}
		}else{
			System.err.println("GAME OVER");
		}
	}

	private static boolean isGameOver(int x, int y) {
		// TODO Auto-generated method stub
//		System.out.println(Player.player1_moves);
		int [][] t= Gameplay.tictactoe;
		int id= Player.getPlayer_id();
		JFrame frame=new JFrame("congrats");
		frame.setSize(400, 100);;
		if (x==y || (x==0 && y==2) || (x==2 && y==0)){
			if(t[1][1]==id && ((t[0][2]==id && t[2][0]==id)|| (t[0][0]==id && t[2][2]==id ))){
				System.out.println("Player "+id+" has won!!");
				
				frame.add(new JLabel("Player "+id+" has won!!"));
//				frame.pack();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				System.exit(0);
				return true;
				
			}
		}
		int x1= (x+1)%3;
		int x2= (x+2)%3;
		int y1= (y+1)%3;
		int y2= (y+2)%3;
		if((t[x][y]==id && t[x][y1]==id && t[x][y2]==id)||(t[x][y]==id && t[x1][y]==id && t[x2][y]==id)){
			System.out.println("Player "+id+" has won!!");
//			frame=new JFrame("congrats");
			frame.add(new JLabel("Player "+id+" has won!!"));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frame.pack();
			frame.setVisible(true);
//			System.exit(0);
			return true;
		}
		for(int [] i_arr:t){
			for(int i: i_arr){
				if (i==-1){
					return false;
				}
			}
		}
		frame.add(new JLabel("It's a tie!!"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack();
		frame.setVisible(true);
		return true;
	}
	

}
