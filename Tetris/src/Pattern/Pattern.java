package Pattern;
/**
 * 有关坐标系的问题，向下向右为正
 */
public class Pattern {
	public boolean[][] mat = new boolean[4][4];
	public int top,buttom,left,right;
	public int type;
	public int currtype = 0;
	public Pattern(boolean[][] mat, int type) {
		super();
		this.mat = mat;
		this.type = type;
		calRegion();
	}
	private void calRegion() {
		top = 3;
		left = 3;
		right = 0;
		buttom = 0;
		for(int i = 0;i < 4; i++) {
			for(int j = 0;j < 4; j++) {
				if(mat[i][j]) {
					if(top>i) {
						top = i;
					}
					if(left>j) {
						left = j;
					}
					if(buttom<i) {
						buttom = i;
					}
					if(right<j) {
						right = j; 
					}
				}
			}
		}
	}
	public void print_info() {
		System.out.println("top = " +top);
		System.out.println("left = " +left);
		System.out.println("buttom = " +buttom);
		System.out.println("right = " +right);
		System.out.println("currtype = " +currtype);
	}
	public void rotate() {
		boolean[][] tem  = new boolean[4][4];
		for(int i = 0;i < 4; i++) {
			for(int j = 0;j < 4; j++) {
				tem[3-j][i] = mat[i][j];
			}
		}
		mat = tem;
		
		currtype = (currtype + 1)%type;
		
		calRegion();
		
	}
	public void derotate() {
		boolean[][] tem  = new boolean[4][4];
		for(int i = 0;i < 4; i++) {
			for(int j = 0;j < 4; j++) {
				tem[j][3-i] = mat[i][j];
			}
		}
		mat = tem;
		
		currtype = (type+(currtype - 1))%type;
		
		calRegion();
		
	}

}
