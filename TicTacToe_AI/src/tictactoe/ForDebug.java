package tictactoe;

public class ForDebug {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int i=100;
////		System.out.println(i);
//		int j=2;
//		i=(i<<8)+j;
////		System.out.println(i);
//		int k=2;
//		i=(i<<8)+k;
////		System.out.println(i);
//		int c=i&255;
//		System.out.println(c);
//		i=c=(i>>8);
//		c=i&255;
//		System.out.println(c);
//		c=(i>>8);
//		System.out.println(c);
//		boolean bla [][]= new boolean [3][3];
//		System.out.println(bla[2][2]);
		DecodeEncode de= new DecodeEncode(0, 2, -127);//upto -127 and 127
		de= new DecodeEncode(16712192);
		System.out.println(de.getScore());
	}

}
