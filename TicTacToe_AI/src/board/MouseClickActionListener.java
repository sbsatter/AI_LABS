package board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MouseClickActionListener implements ActionListener {

	private int x, y;
	public MouseClickActionListener(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.print(x+", "+y+" ");
		Game.setButtonAppearance(x, y, tictactoe.Player.getPlayer());

	}

}
