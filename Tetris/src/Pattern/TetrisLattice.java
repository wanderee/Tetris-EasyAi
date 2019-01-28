package Pattern;

import java.util.Random;

import javax.swing.JFrame;


public class TetrisLattice {
	//三角
	public static final boolean[][] p0 = {{false,false,false,false},
										  {false,false,false,false},
										  {false,false,true ,false},
										  {false,true ,true ,true }};

	//石头
	public static final boolean[][] p1 = {{false,false,false,false},
										  {false,true ,true ,false},
										  {false,true ,true ,false},
										  {false,false,false,false}};
	//横条
	public static final boolean[][] p2 = {{false,false,true ,false},
										  {false,false,true ,false},
										  {false,false,true ,false},
										  {false,false,true ,false}};
	//左闪电
	public static final boolean[][] p3 = {{false,false,false,false},
										  {false,false,false,false},
										  {false,true ,true ,false},
										  {false,false,true ,true }};
	//右闪电
	public static final boolean[][] p4 = {{false,false,false,false},
										  {false,false,false,false},
										  {false,true ,true ,false},
										  {true ,true ,false,false}};
	//左横折
	public static final boolean[][] p5 = {{false,false,false,false},
										  {false,false,false,false},
										  {false,false,true ,false},
										  {true ,true ,true ,false}};
	//右横折
	public static final boolean[][] p6 = {{false,false,false,false},
										  {false,false,false,false},
										  {false,true ,false,false},
										  {false,true ,true ,true }};
	public TetrisLattice copy(){
		// TODO Auto-generated method stub
		TetrisLattice tem = new TetrisLattice();
		tem.curr_x =curr_x;
		tem.curr_y =curr_y;
		
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				tem.Lattices[i][j] = Lattices[i][j];
			}
		}
		tem.currPatt = new Pattern(currPatt.mat, currPatt.type);
		
		return tem;
	}
	private Random rand =new Random();
	private int width = 16;
	private int height = 32;
	public int[][] Lattices = new int[height][width];
	private Pattern[] PatternList= new Pattern[7];
	private Pattern currPatt;
	private int curr_x,curr_y;   //该位置指的是4x4格点的左上角
	public void getCurrPosition(Integer x,Integer y) {
		x=curr_x;
		y=curr_y;
	}
	public int getPatternType() {
		return currPatt.type;
	}
	public TetrisLattice() {
		for(int i = 0;i < height;i++) {
			for(int j = 0;j < width;j++) {
				Lattices[i][j] = 0;
			}
		}
		PatternList[0] = new Pattern(p0, 4);
		PatternList[1] = new Pattern(p1, 1);
		PatternList[2] = new Pattern(p2, 2);
		PatternList[3] = new Pattern(p3, 2);
		PatternList[4] = new Pattern(p4, 2);
		PatternList[5] = new Pattern(p5, 4);
		PatternList[6] = new Pattern(p6, 4);
		initPattern();
	}
	public boolean Handler(int dir) {
		if(dir == 0) {//上操作
			currPatt.rotate();
			if(!AttachPattern(curr_x, curr_y)) {
				currPatt.derotate();
				return false;
			}
		}
		if(dir == 10) {//反上操作
			currPatt.derotate();
			if(!AttachPattern(curr_x, curr_y)) {
				currPatt.rotate();
				return false;
			}
		}
		if(dir == 1) {//右操作
			curr_x++;
			if(!AttachPattern(curr_x, curr_y)) {
				curr_x--;
				return false;
			}
		}
		if(dir == 2) {//下操作
			//speedup();
		}
		if(dir == 3) {//左操作
			curr_x--;
			if(!AttachPattern(curr_x, curr_y)) {
				curr_x++;
				return false;
			}
		}
		return true;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
//	private void speedup() {
//		// TODO Auto-generated method stub
//		BlockFall();
//	}
//	private void speeddown() {
//		// TODO Auto-generated method stub 
//		
//	}
	public void initPattern(int type) {
		if(type<0||type>=PatternList.length) {return;}
		else {
			currPatt = PatternList[type];
			curr_x = width/2 -2;
			curr_y = 0;
		}
	}
	private void initPattern() {
		currPatt = PatternList[rand.nextInt(PatternList.length)];
		curr_x = width/2 -2;
		curr_y = 0;
	}
	public boolean charge(int x,int y) {
		//判断图形是否在合法区域
		if(x+currPatt.left<0) {return false;}
		if(x+currPatt.right>=width) {return false;}
		if(y+currPatt.buttom>=height) {return false;}
		//图形在合法区域的情况下判断是否会出现方块重合的情况
		for(int i = currPatt.top;i <= currPatt.buttom;i++) {
			for(int j = currPatt.left; j <= currPatt.right;j++) {
				if(currPatt.mat[i][j]&&Lattices[y+i][x+j]==2) {
					return false;
				}
			}
		}
		return true;
	}
	private boolean AttachPattern(int x,int y) {
		if(!charge(x, y)) { return false;}
		else {
			HandlePattern(0);
			for(int i = currPatt.top;i <= currPatt.buttom;i++) {
				for(int j = currPatt.left; j <= currPatt.right;j++) {
					if(currPatt.mat[i][j]) {
						Lattices[y+i][x+j] = 1;
					}
				}
			}
			return true;
		}
	}

	private void HandlePattern(int handle) {
		for(int i = 0;i < height;i++) {
			for(int j = 0;j < width;j++) {
				if(Lattices[i][j] == 1) {
					Lattices[i][j] = handle;
				}
			}
		}
	}
	private int charge_earse() {
		boolean sum = true ;
		int row = -1;
		for(int i=0;i<height;i++){
			sum = true;
			for(int j=0;j<width;j++) {
				if(Lattices[i][j]!=2) {sum = false;break;}
			}
			if(sum) {
				row = i;
			}
		}
		return row;
	}
	private int earse_row() {
		int times = 0;
		int row = charge_earse();
		if(row!=-1) {
			int[][] tem = new int[height][width];
			for(int i = 0;i<width;i++) {
				tem[row][i] = 0;
			}
			for(int i = 0;i<row;i++) {
				tem[i+1] = Lattices[i];
			}
			for(int i = row+1;i<height;i++) {
				tem[i] = Lattices[i];
			}
			Lattices = tem;
			times+=earse_row();
		}
		return times;
	}
	public boolean BlockFall() {
		curr_y++;
		if(!AttachPattern(curr_x, curr_y)) {
			curr_y--;
			HandlePattern(2);
			earse_row();
			initPattern();
			return true;
		}
		return false;
	}
	public boolean die() {
		for(int i=0;i<width;i++) {
			if(Lattices[2][i]==2) {
				return true;
			}
		}
		return false;
	}
	public boolean tryBlockFall() {
		curr_y++;
		if(!AttachPattern(curr_x, curr_y)) {
			curr_y--;
			HandlePattern(2);
			earse_row();
			//initPattern();
			return true;
		}
		return false;
	}
	public void print_pattern(boolean[][] a) {
		System.out.println("------------");
		for(int i =0;i < 4;i++) {
			for(int j=0;j < 4;j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
	public void print_Lattices() {
		System.out.println("------------");
		for(int i = 0;i < height;i++) {
			for(int j = 0;j < width;j++) {
				System.out.print(Lattices[i][j]);
			}
			System.out.println();
		}
	}
	public Draw DrawLattice(int size) {
		return new Draw(width, height, size);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TetrisLattice Tlattice = new TetrisLattice();
		Draw draw = new Draw(Tlattice.width, Tlattice.height, 15);
		draw.update(Tlattice.Lattices);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Draw");
		frame.add(draw);
		frame.pack();
		frame.setVisible(true);
	}

}

