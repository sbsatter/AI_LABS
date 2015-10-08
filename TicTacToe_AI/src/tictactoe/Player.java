package tictactoe;

import java.util.ArrayList;

public enum Player{
	player1,player2;
//	public static ArrayList<String> player1_moves= new ArrayList<String>();
//	public static ArrayList<String> player2_moves= new ArrayList<String>();
	public static final int COMPUTER=2;
	public static final int HUMAN=1;
	static Player player;
	static int player_id;
	public static int getPlayer_id() {
		return player_id;
	}
	public static Player getPlayer() {
		return player;
	}
	static void setPlayer(Player plyr){
		player=plyr;
		player_id= (plyr.equals(player1))?Player.HUMAN:Player.COMPUTER;
	}
	public static void nextPlayer(){
		switch(player){
		case player1:
			player=player2;
			player_id=Player.COMPUTER;
//			Gameplay.playComputerMove();
			break;
		case player2:
			player=player1;
			player_id=Player.HUMAN;
			break;
		default:
			player=player1;
			player_id=Player.HUMAN;
			break;
		}
		System.out.println("Player changed to Player "+getPlayer_id() );
	}
	
}
