package tictactoe;

import board.Game;

public class Gameplay {
	public static int moves_played=0;
	public static int [][] tictactoe=new int [3][3];
//	public static int [][] tictactoe={{-1,2,-1},{-1,-1,1},{2,-1,1}};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Game starts");
		initGameplay();
		new board.Game();
	}

	private static void initGameplay() {
		// TODO Auto-generated method stub
		for(int x=0; x<3; x++){
			for(int y=0; y<3; y++){
				tictactoe[x][y]=-1;
			}
		}
		Player.setPlayer(Player.player1);
	}

	public static void playComputerMove() {
		// TODO Auto-generated method stub
		int [][]ttt= new int[3][3];
		for(int a=0; a<3; a++){
			for(int b=0; b<3; b++){
				ttt[a][b]=tictactoe[a][b];
			}
		}
		if(Player.getPlayer_id()==Player.COMPUTER){
			System.out.println("playComputerMove()");
			Minimax look= new Minimax(ttt, 0, Player.HUMAN);
			DecodeEncode de= new DecodeEncode(look.lookAhead());
			int x= de.getX();
			int y= de.getY();
//			tictactoe[x][y]=Player.COMPUTER;
			Player.setPlayer(Player.player2);
			System.err.println("THE HIGHEST POSSIBLE SCORE: "+de.getScore()+"\nx: "+x+"\ty: "+y);
			Game.setButtonAppearance(x, y, Player.getPlayer());
		}
	}

}
