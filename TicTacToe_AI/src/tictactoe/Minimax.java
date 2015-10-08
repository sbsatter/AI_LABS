package tictactoe;

public class Minimax {
	int [] [] currentState;
	int depth=0;
	int totalEmptyCells=0;
	int score [][]= new int [3][3];
	boolean[][] checkedBlankCells;
	boolean[][] blankCells;
	char [][] cells;
	int playerId;
	private int points=0;
	//	private int minScore=Integer.MAX_VALUE;
	private int retX;
	private int retY;
	private boolean isGameDrawn=false;
	//	public Minimax(int [][] tictactoe, int playerId){
	//		this.playerId=playerId;
	//		currentState=tictactoe;
	//		initMinimax();
	//	}
	public Minimax(int [][] tictactoe, int depth, int lastPlayerId){
		playerId=(lastPlayerId==Player.COMPUTER)?Player.HUMAN:Player.COMPUTER;
		currentState=tictactoe;
		this.depth=depth;
		initMinimax();

	}
	private void initMinimax(){
		blankCells=new boolean [3][3];
		checkedBlankCells= new boolean [3][3];
		cells= new char[3][3];
		for(int x=0; x<currentState[0].length; x++ ){
			for(int y=0; y<currentState.length; y++){
				if (currentState[x][y]==-1){
					totalEmptyCells++;
					blankCells[x][y]=true;
					//					score[x][y]=(playerId==Player.COMPUTER)?1000:-1000;
				}
			}
		}
		if (totalEmptyCells>0) {
			//			score = new int[3][3];
			//			lookAhead();
		}
	}
	public int lookAhead(){
		//		int i=0;
		for(int x=0; x<blankCells[0].length; x++){
			for(int y=0; y<blankCells.length; y++){
				if(blankCells[x][y]){

					int [][]temp= new int[3][3];
					for(int a=0; a<3; a++){//filling temp and cells matrices
						for(int b=0; b<3; b++){
							temp[a][b]= currentState[a][b];

							cells[a][b]= (currentState[a][b]==1)?'X':(currentState[a][b]==2)?'O':' ';
						}
					}

					temp[x][y]=playerId;
					
					cells[x][y]= (temp[x][y]==1)?'X':(temp[x][y]==2)?'O':' ';
//					checkedBlankCells[x][y]=true;
					System.out.println("TEMP: Turn of: "+playerId+"=>("+x+","+y+")\t\t |currentState\t\t\t\t");
					for(int a=0; a<3; a++){//print cells and current state
						for(int b=0; b<3; b++){//printing matrix
							//							if(temp[a][b]!=-1){
							if(x==a && y==b)//printing matrix
								System.out.print("=>");//printing matrix
							System.out.print(cells[a][b]+"\t");//printing matrix
							//							}//printing matrix
							//							else{
							//								System.out.print(" \t");
							//							}//printing matrix
						}//printing matrix
						System.out.print("\t |");//printing matrix
						for(int b=0; b<3; b++){//printing matrix
							if(currentState[a][b]!=-1)//printing matrix
								System.out.print(currentState[a][b]+"\t");//printing matrix
							else{//printing matrix
								System.out.print(" \t");//printing matrix
							}//printing matrix
						}//printing matrix
						System.out.println();//printing matrix
					}//printing matrix
					System.out.println("============================================================");
					if(isGameOver(x, y, temp)){
						score[x][y]=generateScore();
						System.out.println("Generated Score (game end) "+score[x][y]);
						DecodeEncode de= new DecodeEncode(x, y, score[x][y]);
						return de.getEncoded_number();
					}


					depth++;
					Minimax look= new Minimax(temp,depth, playerId);
					score[x][y]+= new DecodeEncode(look.lookAhead()).getScore();
					System.out.println("score["+x+"]["+y+"]: "+score[x][y]);
					//					checkedBlankCells[x][y]=true;


					//					i++;
				}
			}
		}
		if(playerId==Player.HUMAN){
			findMinScore();

		}
		else{
			findMaxScore();
		}
		System.out.println("Sent to decode from minimax lookahead: "+retX + " "+ retY+ " " +points);
		DecodeEncode de= new DecodeEncode(retX, retY, points);
		return de.getEncoded_number();
	}
	private int generateScore() {
		// TODO Auto-generated method stub
		int value;

		if(isGameDrawn){
			value=0-depth;


		}else if(playerId==Player.COMPUTER){
			value= 100-depth;
		}
		else{
			value=-100+depth;
		}
		System.err.println("Depth was "+depth+" and final score of simulation was"+value);
		return value;
	}

	private void findMaxScore() {
		// TODO Auto-generated method stub
		points=-1000;
		for(int x=0; x<score[0].length; x++){
			for(int y=0; y<score.length; y++){
				if(score[x][y]>=points && blankCells[x][y]){
					points=score[x][y];
					retX=x;
					retY=y;
				}
			}
		}
	}
	private void findMinScore() {
		// TODO Auto-generated method stub
		points=1000;
		for(int x=0; x<score[0].length; x++){
			for(int y=0; y<score.length; y++){
				if(score[x][y]<=points && blankCells[x][y]){
					points=score[x][y];
					retX=x;
					retY=y;
				}
			}
		}
	}

	public boolean isGameOver(int x, int y, int [][] t) {
		// TODO Auto-generated method stub
		//		System.out.println(Player.player1_moves);

		int id= playerId;
		if (x==y || (x==0 && y==2) || (x==2 && y==0)){
			if(t[1][1]==id && ((t[0][2]==id && t[2][0]==id)|| (t[0][0]==id && t[2][2]==id ))){
				//				System.out.println("Player "+id+" has won!!");
				//				System.exit(0);
				System.out.println("simulation ends: DIAGONAL");
				return true;
			}
		}
		int x1= (x+1)%3;
		int x2= (x+2)%3;
		int y1= (y+1)%3;
		int y2= (y+2)%3;
		if((t[x][y]==id && t[x][y1]==id && t[x][y2]==id)||(t[x][y]==id && t[x1][y]==id && t[x2][y]==id)){
			//			System.out.println("Player "+id+" has won!!");
			//			System.exit(0);
			System.out.println("simulation ends: Horizontal/Vertical");
			return true;

		}
		isGameDrawn=true;
		for(int i=0; i<blankCells[0].length; i++){
			for(int j=0; j<blankCells.length; j++){
				if(/*blankCells[i][j]*/t[i][j]==-1){
					isGameDrawn=false;
					//					System.out.println("simulation continue");

					return false;
				}
			}
		}
		//		System.out.println("simulation did not end in minimax class");
		System.out.println("Simulation ends as draw");
		return true;
	}
}	
