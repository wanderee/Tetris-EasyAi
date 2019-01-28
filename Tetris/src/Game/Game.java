package Game;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Pattern.Draw;
import Pattern.TetrisLattice;
public class Game{
	private TetrisLattice Tlattice = new TetrisLattice();
	private TetrisAI ai;
	private TetrisMove Tmove = new TetrisMove();
	private Draw draw;
	JFrame frame = new JFrame();
	//JLabel label;
	public static int handler;
	public static int times = 10;
	public Game() {
		handler = -1;
//		label=new JLabel("没有按下键盘");
//		getContentPane().add(label, BorderLayout.CENTER);
//		//addKeyListener(Tmove);
//		setSize(300,200);
//		setVisible(true);
		ai = new TetrisAI(Tlattice.getWidth(), Tlattice.getHeight());
		draw = Tlattice.DrawLattice(15);
		draw.update(Tlattice.Lattices);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("俄罗斯方块");
		frame.add(draw);
		frame.pack();
		frame.setVisible(true);
	}
	public void run() {
		frame.addKeyListener(Tmove);
		Tlattice.BlockFall();
	}
	public void update() {
		draw.update(Tlattice.Lattices);
		frame.repaint();
	}
//	private void getHandlerNumber() {
//		char keyboard = Tmove.getKeyBoard();
//		if(keyboard == 'w') {
//			handler = 0;
//		}
//		else if(keyboard == 'd') {
//			handler = 1;
//		}
//		else if(keyboard == 's') {
//			handler = 2;
//		}
//		else if(keyboard == 'a') {
//			handler = 3;
//		}
//		else {
//			handler = -1;
//		}		
//	}
	public void move() {
		Tlattice.Handler(handler);
		handler = -1;
	}
	public void AImove() {
		print_test(11);
		ai.calcWay(Tlattice);
		print_test(2);
		ArrayList<Integer> hArrayList = ai.HandlerList;
		print_test(3);
		for(Integer a:hArrayList) {
			Tlattice.Handler(a);
		}
		print_test(4);
		handler = -1;
	}
	public void fall() {
		Tlattice.BlockFall();
		print_test(1);
	}
	public void print_test(int a) {
		//System.out.println(a);
	}
	public boolean gameover() {
		return Tlattice.die();
	}
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
		try { 
			while(true) {
				game.AImove();
				//game.move();
				Thread.currentThread().sleep(times);//毫秒 
				game.fall();
				game.update();
				if(game.gameover()) {
					break;
				}
			}
		} 
		catch(Exception e){
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "游戏结束", "提示", JOptionPane.ERROR_MESSAGE);
	}
}
