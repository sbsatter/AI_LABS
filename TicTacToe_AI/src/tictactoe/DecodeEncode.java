package tictactoe;

public class DecodeEncode {
	
	int x,y,score;
	int encoded_number;
	boolean negativeScore;
	public DecodeEncode(int x,int y,int score){
		this.x=x;
		this.y=y;
		negativeScore=(score<0)?true:false;
		this.score=Math.abs(score);
		
		encode();
	}

	public DecodeEncode(int encoded_number) {
		// TODO Auto-generated constructor stub
		this.encoded_number=Math.abs(encoded_number);
		if((encoded_number&0x00800000)==0x00800000){
			negativeScore=true;
//			this.encoded_number&=0x00ffffff;
		}
		decode();
	}

	private void encode() {
		// TODO Auto-generated method stub
		encoded_number=(score<<16)+(y<<8)+x;
		if(negativeScore){
			encoded_number|=0x00800000;
			System.out.println("after bitwise or: " + Integer.toBinaryString(encoded_number));
		}
//		encoded_number+=;
		System.out.println("encoded num: "+encoded_number+" where x: "+
x+", y: "+", score: "+score+"and is negative: "+negativeScore);
	}
	
	public void decode() {
		// TODO Auto-generated method stub
		x=(encoded_number&255);
		y=(encoded_number>>8)&255;
//		if(negativeScore){
//			score|=0x80;
//		}
		score=((encoded_number>>16)&0x0000007f);
		if(negativeScore){
			score=-score;
		}
		System.out.println("decoded: "+x+ " "+ y+ " "+ score + " negative- "+negativeScore);
	}

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	public int getScore() {
		System.out.println("get score result is: "+ score);
		return score;
	}

	public int getEncoded_number() {
		return encoded_number;
	}

	public void setEncoded_number(int encoded_number) {
		this.encoded_number = encoded_number;
	}

}
