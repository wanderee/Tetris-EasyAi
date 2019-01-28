package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisMove implements KeyListener {
	public char key;
	private boolean able;
	public TetrisMove() {
		key = ' ';
		able = false;
	}
	public void keyPressed(KeyEvent e) {
		key = e.getKeyChar();
		able = true;
		if(key == 's') {
			if(Game.times>5) {
			Game.times = Game.times/2;}
		}
	}
	public void keyReleased(KeyEvent e) {
		if(key == 's') {
			Game.times = 10;
		}
		able = false;
		}
	public void keyTyped(KeyEvent e){
		char keyboard = getKeyBoard();
		if(keyboard == 'w') {
			Game.handler = 0;
		}
		else if(keyboard == 'd') {
			Game.handler = 1;
		}
		else if(keyboard == 's') {
			Game.handler = 2;
			//Game.times = 100;
		}
		else if(keyboard == 'a') {
			Game.handler = 3;
		}
		else {
			Game.handler = -1;
		}
	}
	public char getKeyBoard() {
		if(able) {
			return key;
		}
		else {
			return ' ';
		}
	}
}
